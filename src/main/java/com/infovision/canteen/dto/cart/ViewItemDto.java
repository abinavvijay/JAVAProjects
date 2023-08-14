package com.infovision.canteen.dto.cart;

import java.util.UUID;

import com.infovision.canteen.model.restaurant.ItemStatus;
import com.infovision.canteen.model.restaurant.Restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewItemDto {

	private UUID itemId;
	
	private String itemName;

	private double amount;

	private String itemDesc;

	private float rating;

	private String imageUrl;

	private ItemStatus status;

	private Restaurant restaurant;
}
