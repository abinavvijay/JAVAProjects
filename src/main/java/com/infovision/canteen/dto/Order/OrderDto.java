package com.infovision.canteen.dto.Order;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

import com.infovision.canteen.dto.employee.ProfileDto;
import com.infovision.canteen.model.cart.Cart;
import com.infovision.canteen.model.employee.Employee;
import com.infovision.canteen.model.payment.Payment;
import com.infovision.canteen.model.restaurant.RestaurantItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
	
	private UUID empId;
	
	private UUID restaurantItemId;
	
	private UUID cartId;
	

}
