package com.infovision.canteen.model.admin;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID itemId;
	
	private String itemName;
	
	@Enumerated(EnumType.STRING)
	private FoodType foodType;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Admin admin;
	
	private String imageUrl;
	 

}
