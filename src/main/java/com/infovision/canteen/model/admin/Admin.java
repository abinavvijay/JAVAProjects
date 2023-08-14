package com.infovision.canteen.model.admin;

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
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

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
