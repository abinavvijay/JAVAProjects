package com.infovision.canteen.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.infovision.canteen.dto.cart.CartItemDto;
import com.infovision.canteen.exception.CartException;
import com.infovision.canteen.model.cart.CartItem;
import com.infovision.canteen.model.restaurant.RestaurantItem;

public interface CartService {

	String addToCart(UUID itemId,UUID empId,int quantity) throws CartException;

	List<CartItem> viewCartItems(UUID cartId) throws Exception;

	
	List<CartItem> editCartItems(UUID itemId, UUID cartId,int quantity) throws Exception;

	String deleteCartItems(UUID itemId, UUID cartId) throws Exception;

}
