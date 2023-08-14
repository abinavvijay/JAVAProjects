package com.infovision.canteen.dto.admin;

import java.util.UUID;

import com.infovision.canteen.model.admin.FoodType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditMenuItemDto {
	
	private UUID id;
	
	private String itemName;

	private FoodType foodType;
	
	private String imageUrl;

}
