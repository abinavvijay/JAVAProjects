package com.infovision.canteen.dto.restaurant;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantItemDto {

	private String itemName;

	private double itemprice;

	private String itemDesc;
	
	private float rating;
	
	private String imageUrl;
	
}
