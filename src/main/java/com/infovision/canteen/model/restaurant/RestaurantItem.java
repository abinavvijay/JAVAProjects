package com.infovision.canteen.model.restaurant;

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
public class RestaurantItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID itemId;
	
	private String itemName;
	
	private double itemprice;
	
	private String itemDesc;
	
	private float rating;
	
	private String imageUrl;
	
	private double discount;
	
	@Enumerated(EnumType.STRING)
	private ItemStatus status;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Restaurant restaurant;	

}
