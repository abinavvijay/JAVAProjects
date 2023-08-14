package com.infovision.canteen.dto.admin;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.infovision.canteen.model.admin.FoodType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDto {

	private String itemName;

	private FoodType foodType;
	
	private String imageUrl;
}
