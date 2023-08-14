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
import com.infovision.canteen.dto.admin.EditMenuItemDto;
import com.infovision.canteen.dto.admin.MenuItemDto;
import com.infovision.canteen.exception.ErrorHandler;
import com.infovision.canteen.service.EmployeeService;
import com.infovision.canteen.service.MenuService;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin("*")
@Log4j2
public class MenuItemController {

	@Autowired
	private MenuService menuService;
	
	
	@RequestMapping(value = "/menuitems", method = RequestMethod.POST)
	public ResponseEntity<?> addMenuItems(@RequestBody List<MenuItemDto> menuItemDtos,@RequestParam UUID adminId) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(menuService.addMenuItems(menuItemDtos,adminId), HttpStatus.OK);
			    log.info("Menu Items are added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Menu Items are not added");
		}

		return response;
		
	}
	
	
	@RequestMapping(value = "/menuitems", method = RequestMethod.PUT)
	public ResponseEntity<?> editMenuItem(@RequestBody EditMenuItemDto editMenuItemDto,@RequestParam UUID adminId) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(menuService.editMenuItem(editMenuItemDto,adminId), HttpStatus.OK);
			    log.info("Menu Item is edited");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Menu Item is not edited");
		}

		return response;
		
	}
	
	@RequestMapping(value = "/menuitems", method = RequestMethod.GET)
	public ResponseEntity<?> viewMenuItems(@RequestParam UUID adminId) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(menuService.viewMenuItems(adminId), HttpStatus.OK);
			    log.info("Menu Items are found");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Menu Items are not found");
		}

		return response;
		
	}
	
	@RequestMapping(value = "/menuitems", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteMenuItem(@RequestParam UUID adminId,@RequestParam UUID menuItemId) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(menuService.deleteMenuItem(adminId,menuItemId), HttpStatus.OK);
			    log.info("Menu Items are found");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("Menu Items are not found");
		}

		return response;
		
	}
}
