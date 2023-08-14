package com.infovision.canteen.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.infovision.canteen.dto.delivery.DeliveryProfileDto;
import com.infovision.canteen.model.delivery.Delivery;
import com.infovision.canteen.model.delivery.DeliveryPersonStatus;
import com.infovision.canteen.model.order.Orders;

public interface DeliveryService {

	String login(String userName, String password) throws Exception;

	String updateDeliveryPersonStatus(UUID deliveryId,DeliveryPersonStatus status) throws Exception;

	Delivery addDeliveryBoy(DeliveryProfileDto deliveryProfileDto,UUID deliveryId) throws Exception;

	DeliveryProfileDto editDeliveryBoy(DeliveryProfileDto deliveryProfileDto, UUID deliveryId) throws Exception;

	Optional<Delivery> viewDeliveryProfileById(UUID deliveryId) throws Exception;

	Orders getDeliveryOrder(UUID deliveryId) throws Exception;

	List<Orders> getMonthDeliveryOrder(UUID deliveryId) throws Exception;

	String workingStatus(UUID deliveryId)throws Exception;

	List<Orders> dailyDeliveryOrders(UUID deliveryId) throws Exception;

	double monthRevenue(UUID deliveryId) throws Exception;

}
