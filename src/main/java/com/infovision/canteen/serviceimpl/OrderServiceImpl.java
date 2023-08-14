package com.infovision.canteen.serviceimpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.infovision.canteen.exception.OrderException;
import com.infovision.canteen.model.cart.CartItem;
import com.infovision.canteen.model.employee.Employee;
import com.infovision.canteen.model.order.EmployeeOrderStatus;
import com.infovision.canteen.model.order.OrderCartItem;
import com.infovision.canteen.model.order.OrderStatus;
import com.infovision.canteen.model.order.Orders;
import com.infovision.canteen.model.order.TopSellingOrders;
import com.infovision.canteen.model.payment.Mode;
import com.infovision.canteen.model.payment.Payment;
import com.infovision.canteen.model.restaurant.ItemStatus;
import com.infovision.canteen.model.restaurant.RestaurantItem;
import com.infovision.canteen.model.restaurant.Status;
import com.infovision.canteen.repository.CartItemRepository;
import com.infovision.canteen.repository.CartRepository;
import com.infovision.canteen.repository.EmployeeRepository;
import com.infovision.canteen.repository.OrderCartItemRepository;
import com.infovision.canteen.repository.OrderRepository;
import com.infovision.canteen.repository.PaymentRepository;
import com.infovision.canteen.repository.RestaurantItemRepository;
import com.infovision.canteen.repository.RestaurantRepository;
import com.infovision.canteen.repository.TopSellingOrdersRepository;
import com.infovision.canteen.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RestaurantItemRepository restaurantItemRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderCartItemRepository orderCartItemRepository;

	@Autowired
	private HttpServletResponse searchServlet;

	@Autowired
	private TopSellingOrdersRepository topSellingOrdersRepository;

	@Override
	public String orderItem(UUID empId, UUID cartId) throws Exception {
		// TODO Auto-generated method stub

		Orders order = new Orders();

		if (employeeRepository.existsById(empId))
			order.setEmployee(employeeRepository.getOne(empId));
		else
			throw new OrderException("Employee Not found");

		if (cartRepository.existsById(cartId)) {

			if (employeeRepository.getOne(empId).getCart().getCartId().equals(cartId)) {

				List<CartItem> cartItems = cartItemRepository.findCartItems(cartId);

				if (cartItems.isEmpty())
					throw new Exception("Cart consist of zero items");

				order.setCart(employeeRepository.getOne(empId).getCart());

				for (CartItem cartItem : cartItems) {

					OrderCartItem orderCartItem = new OrderCartItem();

					orderCartItem.setCart(cartItem.getCart());
					orderCartItem.setRestaurantItem(cartItem.getRestaurantItem());
					orderCartItem.setAmount(cartItem.getAmount());
					orderCartItem.setQuantity(cartItem.getQuantity());
					orderCartItem.setOrder(order);

					orderCartItemRepository.save(orderCartItem);

				}

			} else
				throw new OrderException("Cart not belongs current Employee");

		} else
			throw new OrderException("Cart Not found");

		Payment payment = new Payment();

		payment.setAmount(cartRepository.getOne(cartId).getTotalamount());
		payment.setMode(Mode.PAYTM);

		String s = String.valueOf(cartRepository.getOne(cartId).getTotalamount());

		ModelAndView m = new ModelAndView();
		
		paymentRepository.save(payment);

		order.setDate(LocalDate.now());
		order.setTime(LocalTime.now());
		order.setPayment(payment);

		order.setEmployeeOrderStatus(EmployeeOrderStatus.CONFIRM);

		orderRepository.save(order);

		List<OrderCartItem> orderCartItems = orderCartItemRepository.findByOrderId(order.getOrderId());

		for (OrderCartItem orderCartItem : orderCartItems) {

			if (orderCartItem.getRestaurantItem().getStatus().equals(ItemStatus.AVAILABLE)
					&& orderCartItem.getRestaurantItem().getRestaurant().getRestaurantStatus().equals(Status.ACTIVE)) {
				orderCartItem.setRestaurantOrderStatus(OrderStatus.ACCEPT);
				orderCartItemRepository.save(orderCartItem);
			}

		}

		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("https://payment10.azurewebsites.net");
		return "Order is placed";
	}

	@Override
	public String cancelOrderItem(UUID orderId) throws OrderException {
		// TODO Auto-generated method stub

		if (orderRepository.existsById(orderId)) {
			Orders order = orderRepository.getOne(orderId);

			LocalTime time = LocalTime.now().minusMinutes(5);
			if (time.getHour() == order.getTime().getHour() && time.getMinute() <= time.getMinute())
				order.setEmployeeOrderStatus(EmployeeOrderStatus.CANCEL);
			else
				throw new OrderException("Time Is  Expired to cancel the order");

			orderRepository.save(order);
		} else
			throw new OrderException("Order Not found");

		return "Order Cancelled";
	}

	@Override
	public List<OrderCartItem> getEmpOrders(UUID empId) throws OrderException {
		// TODO Auto-generated method stub

		if (employeeRepository.existsById(empId)) {
			Optional<Employee> emp = employeeRepository.findById(empId);

			List<OrderCartItem> orders = orderCartItemRepository.getByCart(emp.get().getCart().getCartId());
//			List<Orders> orders = orderRepository.getOrders(empId);

			if (orders.isEmpty())
				throw new OrderException("Orders list is empty");

			return orders;

		} else
			throw new OrderException("Employee Not found");

	}

	@Override
	public List<OrderCartItem> getAllOrders() throws OrderException {
		// TODO Auto-generated method stub

		List<OrderCartItem> orders = orderCartItemRepository.getAll(LocalDate.now());

		if (orders.isEmpty())
			throw new OrderException("Orders list is empty");

		return orders;

	}

	@Override
	public List<OrderCartItem> getRestaurantOrders(UUID restId) throws OrderException {
		// TODO Auto-generated method stub
		if (restaurantRepository.existsById(restId)) {
			List<OrderCartItem> orders = orderCartItemRepository.getByRestaurant(restId, LocalDate.now());

			if (orders.isEmpty())
				throw new OrderException("Orders list is empty");

			return orders;

		} else
			throw new OrderException("Restaurant Not found");
	}

	@Override
	public List<TopSellingOrders> topSellingOrders() throws OrderException {
		// TODO Auto-generated method stub

		List<TopSellingOrders> orders = orderCartItemRepository.getTopOrders(LocalDate.now());

		if (orders.isEmpty())
			throw new OrderException("Orders not found today");

		for (TopSellingOrders topSellingOrders : orders) {
			TopSellingOrders top = new TopSellingOrders();

			top.setCount(topSellingOrders.getCount());
			top.setItemId(topSellingOrders.getItemId());
			top.setDate(LocalDate.now());

			topSellingOrdersRepository.save(top);
		}

		return orders;
	}

	@Override
	public String discounts(UUID itemId, double discount) throws OrderException {
		// TODO Auto-generated method stub

		if (restaurantItemRepository.existsById(itemId)) {
			RestaurantItem restaurantItem = restaurantItemRepository.getOne(itemId);

			restaurantItem.setDiscount(discount);

			restaurantItemRepository.save(restaurantItem);
		} else
			throw new OrderException("RestaurantItem not found");

		return "discount added";
	}

}
