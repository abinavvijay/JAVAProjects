package com.infovision.canteen.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infovision.canteen.model.delivery.Address;
import com.infovision.canteen.model.order.TopSellingOrders;

public interface TopSellingOrdersRepository extends JpaRepository<TopSellingOrders, UUID>{
	

}
