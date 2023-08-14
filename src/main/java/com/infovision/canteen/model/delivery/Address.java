package com.infovision.canteen.model.delivery;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID addressId;
	
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
