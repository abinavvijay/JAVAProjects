package com.infovision.canteen.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infovision.canteen.dto.cart.CartItemDto;
import com.infovision.canteen.dto.cart.ViewItemDto;
import com.infovision.canteen.exception.CartException;
import com.infovision.canteen.model.cart.Cart;
import com.infovision.canteen.model.cart.CartItem;
import com.infovision.canteen.model.employee.Employee;
import com.infovision.canteen.model.order.OrderCartItem;
import com.infovision.canteen.model.restaurant.RestaurantItem;
import com.infovision.canteen.repository.CartItemRepository;
import com.infovision.canteen.repository.CartRepository;
import com.infovision.canteen.repository.EmployeeRepository;
import com.infovision.canteen.repository.OrderCartItemRepository;
import com.infovision.canteen.repository.RestaurantItemRepository;
import com.infovision.canteen.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private RestaurantItemRepository restaurantItemRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CartRepository cartRepository;

	@Override
	public String addToCart(UUID itemId, UUID empId, int quantity) throws CartException {
		// TODO Auto-generated method stub

		if (restaurantItemRepository.existsById(itemId)) {
			if (employeeRepository.existsById(empId)) {

				RestaurantItem restaurantItem = restaurantItemRepository.getOne(itemId);
				Employee employee = employeeRepository.getOne(empId);

				List<CartItem> cartItems = cartItemRepository.findByCartId(employee.getCart().getCartId());

					for (CartItem item : cartItems) {

						if(item.getRestaurantItem().getRestaurant().getRestaurantProfile().getRestaurantName() 
								!= restaurantItem.getRestaurant().getRestaurantProfile().getRestaurantName())
							throw new CartException("Item belongs to various restaurant");
						
						if (item.getRestaurantItem().getItemId()
								.equals(itemId) 
								)
							throw new CartException("Item already exists in cart");

					}

					addProductTocart(employee, restaurantItem, quantity);

				settingCartAmount(employee.getCart().getCartId());

			} else
				throw new CartException("employee not found");
		} else
			throw new CartException("Item not found");

		return "Item is added to cart";
	}

	private double settingCartAmount(UUID id) {
		// TODO Auto-generated method stub

		Cart cart = cartRepository.getOne(id);

		List<CartItem> cartItems = cartItemRepository.findByCartId(id);

		double total = 0;

		for (CartItem item : cartItems) {

			total += item.getAmount();
		}

		cart.setTotalamount(total);
		cartRepository.save(cart);

		return total;

	}

	public void addProductTocart(Employee employee, RestaurantItem restaurantItem, int quantity) {
		CartItem cartItem = new CartItem();

		cartItem.setCart(employee.getCart());
		cartItem.setRestaurantItem(restaurantItem);
		double discount = restaurantItem.getDiscount() * 100;
		cartItem.setAmount(quantity * (restaurantItem.getItemprice() - discount));
		cartItem.setQuantity(quantity);
		cartItemRepository.save(cartItem);
	}

	@Override
	public List<CartItem> viewCartItems(UUID cartId) throws Exception {
		// TODO Auto-generated method stub

		if (cartRepository.existsById(cartId)) {

			List<CartItem> cartItems = cartItemRepository.findCartItems(cartId);

			if (cartItems.isEmpty())
				throw new Exception("Cart items are empty");

			return cartItems;

		} else
			throw new Exception("Cart Not found");

	}

	@Override
	public List<CartItem> editCartItems(UUID itemId, UUID cartId, int quantity) throws Exception {
		// TODO Auto-generated method stub
		if (cartRepository.existsById(cartId)) {
			
			if (restaurantItemRepository.existsById(itemId)) {

				List<CartItem> cartItems = cartItemRepository.findCartItems(cartId);

				if (cartItems.isEmpty())
					throw new Exception("Cart Id not found");

				for (CartItem cartItem : cartItems) {

					if (cartItem.getCart().getCartId().equals(cartId)
							&& cartItem.getRestaurantItem().getItemId().equals(itemId)) {
						cartItem.setQuantity(quantity);
						cartItem.setAmount(quantity * cartItem.getRestaurantItem().getItemprice());
						cartItemRepository.save(cartItem);
					}

					settingCartAmount(cartId);
					
				}
				
				return cartItems;

			} else
				throw new Exception("Item Details not found");
		} else
			throw new Exception("cartDetails not found");

		
	}

	@Override
	@Transactional
	public String deleteCartItems(UUID itemId, UUID cartId) throws Exception {
		// TODO Auto-generated method stub
		
		if(cartRepository.existsById(cartId))
		{
		if(restaurantItemRepository.existsById(itemId))
		{
		List<CartItem> cartItems = cartItemRepository.findCartItems(cartId);

		if (cartItems.isEmpty())
			throw new Exception("Cart Id not found");

		for (CartItem cartItem : cartItems) {

			if (cartItem.getCart().getCartId().equals(cartId)
					&& cartItem.getRestaurantItem().getItemId().equals(itemId)) {

				cartItemRepository.delete(cartItem.getCartItemId());

			}

		}
		}
		else
			throw new Exception("item does not exits");
		}
		else
			throw new Exception("Cart does not exits");
		
		return "CartItem deleted from Cart";
	}

}
