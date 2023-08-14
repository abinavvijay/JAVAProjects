package com.infovision.canteen.model.restaurant;

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
public class RestaurantProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID restaurantProfileId;
	
	private String restaurantName;
	
	private String imageUrl;
	
	private String email;
	
	private long mobileNumber;
	
	private long pincode;
	
	private String password;
	
	private String country;
	
	private String state;
	
	private String city;
}
