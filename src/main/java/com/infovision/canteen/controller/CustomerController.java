package com.infovision.canteen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infovision.canteen.exception.ErrorHandler;
import com.infovision.canteen.service.CustomerService;
import com.infovision.canteen.service.DeliveryService;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin("*")
@Log4j2
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/service", method =  RequestMethod.PUT)
	public ResponseEntity<?> assignOrders() {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<>(customerService.assignOrders(), HttpStatus.OK);
			    log.info("assing orders succesfully");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("assing orders not succesfully");
		}
		return response;
	}

}
