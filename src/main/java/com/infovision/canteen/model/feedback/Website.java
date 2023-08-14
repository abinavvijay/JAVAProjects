package com.infovision.canteen.model.feedback;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.infovision.canteen.model.employee.Employee;
import com.infovision.canteen.model.restaurant.RestaurantItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Website {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID websiteId;
	
	private String websiteName;

}
