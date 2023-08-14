package com.infovision.canteen.serviceimpl;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infovision.canteen.dto.restaurant.RestaurantProfileDto;
import com.infovision.canteen.exception.RestaurantException;
import com.infovision.canteen.model.cart.CartItem;
import com.infovision.canteen.model.feedback.ItemFeedback;
import com.infovision.canteen.model.order.OrderCartItem;
import com.infovision.canteen.model.restaurant.Restaurant;
import com.infovision.canteen.model.restaurant.RestaurantItem;
import com.infovision.canteen.model.restaurant.RestaurantProfile;
import com.infovision.canteen.model.restaurant.Status;
import com.infovision.canteen.repository.CartItemRepository;
import com.infovision.canteen.repository.ItemFeedbackRepository;
import com.infovision.canteen.repository.OrderCartItemRepository;
import com.infovision.canteen.repository.RestaurantItemRepository;
import com.infovision.canteen.repository.RestaurantProfileRepository;
import com.infovision.canteen.repository.RestaurantRepository;
import com.infovision.canteen.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private RestaurantProfileRepository restaurantProfileRepository;

	@Autowired
	private RestaurantItemRepository restaurantItemRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private ItemFeedbackRepository itemFeedbackRepository;

	@Autowired
	private OrderCartItemRepository orderCartItemRepository;

	@Override
	public RestaurantProfileDto addRestaurant(RestaurantProfileDto restaurantProfileDto)
			throws RestaurantException, IOException {
		// TODO Auto-generated method stub

		List<Restaurant> restaurants = restaurantRepository.findAll();

		if (restaurants.isEmpty())
			restaurant(restaurantProfileDto);
		else {
			for (Restaurant restaurant : restaurants) {
				if (restaurant.getRestaurantProfile().getRestaurantName()
						.equals(restaurantProfileDto.getRestaurantName())
						&& restaurant.getRestaurantProfile().getCity().equals(restaurantProfileDto.getCity())) {
					throw new RestaurantException(
							"Restaurant already exists with this name " + restaurantProfileDto.getRestaurantName());
				}
			}

			restaurant(restaurantProfileDto);
		}

		return restaurantProfileDto;
	}

	public void restaurant(RestaurantProfileDto restaurantProfileDto) {
		Restaurant restaurant = new Restaurant();

		RestaurantProfile restaurantProfile = new RestaurantProfile();

		restaurantProfile.setRestaurantName(restaurantProfileDto.getRestaurantName());
		restaurantProfile.setEmail(restaurantProfileDto.getEmail());
		restaurantProfile.setMobileNumber(restaurantProfileDto.getMobileNumber());
		restaurantProfile.setPassword(restaurantProfileDto.getPassword());
		restaurantProfile.setCountry(restaurantProfileDto.getCountry());
		restaurantProfile.setState(restaurantProfileDto.getState());
		restaurantProfile.setCity(restaurantProfileDto.getCity());
		restaurantProfile.setImageUrl(restaurantProfileDto.getImageUrl());
		restaurantProfile.setPincode(restaurantProfileDto.getPincode());
		
		restaurant.setRestaurantStatus(Status.ACTIVE);
		restaurant.setRestaurantProfile(restaurantProfile);

		restaurantProfileRepository.save(restaurantProfile);

		restaurantRepository.save(restaurant);
	}

	@Override
	public RestaurantProfileDto editRestaurant(RestaurantProfileDto restaurantProfileDto, UUID id,boolean restName)
			throws RestaurantException {
		// TODO Auto-generated method stub

		if (restaurantRepository.existsById(id)) {
			Restaurant restaurant = restaurantRepository.getOne(id);

			if(restName == true)
			{
			if (restaurant.getRestaurantProfile().getRestaurantName().equals(restaurantProfileDto.getRestaurantName())
					&& restaurant.getRestaurantProfile().getCity().equals(restaurantProfileDto.getCity())) {
				throw new RestaurantException(
						"Restaurant already exists with this name " + restaurantProfileDto.getRestaurantName());
			}
			restaurant.getRestaurantProfile().setRestaurantName(restaurantProfileDto.getRestaurantName());
			}

			restaurant.getRestaurantProfile().setCity(restaurantProfileDto.getCity());
			restaurant.getRestaurantProfile().setCountry(restaurantProfileDto.getCountry());
			restaurant.getRestaurantProfile().setEmail(restaurantProfileDto.getEmail());
			restaurant.getRestaurantProfile().setMobileNumber(restaurantProfileDto.getMobileNumber());
			restaurant.getRestaurantProfile().setPassword(restaurantProfileDto.getPassword());
			restaurant.getRestaurantProfile().setState(restaurantProfileDto.getState());
			restaurant.getRestaurantProfile().setImageUrl(restaurantProfileDto.getImageUrl());
			restaurant.getRestaurantProfile().setPincode(restaurantProfileDto.getPincode());
			restaurantRepository.save(restaurant);

		} else
			throw new RestaurantException("Restaurant Details not found");

		return restaurantProfileDto;
	}

	@Override
	public Restaurant viewRestaurant(String restaurantName) throws RestaurantException {
		// TODO Auto-generated method stub

		Restaurant restaurant = restaurantRepository.findByName(restaurantName);

		if (restaurant == null)
			throw new RestaurantException("Restaurant Details are not found");

		return restaurant;
	}

	@Override
	public String deleteRestaurant(String restaurantName) throws RestaurantException {
		// TODO Auto-generated method stub

		Restaurant restaurant = restaurantRepository.findByName(restaurantName);

		if (restaurant == null)
			throw new RestaurantException("Restaurant Details are not found");

		List<RestaurantItem> restaurantItems = restaurantItemRepository.findByRestaurant(restaurant.getRestaurantid());

		List<OrderCartItem> orderCartItems = orderCartItemRepository.getRestItems(restaurant.getRestaurantid());

		List<ItemFeedback> feedbackList = itemFeedbackRepository.findAll();

		if (feedbackList.isEmpty() == false) {
			for (ItemFeedback feedback : feedbackList) {
				if (feedback.getRestaurantItem().getRestaurant().getRestaurantid().equals(restaurant.getRestaurantid()))
					itemFeedbackRepository.delete(feedback.getFeedbackId());
			}
		}

		if (orderCartItems.isEmpty() == false)
			orderCartItemRepository.deleteInBatch(orderCartItems);

		List<CartItem> cartItems = cartItemRepository.getRestItems(restaurant.getRestaurantid());

		if (cartItems.isEmpty() == false)
			cartItemRepository.deleteInBatch(cartItems);

		restaurantItemRepository.deleteInBatch(restaurantItems);

		restaurantRepository.delete(restaurant);

		return "Restaurant is deleted";
	}

	@Override
	public List<Restaurant> getAllRestaurants() throws RestaurantException {
		// TODO Auto-generated method stub

		List<Restaurant> restaurants = restaurantRepository.findAll();

		if (restaurants.isEmpty())
			throw new RestaurantException("Restaurants List is empty");

		return restaurants;
	}

	@Override
	public Restaurant restaurantstatus(Status status, String restaurantName) throws RestaurantException {
		// TODO Auto-generated method stub

		Restaurant restaurant = restaurantRepository.findByName(restaurantName);

		if (restaurant == null)
			throw new RestaurantException("Restaurant Details are not found");

		restaurant.setRestaurantStatus(status);

		restaurantRepository.save(restaurant);

		return restaurant;
	}

	@Override
	public Status restaurantstatus(String restaurantName) throws RestaurantException {
		// TODO Auto-generated method stub

		Restaurant restaurant = restaurantRepository.findByName(restaurantName);

		if (restaurant == null)
			throw new RestaurantException("Restaurant Details are not found");

		return restaurant.getRestaurantStatus();
	}

	@Override
	public List<Restaurant> getAllRestaurants(String location) throws RestaurantException {
		// TODO Auto-generated method stub
		List<Restaurant> restaurants = restaurantRepository.findByLocation(location);

		if (restaurants.isEmpty())
			throw new RestaurantException("Restaurants List is empty");

		return restaurants;
	}

	@Override
	public String allRestaurantstatus(String location, Status status) throws RestaurantException {
		// TODO Auto-generated method stub

		List<Restaurant> restaurants = restaurantRepository.findByLocation(location);

		if (restaurants.isEmpty())
			throw new RestaurantException("Restaurants List is empty");

		for (Restaurant restaurant : restaurants) {
			restaurant.setRestaurantStatus(status);
			restaurantRepository.save(restaurant);

		}
		String s = "All Restaurants Status are setted as " + status;

		return s;
	}

	// View all Restaurant
	@Override
	public Object getRestaurant() {
		List<RestaurantProfile> restaurantProfileList = restaurantProfileRepository.findAll();
		List<RestaurantProfileDto> restaurantProfileDtos = new ArrayList<>();
		for (RestaurantProfile profile : restaurantProfileList) {
			restaurantProfileDtos.add(getRestaurantProfileDto(profile));
		}
		return restaurantProfileDtos;
	}

	// Delete a restaurant by id
	@Override
	public String removeRestaurant(UUID restaurantProfileId) throws Exception {
		Optional<RestaurantProfile> optionalRestaurantProfile = restaurantProfileRepository
				.findById(restaurantProfileId);
		if (!optionalRestaurantProfile.isPresent())
			throw new Exception("Restaurant profile is not available");
		RestaurantProfile restaurantProfile = optionalRestaurantProfile.get();
		restaurantProfileRepository.delete(restaurantProfile);
		return "Restaurant profile has been deleted";
	}

	// Function to get RestaurantProfileDto for view all RestaurantProfile
	public RestaurantProfileDto getRestaurantProfileDto(RestaurantProfile restaurantProfile) {
		RestaurantProfileDto restaurantProfileDto = new RestaurantProfileDto();
		restaurantProfileDto.setRestaurantName(restaurantProfile.getRestaurantName());
		restaurantProfileDto.setImageUrl(restaurantProfile.getImageUrl());
		restaurantProfileDto.setEmail(restaurantProfile.getEmail());
		restaurantProfileDto.setMobileNumber(restaurantProfile.getMobileNumber());
		restaurantProfileDto.setPassword(restaurantProfile.getPassword());
		restaurantProfileDto.setCountry(restaurantProfile.getCountry());
		restaurantProfileDto.setState(restaurantProfile.getState());
		restaurantProfileDto.setCity(restaurantProfile.getCity());
		return restaurantProfileDto;
	}

}
