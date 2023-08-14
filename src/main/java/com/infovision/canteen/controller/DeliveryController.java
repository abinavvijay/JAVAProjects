package com.infovision.canteen.controller;

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

import com.infovision.canteen.dto.delivery.DeliveryProfileDto;
import com.infovision.canteen.exception.ErrorHandler;
import com.infovision.canteen.model.delivery.DeliveryPersonStatus;
import com.infovision.canteen.service.DeliveryService;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin("*")
@Log4j2
public class DeliveryController {
	
	@Autowired
	private DeliveryService deliveryService;
	
	
//Delivery boy login
	@RequestMapping(value = "/deliverylogin", method =  RequestMethod.GET)
	public ResponseEntity<?> login(@RequestParam String userName, @RequestParam String password) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<>(deliveryService.login(userName, password), HttpStatus.OK);
			    log.info("Login successful");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Invalid Username of Password");
		}
		return response;
	}
	
	
//Delivery boy status update
	@RequestMapping(value = "/deliverypersonstatus", method = RequestMethod.PUT)
	public ResponseEntity<?> updateDeliveryPersonStatus(@RequestParam UUID deliveryId,@RequestParam DeliveryPersonStatus status) throws Exception {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<>(deliveryService.updateDeliveryPersonStatus(deliveryId,status), HttpStatus.OK);
			    log.info("Employee details are edited");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Employee details are not edited");
		}
		return response;
	}

	
//Add Delivery Boy's Profile
//	@RequestMapping(value = "/deliveryperson", method =  RequestMethod.POST)
//	public ResponseEntity<?>addDeliveryBoy(@RequestBody DeliveryProfileDto deliveryProfileDto,@RequestParam UUID deliveryId) {
//		ResponseEntity<?> response;
//		try {
//			response = new ResponseEntity<>(deliveryService.addDeliveryBoy(deliveryProfileDto,deliveryId), HttpStatus.OK);
//			    log.info("New Delivery boy has been added successfully");
//		} catch (Exception e) {
//			response = new ResponseEntity<ErrorHandler>(
//					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
//			 log.error("Delivery Boy has not been added");
//		}
//		return response;
//	}
	
	
//Edit Delivery Boy's Profile by id
	@RequestMapping(value = "/deliveryperson", method = RequestMethod.PUT)
	public ResponseEntity<?> updateDeliveryBoy(@RequestBody DeliveryProfileDto deliveryProfileDto, @RequestParam UUID deliveryId) throws Exception {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<>(deliveryService.editDeliveryBoy(deliveryProfileDto, deliveryId), HttpStatus.OK);
			    log.info("Delivery boy's profile has been updateed successfully");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Unable to update Delivery boy's profile");
		}
		return response;
	}
	
	
//View Delivery Boy's Profile by id
	@RequestMapping(value = "/deliveryperson", method = RequestMethod.GET)
	public ResponseEntity<?> getDeliveryProfileById(@RequestParam UUID deliveryId) throws Exception {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<>(deliveryService.viewDeliveryProfileById(deliveryId), HttpStatus.OK);
			    log.info("viewing delivery boy's profile details");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Unable to view delivery boy's profile details");
		}
		return response;
	}	
	
	//View Delivery Boy's Order yet to be delivered
	@RequestMapping(value = "/deliveryorder", method = RequestMethod.GET)
	public ResponseEntity<?> getDeliveryOrder(@RequestParam UUID deliveryId) throws Exception {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<>(deliveryService.getDeliveryOrder(deliveryId), HttpStatus.OK);
			    log.info("viewing delivery boy's profile details");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Unable to view delivery boy's profile details");
		}
		return response;
	}	
	
	@RequestMapping(value = "/monthdeliveryorders", method = RequestMethod.GET)
	public ResponseEntity<?> getMonthDeliveryOrder(@RequestParam UUID deliveryId) throws Exception {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<>(deliveryService.getMonthDeliveryOrder(deliveryId), HttpStatus.OK);
			    log.info("viewing delivery boy's profile details");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Unable to view delivery boy's profile details");
		}
		return response;
	}	
	
	@RequestMapping(value = "/workingstatus", method = RequestMethod.PUT)
	public ResponseEntity<?> workingStatus(@RequestParam UUID deliveryId) throws Exception {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<>(deliveryService.workingStatus(deliveryId), HttpStatus.OK);
			    log.info("viewing delivery boy's profile details");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Unable to view delivery boy's profile details");
		}
		return response;
	}	
	
	@RequestMapping(value = "/dailydeliveryorders", method = RequestMethod.GET)
	public ResponseEntity<?> dailyDeliveryOrders(@RequestParam UUID deliveryId) throws Exception {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<>(deliveryService.dailyDeliveryOrders(deliveryId), HttpStatus.OK);
			    log.info("viewing delivery boy's profile details");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Unable to view delivery boy's profile details");
		}
		return response;
	}	
	
	@RequestMapping(value = "/deliverymonthrevenue", method = RequestMethod.GET)
	public ResponseEntity<?> monthRevenue(@RequestParam UUID deliveryId) throws Exception {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<Double>(deliveryService.monthRevenue(deliveryId), HttpStatus.OK);
			    log.info("viewing delivery boy's profile details");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Unable to view delivery boy's profile details");
		}
		return response;
	}	
	
}
