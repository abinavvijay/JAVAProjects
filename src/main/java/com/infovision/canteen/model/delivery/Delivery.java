package com.infovision.canteen.model.delivery;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.infovision.canteen.model.credentials.Credentials;
import com.infovision.canteen.model.cart.Cart;
import com.infovision.canteen.model.employee.Employee;
import com.infovision.canteen.model.employee.Profile;
import com.infovision.canteen.model.credentials.Credentials;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID deliveryId;
	
	private long mobileNumber;
	
	@Enumerated(EnumType.STRING)
	private DeliveryPersonStatus deliveryPersonStatus;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@Enumerated(EnumType.STRING)
	private WorkingStatus workingStatus;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private Credentials credentials;
	
	
}
