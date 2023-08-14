package com.infovision.canteen.repository;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.restaurant.RestaurantItem;

@Repository
@Transactional
public interface RestaurantItemRepository extends JpaRepository<RestaurantItem, UUID>{

	@Query("select s from RestaurantItem s where s.restaurant.restaurantid=:restaurantid")
	List<RestaurantItem> findByRestaurant(@Param("restaurantid") UUID restaurantid);


	@Query("select s from RestaurantItem s where s.restaurant.restaurantid=:restId AND s.itemName=:itemName" )
	RestaurantItem getRestItem(@Param("restId")UUID restId,@Param("itemName")String itemName);


	@Modifying
	@Query("DELETE FROM RestaurantItem s WHERE s.itemId=:itemId")
	void deleteRestItem(@Param("itemId")UUID itemId);

}
