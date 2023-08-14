package com.infovision.canteen.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infovision.canteen.dto.restaurant.RestaurantItemDto;
import com.infovision.canteen.exception.RestaurantItemException;
import com.infovision.canteen.model.cart.CartItem;
import com.infovision.canteen.model.feedback.Feedback;
import com.infovision.canteen.model.feedback.ItemFeedback;
import com.infovision.canteen.model.order.OrderCartItem;
import com.infovision.canteen.model.restaurant.ItemStatus;
import com.infovision.canteen.model.restaurant.Restaurant;
import com.infovision.canteen.model.restaurant.RestaurantItem;
import com.infovision.canteen.repository.CartItemRepository;
import com.infovision.canteen.repository.FeedbackRepository;
import com.infovision.canteen.repository.ItemFeedbackRepository;
import com.infovision.canteen.repository.OrderCartItemRepository;
import com.infovision.canteen.repository.RestaurantItemRepository;
import com.infovision.canteen.repository.RestaurantRepository;
import com.infovision.canteen.service.RestaurantItemService;

@Service
public class RestaurantItemServiceImpl implements RestaurantItemService {

	@Autowired
	private RestaurantItemRepository restaurantItemRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private ItemFeedbackRepository itemFeedbackRepository;
	
	@Autowired
	private OrderCartItemRepository orderCartItemRepository;

	@Override
	public List<RestaurantItemDto> addItem(List<RestaurantItemDto> restaurantItemDtos, UUID RestId)
			throws RestaurantItemException {
		// TODO Auto-generated method stub

		Restaurant restaurant = restaurantRepository.getOne(RestId);
 
		if (restaurantRepository.existsById(RestId)) {
			
		List<RestaurantItem> restItems=restaurantItemRepository.findByRestaurant(RestId);
		
		if(restItems.isEmpty())
		{
			for (RestaurantItemDto restaurantItemDto : restaurantItemDtos) {
				restItem( restaurantItemDto ,restaurant);
			}
		}
		else
		{
			for (RestaurantItemDto restaurantItemDto : restaurantItemDtos) {
			
				int count=0;
				
			for(RestaurantItem restaurantItem:restItems)
			{
				if(restaurantItem.getItemName().equals(restaurantItemDto.getItemName()))
				{
					count++;
					break;
				}
			}
			if(count==0)
			 restItem( restaurantItemDto ,restaurant);
			
			}
		}

		} else
			throw new RestaurantItemException("Restaurant details not found");


		return restaurantItemDtos;
	}
	
	public void restItem(RestaurantItemDto restaurantItemDto,Restaurant restaurant)
	{
		
		RestaurantItem restaurantItem = new RestaurantItem();

		restaurantItem.setItemDesc(restaurantItemDto.getItemDesc());
		restaurantItem.setItemName(restaurantItemDto.getItemName());
		restaurantItem.setItemprice(restaurantItemDto.getItemprice());
		restaurantItem.setRating(restaurantItemDto.getRating());
		restaurantItem.setRestaurant(restaurant);
		restaurantItem.setStatus(ItemStatus.AVAILABLE);
		restaurantItem.setImageUrl(restaurantItemDto.getImageUrl());

		restaurantItem.setRestaurant(restaurant);
		restaurantItemRepository.save(restaurantItem);
		
		
	}

	@Override
	public RestaurantItemDto editItem(UUID id, RestaurantItemDto restaurantItemDto,boolean itemName) throws RestaurantItemException {
		// TODO Auto-generated method stub

			if (restaurantItemRepository.existsById(id) ){
				RestaurantItem restaurantItem = restaurantItemRepository.getOne(id);

				if(itemName ==true)
				{
					if(restaurantItem.getItemName().equals(restaurantItemDto.getItemName()))
						throw new RestaurantItemException("ItemName already exits");
					
					restaurantItem.setItemName(restaurantItem.getItemName());
				}
				restaurantItem.setItemDesc(restaurantItemDto.getItemDesc());
				restaurantItem.setItemprice(restaurantItemDto.getItemprice());
				restaurantItem.setRating(restaurantItemDto.getRating());
				restaurantItem.setStatus(ItemStatus.AVAILABLE);
				restaurantItem.setImageUrl(restaurantItemDto.getImageUrl());

				restaurantItemRepository.save(restaurantItem);

			}
			else
				throw new RestaurantItemException("RestItem not found");

		return restaurantItemDto;
	}

