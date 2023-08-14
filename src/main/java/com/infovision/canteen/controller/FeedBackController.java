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
import com.infovision.canteen.service.FeedbackService;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin("*")
@Log4j2
public class FeedBackController {

	@Autowired
	private FeedbackService feedbackService;
	
	@RequestMapping(value = "/websitefeedback", method = RequestMethod.POST)
	public ResponseEntity<?> postWebsiteFeedback(@RequestParam UUID empId,@RequestParam String feedback ,@RequestParam double rating) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(feedbackService.postWebsiteFeedback(empId,feedback,rating), HttpStatus.OK);
			    log.info("Menu Items are found");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Menu Items are not found");
		}

		return response;
		
	}
	
	@RequestMapping(value = "/employeesfeedback", method = RequestMethod.GET)
	public ResponseEntity<?> getWebisteFeedback() throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(feedbackService.getWebisteFeedback(), HttpStatus.OK);
			    log.info("Menu Items are found");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Menu Items are not found");
		}

		return response;
		
	}
	
	@RequestMapping(value = "/orderfeedback", method = RequestMethod.POST)
	public ResponseEntity<?> postOrderFeedback(@RequestParam UUID empId,@RequestParam String feedback ,@RequestParam double rating,@RequestParam UUID itemId) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(feedbackService.postOrderFeedback(empId,feedback,rating,itemId), HttpStatus.OK);
			    log.info("Menu Items are found");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Menu Items are not found");
		}

		return response;
		
	}
	
	@RequestMapping(value = "/orderfeedback", method = RequestMethod.GET)
	public ResponseEntity<?> getOrderFeedback(@RequestParam UUID itemId) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(feedbackService.getOrderFeedback(itemId), HttpStatus.OK);
			    log.info("Menu Items are found");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Menu Items are not found");
		}

		return response;
		
	}

	
}
