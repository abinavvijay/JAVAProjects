package com.infovision.canteen.serviceimpl;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infovision.canteen.exception.RestaurantException;
import com.infovision.canteen.exception.RevenueException;
import com.infovision.canteen.model.order.OrderCartItem;
import com.infovision.canteen.model.restaurant.Restaurant;
import com.infovision.canteen.repository.OrderCartItemRepository;
import com.infovision.canteen.repository.RestaurantItemRepository;
import com.infovision.canteen.repository.RestaurantRepository;
import com.infovision.canteen.service.RevenueService;

@Service
public class RevenueServiceImpl implements RevenueService {

	@Autowired
	private OrderCartItemRepository orderCartItemRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public double dayRevenue(UUID restId) throws RevenueException {
		// TODO Auto-generated method stub

		if (restaurantRepository.existsById(restId)) 
			{
			
			
		List<OrderCartItem> orders = orderCartItemRepository.getByRestaurant(restId, LocalDate.now());

		if (orders.isEmpty())
			throw new RevenueException("Orders not found");

		double revenue = 0;
		for (OrderCartItem OrderCartItem : orders) {
			revenue += OrderCartItem.getAmount();
		}

		return revenue;
		
			}
			else
				throw new RevenueException("Restaurant Details not found");
			
	}

	@Override
	public double weekRevenue(UUID restId) throws RevenueException {
		// TODO Auto-generated method stub
		LocalDate date = LocalDate.now().minusDays(7);
		
		if (restaurantRepository.existsById(restId)) 
		{

		List<OrderCartItem> orders = orderCartItemRepository.getWeekRevenue(restId, date);

		if (orders.isEmpty())
			throw new RevenueException("Orders not found");

		double revenue = 0;
		for (OrderCartItem OrderCartItem : orders) {
			revenue += OrderCartItem.getAmount();
		}

		return revenue;
		
		}
		else
			throw new RevenueException("Restaurant Details not found");

	}

	@Override
	public double monthRevenue(UUID restId) throws RevenueException {
		// TODO Auto-generated method stub

		if (restaurantRepository.existsById(restId)) 
		{
		List<OrderCartItem> orders = orderCartItemRepository.getMonthRevenue(restId,
				LocalDate.now().getMonth().getValue(), LocalDate.now().getYear());

		if (orders.isEmpty())
			throw new RevenueException("Orders not found");

		double revenue = 0;
		for (OrderCartItem OrderCartItem : orders) {
			revenue += OrderCartItem.getAmount();
		}

		return revenue;
		}
		else
			throw new RevenueException("Restaurant Details not found");
	}

	@Override
	public double dayAllRestRevenue() throws RevenueException {
		// TODO Auto-generated method stub

		List<OrderCartItem> orders = orderCartItemRepository.getDayAllRestRevenue(LocalDate.now());

		if (orders.isEmpty())
			throw new RevenueException("Orders not found");

		double revenue = 0;

		for (OrderCartItem OrderCartItem : orders) {
			revenue += OrderCartItem.getAmount();
		}

		return revenue;
	}

	@Override
	public double weekAllRestRevenue() throws RevenueException {
		// TODO Auto-generated method stub

		List<OrderCartItem> orders = orderCartItemRepository.getWeekAllRestRevenue(LocalDate.now().minusDays(7));

		if (orders.isEmpty())
			throw new RevenueException("Orders not found");

		double revenue = 0;

		for (OrderCartItem OrderCartItem : orders) {
			revenue += OrderCartItem.getAmount();
		}

		return revenue;
	}

	@Override
	public double monthAllRestRevenue() throws RevenueException {
		// TODO Auto-generated method stub

		List<OrderCartItem> orders = orderCartItemRepository.getMonthAllRestRevenue(LocalDate.now().getMonth().getValue(),
				LocalDate.now().getYear());

		if (orders.isEmpty())
			throw new RevenueException("Orders not found");

		double revenue = 0;

		for (OrderCartItem OrderCartItem : orders) {
			revenue += OrderCartItem.getAmount();
		}

		return revenue;

	}

}
