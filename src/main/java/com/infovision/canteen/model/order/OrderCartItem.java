package com.infovision.canteen.model.order;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.infovision.canteen.model.cart.Cart;
import com.infovision.canteen.model.delivery.Delivery;
import com.infovision.canteen.model.employee.Employee;
import com.infovision.canteen.model.payment.Payment;
import com.infovision.canteen.model.restaurant.RestaurantItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderCartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID orderCartItemId;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;
	
	@OneToOne(cascade = CascadeType.ALL)
	private RestaurantItem restaurantItem;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus RestaurantOrderStatus;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus DeliveryOrderStatus;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Orders order;
	
	private int quantity;
	
	private double amount;

}
