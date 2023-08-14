package com.infovision.canteen.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.restaurant.RestaurantProfile;

@Repository
public interface RestaurantProfileRepository extends JpaRepository<RestaurantProfile, UUID>{

}
