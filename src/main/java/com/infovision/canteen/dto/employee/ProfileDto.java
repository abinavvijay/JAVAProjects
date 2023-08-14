package com.infovision.canteen.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {

	private String firstName;

	private String lastName;

	private String email;

	private long mobileNumber;

	private String country;

	private String state;

	private String city;

	private String gender;

	private String password;

	private long employeeId;
	
	private String imageUrl;
	
	private long pincode;
}
