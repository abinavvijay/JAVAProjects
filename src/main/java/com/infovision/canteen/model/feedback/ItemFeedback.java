package com.infovision.canteen.model.feedback;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.infovision.canteen.model.employee.Employee;
import com.infovision.canteen.model.restaurant.RestaurantItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ItemFeedback {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID feedbackId;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Employee employee;
	
	private String feedback;
	
	@OneToOne(cascade = CascadeType.ALL)
	private RestaurantItem restaurantItem;

	private double rating;
	
}