	@Override
	public List<RestaurantItem> getAllItems() throws RestaurantItemException {
		// TODO Auto-generated method stub

		List<RestaurantItem> restaurantItems = restaurantItemRepository.findAll();

		if (restaurantItems.isEmpty())
			throw new RestaurantItemException("ResturantItems are empty,pls add");

		return restaurantItems;
	}

	@Override
	public RestaurantItem getItem(UUID restId, String itemName) throws RestaurantItemException {
		// TODO Auto-generated method stub

		if (restaurantRepository.existsById(restId)) {
			RestaurantItem restaurantItem = restaurantItemRepository.getRestItem(restId, itemName);

			if (restaurantItem == null)
				throw new RestaurantItemException("Restaurant Item not found");

			return restaurantItem;

		} else
			throw new RestaurantItemException("Restaurant not found");

	}

	@Override
	public List<RestaurantItem> getAllRestItems(UUID restId) throws RestaurantItemException {
		// TODO Auto-generated method stub
		if (restaurantRepository.existsById(restId)) {

			List<RestaurantItem> restaurantItems = restaurantItemRepository.findByRestaurant(restId);

			if (restaurantItems.isEmpty())
				throw new RestaurantItemException("ResturantItems are empty,pls add");

			return restaurantItems;
		} else
			throw new RestaurantItemException("Restaurant not found");

	}

	@Override
	@Transactional
	public String deleteItem(UUID restId, String itemName) throws RestaurantItemException {
		// TODO Auto-generated method stub

		if (restaurantRepository.existsById(restId)) {

			List<RestaurantItem> restaurantItems = restaurantItemRepository.findByRestaurant(restId);

			if (restaurantItems.isEmpty())
				throw new RestaurantItemException("ResturantItems are empty,pls add");

			List<ItemFeedback> feedbackList = itemFeedbackRepository.findAll();

			if (feedbackList.isEmpty() == false) {
				for (ItemFeedback feedback : feedbackList) {
					if (feedback.getRestaurantItem().getRestaurant().getRestaurantid().equals(restId) && 
							feedback.getRestaurantItem().getItemName().equals(itemName))
					{
						itemFeedbackRepository.delete(feedback.getFeedbackId());
						break;
					}
				}
			}
			
			List<OrderCartItem> orderCartItems=orderCartItemRepository.findByItemName(itemName);
			
			if (orderCartItems.isEmpty() == false)
				orderCartItemRepository.deleteInBatch(orderCartItems);
			
			List<CartItem> cartItems = cartItemRepository.findByItemName(itemName);

			if (cartItems.isEmpty() == false)
				cartItemRepository.deleteInBatch(cartItems);

			for (RestaurantItem restaurantItem : restaurantItems) {

				if (restaurantItem.getItemName().equals(itemName) && restaurantItem.getRestaurant().getRestaurantid().equals(restId)) {
					restaurantItemRepository.deleteRestItem(restaurantItem.getItemId());
					return "Item is deleted";
				}
			}

			throw new RestaurantItemException("RestaurantItem is not found");

		} else
			throw new RestaurantItemException("Restaurant not found");

	}

	@Override
	@Transactional
	public String deleteRestItems(UUID restId) throws RestaurantItemException {
		// TODO Auto-generated method stub

		if (restaurantRepository.existsById(restId)) {

			List<RestaurantItem> restaurantItems = restaurantItemRepository.findByRestaurant(restId);

			if (restaurantItems.isEmpty())
				throw new RestaurantItemException("ResturantItems are empty,pls add");

			List<OrderCartItem> orderCartItems = orderCartItemRepository.getRestItems(restId);
			

			List<ItemFeedback> feedbackList = itemFeedbackRepository.findAll();

			if (feedbackList.isEmpty() == false) {
				for (ItemFeedback feedback : feedbackList) {
					if (feedback.getRestaurantItem().getRestaurant().getRestaurantid().equals(restId))
						itemFeedbackRepository.delete(feedback.getFeedbackId());
				}
			}
			
			if (orderCartItems.isEmpty() == false)
				orderCartItemRepository.deleteInBatch(orderCartItems);
			
			List<CartItem> cartItems = cartItemRepository.getRestItems(restId);

			if (cartItems.isEmpty() == false)
				cartItemRepository.deleteInBatch(cartItems);
			
			restaurantItemRepository.deleteInBatch(restaurantItems);

			return "Items are deleted";

		} else
			throw new RestaurantItemException("Restaurant not found");

	}

}
