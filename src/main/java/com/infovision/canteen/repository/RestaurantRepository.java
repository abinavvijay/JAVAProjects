package com.infovision.canteen.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.DAOUser;
import com.infovision.canteen.model.restaurant.Restaurant;
import com.infovision.canteen.model.restaurant.RestaurantItem;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

	@Query("select s from Restaurant s where s.restaurantProfile.city=:location")
	List<Restaurant> findByLocation(@Param("location")String location);

	@Query("select s from Restaurant s where s.restaurantProfile.restaurantName=:restName")
	Restaurant findByName(@Param("restName")String restName);

	@Query("select s from RestaurantItem s where s.restaurant.restaurantProfile.restaurantName=:restName AND s.itemName=:itemName" )
	RestaurantItem findRestItem(@Param("itemName")String itemName,@Param("restName") String restName);

	@Query("select s from Restaurant s where s.restaurantProfile.city=:location" )
	List<Restaurant> findAll(@Param("location")String location);



}
