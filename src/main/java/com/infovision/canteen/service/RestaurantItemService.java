package com.infovision.canteen.service;

import java.util.List;
import java.util.UUID;

import com.infovision.canteen.dto.restaurant.RestaurantItemDto;
import com.infovision.canteen.exception.RestaurantItemException;
import com.infovision.canteen.model.restaurant.RestaurantItem;

public interface RestaurantItemService {

	List<RestaurantItemDto> addItem(List<RestaurantItemDto> restaurantItemDtos,UUID RestId) throws RestaurantItemException;

	RestaurantItemDto editItem(UUID id,RestaurantItemDto restaurantItemDto,boolean itemName) throws RestaurantItemException;

	List<RestaurantItem> getAllItems() throws RestaurantItemException;

	RestaurantItem getItem(UUID restId, String itemName) throws RestaurantItemException;

	List<RestaurantItem> getAllRestItems(UUID restId) throws RestaurantItemException;

	String deleteItem(UUID restId, String itemName) throws RestaurantItemException;

	String deleteRestItems(UUID restId) throws RestaurantItemException;

}
