package com.infovision.canteen.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.infovision.canteen.dto.restaurant.RestaurantItemDto;
import com.infovision.canteen.dto.restaurant.RestaurantProfileDto;
import com.infovision.canteen.exception.RestaurantException;
import com.infovision.canteen.model.restaurant.Restaurant;
import com.infovision.canteen.model.restaurant.Status;

public interface RestaurantService {

	RestaurantProfileDto addRestaurant(RestaurantProfileDto restaurantProfileDto) throws RestaurantException, IOException;

	RestaurantProfileDto editRestaurant(RestaurantProfileDto restaurantProfileDto, UUID id,boolean restName) throws RestaurantException;
	
	Object getRestaurant();

	String removeRestaurant(UUID restaurantProfileId) throws Exception;

	Restaurant viewRestaurant(String restaurantName) throws RestaurantException;

	String deleteRestaurant(String restaurantName)throws RestaurantException;

	List<Restaurant> getAllRestaurants() throws RestaurantException;

	Restaurant restaurantstatus(Status status,String restaurantName)throws RestaurantException;

	Status restaurantstatus(String restaurantName)throws RestaurantException;

	List<Restaurant> getAllRestaurants(String location) throws RestaurantException;

	String allRestaurantstatus(String location,Status status)throws RestaurantException;

}
