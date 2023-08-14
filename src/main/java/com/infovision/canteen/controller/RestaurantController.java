package com.infovision.canteen.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infovision.canteen.dto.restaurant.RestaurantProfileDto;
import com.infovision.canteen.exception.ErrorHandler;
import com.infovision.canteen.model.restaurant.Status;
import com.infovision.canteen.service.RestaurantService;
//import com.infovision.canteen.serviceimpl.JwtUserDetailsService;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin("*")
@Log4j2
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
//	@Autowired
//	private JwtUserDetailsService userDetailsService;
	
	@RequestMapping(value = "/registerforrestaurant", method = RequestMethod.POST)
	public ResponseEntity<?> addRestaurant(@RequestBody RestaurantProfileDto restaurantProfileDto) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(restaurantService.addRestaurant(restaurantProfileDto), HttpStatus.OK);
//			userDetailsService.save(restaurantProfileDto.getEmail(),restaurantProfileDto.getPassword());
			log.info("new Restaurant is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Restaurant is not added");
		}

		return response;
	
	}
	
	
	@RequestMapping(value = "/registerforrestaurant", method = RequestMethod.PUT)
	public ResponseEntity<?> editRestaurant(@RequestBody RestaurantProfileDto restaurantProfileDto,@RequestParam UUID id,@RequestParam boolean restName) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(restaurantService.editRestaurant(restaurantProfileDto,id,restName), HttpStatus.OK);
			    log.info("Restaurant details are edited");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Restaurant details are not edited");
		}

		return response;
		
	}
	
	@RequestMapping(value = "/registerforrestaurant", method = RequestMethod.GET)
	public ResponseEntity<?> viewRestaurant(@RequestParam String restaurantName) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(restaurantService.viewRestaurant(restaurantName), HttpStatus.OK);
			    log.info("Restaurant details are Viewed");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Restaurant details are not Viewed");
		}

		return response;
		
	}
	
	@RequestMapping(value = "/registerforrestaurant", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRestaurant(@RequestParam String restaurantName) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(restaurantService.deleteRestaurant(restaurantName), HttpStatus.OK);
			    log.info("Restaurant details are deleted");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Restaurant details are not deleted");
		}

		return response;
		
	}
	
	@RequestMapping(value = "/allrestaurants", method = RequestMethod.GET)
	public ResponseEntity<?> getAllRestaurants() throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(restaurantService.getAllRestaurants(), HttpStatus.OK);
			    log.info("Restaurants List found");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Restaurants List not found");
		}

		return response;
		
	}
	
	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public ResponseEntity<?> getAllRestaurants(@RequestParam String location) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(restaurantService.getAllRestaurants(location), HttpStatus.OK);
			    log.info("Restaurants List found");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Restaurants List not found");
		}

		return response;
		
	}
	
	
	@RequestMapping(value = "/restaurantstatus", method = RequestMethod.PUT)
	public ResponseEntity<?> restaurantstatus(@RequestParam Status status,@RequestParam String restaurantName) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(restaurantService.restaurantstatus(status,restaurantName), HttpStatus.OK);
			    log.info("Restaurant Status changed");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Restaurant Status not changed");
		}

		return response;
		
	}
	
//	Setting All Restaurants Status as inactive
	
	@RequestMapping(value = "/allrestaurantstatus", method = RequestMethod.PUT)
	public ResponseEntity<?> allRestaurantstatus(@RequestParam String location,@RequestParam Status status) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(restaurantService.allRestaurantstatus(location,status), HttpStatus.OK);
			    log.info("Restaurant Status changed");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Restaurant Status not changed");
		}

		return response;
		
	}
	
	@RequestMapping(value = "/restaurantstatus", method = RequestMethod.GET)
	public ResponseEntity<?> restaurantstatus(@RequestParam String restaurantName) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(restaurantService.restaurantstatus(restaurantName), HttpStatus.OK);
			    log.info("Restaurant Status changed");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Restaurant Status not changed");
		}

		return response;
		
	}
	

	
	
	
}
