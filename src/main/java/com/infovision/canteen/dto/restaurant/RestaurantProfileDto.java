package com.infovision.canteen.dto.restaurant;

import javax.persistence.Lob;

import org.hibernate.type.descriptor.sql.VarbinaryTypeDescriptor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantProfileDto {

	private String restaurantName;

	private String imageUrl;

	private String email;

	private long mobileNumber;

	private String password;

	private String country;

	private String state;

	private String city;
	
	private long pincode;
}
