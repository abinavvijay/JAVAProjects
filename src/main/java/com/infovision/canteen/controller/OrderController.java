package com.infovision.canteen.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infovision.canteen.dto.Order.OrderDto;
import com.infovision.canteen.dto.restaurant.RestaurantProfileDto;
import com.infovision.canteen.exception.ErrorHandler;
import com.infovision.canteen.service.OrderService;
import com.infovision.canteen.service.RestaurantService;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin("*")
@Log4j2
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ResponseEntity<?> orderItem(@RequestParam UUID empId, @RequestParam UUID cartId) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(orderService.orderItem(empId,cartId), HttpStatus.OK);
			log.info("new Order is Placed");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Order is not Placed");
		}

		return response;
	
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.PUT)
	public ResponseEntity<?> cancelOrderItem(@RequestParam UUID orderId) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(orderService.cancelOrderItem(orderId), HttpStatus.OK);
			log.info(" Order is cancelled");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Order is not cancelled");
		}

		return response;
	
	}

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public ResponseEntity<?> getEmpOrders(@RequestParam UUID empId) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(orderService.getEmpOrders(empId), HttpStatus.OK);
			log.info(" Order is cancelled");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Order is not cancelled");
		}

		return response;
	
	}
	
	@RequestMapping(value = "/allorders", method = RequestMethod.GET)
	public ResponseEntity<?> getAllOrders() throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
			log.info(" Order is cancelled");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Order is not cancelled");
		}

		return response;
	
	}
	
	@RequestMapping(value = "/restaurantorders", method = RequestMethod.GET)
	public ResponseEntity<?> getRestaurantOrders(@RequestParam UUID restId) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(orderService.getRestaurantOrders(restId), HttpStatus.OK);
			log.info(" Order is cancelled");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Order is not cancelled");
		}

		return response;
	
	}
	
	@RequestMapping(value = "/topsellingorders", method = RequestMethod.GET)
	public ResponseEntity<?> topSellingOrders() throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(orderService.topSellingOrders(), HttpStatus.OK);
			log.info(" Order is cancelled");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Order is not cancelled");
		}

		return response;
	
	}
	
	@RequestMapping(value = "/discount", method = RequestMethod.PUT)
	public ResponseEntity<?> discount(@RequestParam UUID itemId,@RequestParam double discount) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(orderService.discounts(itemId,discount), HttpStatus.OK);
			log.info(" Order is cancelled");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Order is not cancelled");
		}

		return response;
	
	}
}
