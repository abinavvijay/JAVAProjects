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

import com.infovision.canteen.dto.restaurant.RestaurantItemDto;
import com.infovision.canteen.exception.ErrorHandler;
import com.infovision.canteen.service.RestaurantItemService;
import com.infovision.canteen.service.RevenueService;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin("*")
@Log4j2
public class RevenueController {

	@Autowired
	private RevenueService revenueService;
	
	@RequestMapping(value = "/dayrevenue", method =  RequestMethod.GET)
	public ResponseEntity<?> dayRevenue(@RequestParam UUID RestId) {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(revenueService.dayRevenue(RestId), HttpStatus.OK);
			    log.info("new Restaurant is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Restaurant is not added");
		}

		return response;

	}
	
	@RequestMapping(value = "/weekrevenue", method =  RequestMethod.GET)
	public ResponseEntity<?> weekRevenue(@RequestParam UUID RestId) {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(revenueService.weekRevenue(RestId), HttpStatus.OK);
			    log.info("new Restaurant is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Restaurant is not added");
		}

		return response;

	}
	
	@RequestMapping(value = "/monthrevenue", method =  RequestMethod.GET)
	public ResponseEntity<?> monthRevenue(@RequestParam UUID RestId) {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(revenueService.monthRevenue(RestId), HttpStatus.OK);
			    log.info("new Restaurant is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Restaurant is not added");
		}

		return response;

	}
	
	@RequestMapping(value = "/dayallrevenue", method =  RequestMethod.GET)
	public ResponseEntity<?> dayAllRestRevenue() {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(revenueService.dayAllRestRevenue(), HttpStatus.OK);
			    log.info("new Restaurant is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Restaurant is not added");
		}

		return response;

	}
	
	@RequestMapping(value = "/weekallrevenue", method =  RequestMethod.GET)
	public ResponseEntity<?> weekAllRestRevenue() {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(revenueService.weekAllRestRevenue(), HttpStatus.OK);
			    log.info("new Restaurant is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Restaurant is not added");
		}

		return response;

	}
	
	@RequestMapping(value = "/monthallrevenue", method =  RequestMethod.GET)
	public ResponseEntity<?> monthAllRestRevenue() {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(revenueService.monthAllRestRevenue(), HttpStatus.OK);
			    log.info("new Restaurant is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Restaurant is not added");
		}

		return response;

	}
}
