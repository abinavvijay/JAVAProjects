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

import com.infovision.canteen.dto.admin.AdminDto;
import com.infovision.canteen.dto.employee.ProfileDto;
import com.infovision.canteen.dto.restaurant.RestaurantItemDto;
import com.infovision.canteen.exception.ErrorHandler;
import com.infovision.canteen.model.admin.FoodType;
import com.infovision.canteen.service.EmployeeService;
//import com.infovision.canteen.serviceimpl.JwtUserDetailsService;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin("*")
@Log4j2
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
//	@Autowired
//	private JwtUserDetailsService userDetailsService;
//	
	
	@RequestMapping(value = "/login", method =  RequestMethod.GET)
	public ResponseEntity<?> login(@RequestParam String email,@RequestParam String password) {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(employeeService.login(email,password), HttpStatus.OK);
			    log.info("login success");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("login not success");
		}

		return response;

	}
	
//	@RequestMapping(value = "/forgotpassword", method =  RequestMethod.)
//	public ResponseEntity<?> forGotPassword(@RequestParam String email) {
//
//		ResponseEntity<?> response;
//
//		try {
//			response = new ResponseEntity<>(employeeService.forGotPassword(email), HttpStatus.OK);
////			userDetailsService.save(profileDto.getEmail(),profileDto.getPassword());
//			    log.info("login success");
//		} catch (Exception e) {
//			response = new ResponseEntity<ErrorHandler>(
//					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
//			 log.error("login not success");
//		}
//
//		return response;
//
//	}
	
	@RequestMapping(value = "/registerforemployee", method =  RequestMethod.POST)
	public ResponseEntity<?> addEmployee(@RequestBody ProfileDto profileDto) {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(employeeService.addEmployee(profileDto), HttpStatus.OK);
			    log.info("new employee is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new employee is not added");
		}

		return response;

	}
	
	@RequestMapping(value = "/registerforemployee", method = RequestMethod.PUT)
	public ResponseEntity<?> editEmployee(@RequestBody ProfileDto profileDto,@RequestParam UUID id,@RequestParam boolean email,@RequestParam boolean empId) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(employeeService.editEmployee(profileDto,id,email,empId), HttpStatus.OK);
			    log.info("Employee details are edited");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Employee details are not edited");
		}

		return response;
		
	}
	
	@RequestMapping(value = "/homepageview/menu", method = RequestMethod.GET)
	public ResponseEntity<?> homePage(@RequestParam FoodType foodType,@RequestParam String location) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(employeeService.homePage(foodType,location), HttpStatus.OK);
			    log.info("Employee landed in to homepage");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Employee not landed in to homepage");
		}

		return response;
		
	}
	
//	@RequestMapping(value = "/homepageview", method = RequestMethod.GET)
//	public ResponseEntity<?> homePageAll(@RequestParam String location) throws Exception {
//		
//		ResponseEntity<?> response;
//
//		try {
//			response = new ResponseEntity<>(employeeService.homePageAll(location), HttpStatus.OK);
//			    log.info("Employee landed in to homepage");
//		} catch (Exception e) {
//			response = new ResponseEntity<ErrorHandler>(
//					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
//			 log.error("Employee not landed in to homepage");
//		}
//
//		return response;
//		
//	}
	
	@RequestMapping(value = "/registerforemployee", method = RequestMethod.GET)
	public ResponseEntity<?> viewProfile(@RequestParam long empId) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(employeeService.viewProfile(empId), HttpStatus.OK);
			    log.info("Employee details are edited");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Employee details are not edited");
		}

		return response;
		
	}
}
