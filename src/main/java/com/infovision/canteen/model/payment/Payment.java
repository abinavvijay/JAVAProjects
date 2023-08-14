package com.infovision.canteen.model.payment;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.infovision.canteen.model.restaurant.Restaurant;
import com.infovision.canteen.model.restaurant.RestaurantProfile;
import com.infovision.canteen.model.restaurant.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID paymentId;
	
	private double amount;
	
	@Enumerated(EnumType.STRING)
	private Mode mode;
}
