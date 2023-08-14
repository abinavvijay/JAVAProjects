package com.infovision.canteen.dto.cart;

import java.util.List;
import java.util.UUID;

import com.infovision.canteen.model.cart.CartItem;
import com.infovision.canteen.model.restaurant.RestaurantItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

	
	
	private List<ViewItemDto> restaurantItems;
	
	private double total;
}
