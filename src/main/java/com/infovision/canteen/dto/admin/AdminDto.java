package com.infovision.canteen.dto.admin;

import org.hibernate.type.descriptor.sql.VarbinaryTypeDescriptor;

import com.infovision.canteen.dto.restaurant.RestaurantProfileDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {

	private String firstName;

	private String lastName;

	private String email;

	private long mobileNumber;

	private String country;

	private String state;

	private String city;

	private String gender;

	private String password;
	
	private String imageUrl;
	
}
