package com.infovision.canteen.serviceimpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infovision.canteen.exception.OrderException;
import com.infovision.canteen.model.delivery.Delivery;
import com.infovision.canteen.model.delivery.WorkingStatus;
import com.infovision.canteen.model.order.DeliveryOrderStatus;
import com.infovision.canteen.model.order.OrderCartItem;
import com.infovision.canteen.model.order.OrderStatus;
import com.infovision.canteen.model.order.Orders;
import com.infovision.canteen.repository.CartItemRepository;
import com.infovision.canteen.repository.DeliveryRepository;
import com.infovision.canteen.repository.OrderCartItemRepository;
import com.infovision.canteen.repository.OrderRepository;
import com.infovision.canteen.repository.PaymentRepository;
import com.infovision.canteen.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private OrderCartItemRepository orderCartItemRepository;

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public List<Orders> assignOrders() throws OrderException {
		// TODO Auto-generated method stub
		
		List<Orders> orders= orderRepository.findByTIme(LocalTime.now().minusMinutes(5),LocalDate.now());
		
		if(orders.isEmpty())
			throw new OrderException("Orders list is empty");
		
		List<Delivery> deliveryBoys=deliveryRepository.findDeliveryBoys();
		
		if(deliveryBoys.isEmpty())
			throw new OrderException("Delivery Boys are not avaialable");
		
		int n=0;
		
		if(orders.size()>deliveryBoys.size())
			n=deliveryBoys.size();
		else if(orders.size()<deliveryBoys.size())
			n=orders.size();
		else
			n=orders.size();
		
		for(int i=0;i<n;i++)
		{
			
			orders.get(i).setDelivery(deliveryBoys.get(i));
			orders.get(i).setDeliveryOrderStatus(DeliveryOrderStatus.PENDING);
			
			deliveryBoys.get(i).setWorkingStatus(WorkingStatus.BUSY);
			
			deliveryRepository.save(deliveryBoys.get(i));
			
			orderRepository.save(orders.get(i));
			
			List<OrderCartItem> orderCartItems=orderCartItemRepository.findByOrderId(orders.get(i).getOrderId());
			
			for(OrderCartItem cartItem:orderCartItems)
			{
				cartItem.setDeliveryOrderStatus(OrderStatus.ACCEPT);
				orderCartItemRepository.save(cartItem);
				
			}
			
		}
		
		List<Orders> ora=new ArrayList<>();
		
		if(n<orders.size())
			ora=orders.subList(n, orders.size());
		
		return ora;
	}

}
