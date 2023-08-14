package com.infovision.canteen.model.employee;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.infovision.canteen.model.cart.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID empId;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Profile profile;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;
	
	
}
