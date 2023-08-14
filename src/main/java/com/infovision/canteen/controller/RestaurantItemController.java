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
import com.infovision.canteen.repository.RestaurantProfileRepository;
import com.infovision.canteen.service.RestaurantItemService;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin("*")
@Log4j2
public class RestaurantItemController {

	@Autowired
	private RestaurantItemService restaurantItemService;
	
	
	@RequestMapping(value = "/item", method =  RequestMethod.POST)
	public ResponseEntity<?> addItem(@RequestBody List<RestaurantItemDto> restaurantItemDtos,@RequestParam UUID RestId) {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(restaurantItemService.addItem(restaurantItemDtos,RestId), HttpStatus.OK);
			    log.info("new Restaurant is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Restaurant is not added");
		}

		return response;

	}
	
	@RequestMapping(value = "/item", method =  RequestMethod.PUT)
	public ResponseEntity<?> editItem(@RequestParam UUID id,@RequestBody RestaurantItemDto restaurantItemDto,@RequestParam boolean itemName) {
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(restaurantItemService.editItem(id,restaurantItemDto,itemName), HttpStatus.OK);
			    log.info("new Restaurant is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Restaurant is not added");
		}

		return response;

	}
	
	@RequestMapping(value = "/allitems", method =  RequestMethod.GET)
	public ResponseEntity<?> getAllItems() {
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(restaurantItemService.getAllItems(), HttpStatus.OK);
			    log.info("new Restaurant is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Restaurant is not added");
		}

		return response;

	}
	
	@RequestMapping(value = "/item", method =  RequestMethod.GET)
	public ResponseEntity<?> getItem(@RequestParam UUID RestId,@RequestParam String itemName) {
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(restaurantItemService.getItem(RestId,itemName), HttpStatus.OK);
			    log.info("new Restaurant is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Restaurant is not added");
		}

		return response;

	}
//	we can use it homepage
	
	@RequestMapping(value = "/restaurantitem", method =  RequestMethod.GET)
	public ResponseEntity<?> getAllRestItems(@RequestParam UUID RestId) {
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(restaurantItemService.getAllRestItems(RestId), HttpStatus.OK);
			    log.info("new Restaurant is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Restaurant is not added");
		}

		return response;

	}
	
	@RequestMapping(value = "/item", method =  RequestMethod.DELETE)
	public ResponseEntity<?> deleteItem(@RequestParam UUID RestId,@RequestParam String  itemName) {
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(restaurantItemService.deleteItem(RestId,itemName), HttpStatus.OK);
			    log.info("new Restaurant is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Restaurant is not added");
		}

		return response;

	}
	
	@RequestMapping(value = "/restaurantitem", method =  RequestMethod.DELETE)
	public ResponseEntity<?> deleteRestItems(@RequestParam UUID RestId) {
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(restaurantItemService.deleteRestItems(RestId), HttpStatus.OK);
			    log.info("new Restaurant is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Restaurant is not added");
		}

		return response;

	}
	
	
}
