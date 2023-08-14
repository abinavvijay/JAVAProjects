package com.infovision.canteen.service;

import java.util.List;

import com.infovision.canteen.exception.OrderException;
import com.infovision.canteen.model.order.Orders;

public interface CustomerService {

	
	List<Orders> assignOrders() throws OrderException;

}
