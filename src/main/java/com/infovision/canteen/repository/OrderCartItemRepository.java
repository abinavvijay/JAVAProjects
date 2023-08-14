package com.infovision.canteen.repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.infovision.canteen.model.admin.MenuItem;
import com.infovision.canteen.model.order.EmployeeOrderStatus;
import com.infovision.canteen.model.order.OrderCartItem;
import com.infovision.canteen.model.order.TopSellingOrders;

@Repository
public interface OrderCartItemRepository extends JpaRepository<OrderCartItem, UUID>{

	@Query("select s from OrderCartItem s where s.cart.cartId=:cartId AND s.order.employeeOrderStatus = 'CONFIRM'")
	List<OrderCartItem> getByCart(@Param("cartId") UUID cartId);

	@Query("select s from OrderCartItem s where s.order.date=:now AND s.order.employeeOrderStatus LIKE 'CONFIRM'")
	List<OrderCartItem> getAll(@Param("now")LocalDate now);

	@Query("select s from OrderCartItem s where s.order.date=:now AND s.restaurantItem.restaurant.restaurantid=:restId AND s.order.employeeOrderStatus LIKE 'CONFIRM'")
	List<OrderCartItem> getByRestaurant(@Param("restId")UUID restId,@Param("now") LocalDate now);

	@Query("select new com.infovision.canteen.model.order.TopSellingOrders(s.restaurantItem.itemId, count(s)) from OrderCartItem s "
			+ "where s.order.date=:now AND s.order.employeeOrderStatus LIKE 'CONFIRM' GROUP BY s.restaurantItem.itemId")
	List<TopSellingOrders> getTopOrders(@Param("now")LocalDate now);

	@Query("select s from OrderCartItem s where s.order.date >=:date AND s.restaurantItem.restaurant.restaurantid=:restId AND s.order.employeeOrderStatus LIKE 'CONFIRM'")
	List<OrderCartItem> getWeekRevenue(@Param("restId")UUID restId,@Param("date")LocalDate date);

	@Query("select s from OrderCartItem s where s.order.date=:now AND s.order.employeeOrderStatus LIKE 'CONFIRM'")
	List<OrderCartItem> getDayAllRestRevenue(@Param("now")LocalDate now);

	@Query("select s from OrderCartItem s where s.order.date >=:minusDays AND s.order.employeeOrderStatus LIKE 'CONFIRM'")
	List<OrderCartItem> getWeekAllRestRevenue(@Param("minusDays")LocalDate minusDays);

	@Query("select s from OrderCartItem s where s.order.orderId=:orderId")
	List<OrderCartItem> findByOrderId(@Param("orderId")UUID orderId);

	@Query("select s from OrderCartItem s where MONTH(s.order.date)=:value AND YEAR(s.order.date)=:year AND s.restaurantItem.restaurant.restaurantid=:restId AND s.order.employeeOrderStatus LIKE 'CONFIRM'")
	List<OrderCartItem> getMonthRevenue(@Param("restId")UUID restId,@Param("value") int value,@Param("year") int year);

	@Query("select s from OrderCartItem s where MONTH(s.order.date)=:month AND YEAR(s.order.date)=:year AND s.order.employeeOrderStatus LIKE 'CONFIRM'")
	List<OrderCartItem> getMonthAllRestRevenue(@Param("month") int value,@Param("year") int year);

	@Query("select s from OrderCartItem s where s.restaurantItem.restaurant.restaurantid=:restId")
	List<OrderCartItem> getRestItems(@Param("restId")UUID restId);

	@Query("select s from OrderCartItem s where s.restaurantItem.itemName=:itemName")
	List<OrderCartItem> findByItemName(@Param("itemName")String itemName);

	

}
