package com.infovision.canteen.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infovision.canteen.exception.ErrorHandler;
import com.infovision.canteen.service.CartService;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin("*")
@Log4j2
public class CartController {

	@Autowired
	private CartService cartService;
	
	@RequestMapping(value = "/cart", method =  RequestMethod.POST)
	public ResponseEntity<?> addToCart(@RequestParam UUID itemId,@RequestParam UUID empId,@RequestParam int quantity) {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<String>(cartService.addToCart(itemId,empId,quantity), HttpStatus.OK);
			    log.info("new employee is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new employee is not added");
		}

		return response;

	}
	
	@RequestMapping(value = "/cart", method =  RequestMethod.GET)
	public ResponseEntity<?> viewCartItems(@RequestParam UUID cartId) {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(cartService.viewCartItems(cartId), HttpStatus.OK);
			    log.info("new employee is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new employee is not added");
		}

		return response;

	}
	
	@RequestMapping(value = "/cart", method =  RequestMethod.PUT)
	public ResponseEntity<?> editCartItems(@RequestParam UUID itemId,@RequestParam UUID cartId,@RequestParam int quantity) {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(cartService.editCartItems(itemId,cartId,quantity), HttpStatus.OK);
			    log.info("new employee is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new employee is not added");
		}

		return response;

	}
	
	@RequestMapping(value = "/cart", method =  RequestMethod.DELETE)
	public ResponseEntity<?> deleteCartItems(@RequestParam UUID itemId,@RequestParam UUID cartId) {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<String>(cartService.deleteCartItems(itemId,cartId), HttpStatus.OK);
			    log.info("new employee is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new employee is not added");
		}

		return response;

	}
}
