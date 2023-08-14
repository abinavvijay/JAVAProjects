package com.infovision.canteen.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryProfileDto {
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private long mobileNumber;
	
	private String addressLine;
	
	private long pincode;
	
	private String country;
	
	private String state;
	
	private String city;
	
	private String Gender;
	
	private String imageUrl;
}
