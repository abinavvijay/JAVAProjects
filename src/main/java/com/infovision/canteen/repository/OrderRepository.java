package com.infovision.canteen.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.admin.MenuItem;
import com.infovision.canteen.model.order.OrderCartItem;
import com.infovision.canteen.model.order.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, UUID>{

	@Query("select s from Orders s where s.employee.empId=:empId")
	List<Orders> getByEmployee(@Param("empId")UUID empId);

	@Query("select s from Orders s where s.date=:localDate AND s.time<=:now AND s.delivery.deliveryId IS NULL AND s.employeeOrderStatus LIKE 'CONFIRM'")
	List<Orders> findByTIme(@Param("now")LocalTime now, @Param("localDate")LocalDate localDate);

	@Query("select s from Orders s where s.delivery.deliveryId=:deliveryId AND s.deliveryOrderStatus LIKE 'PENDING'")
	Orders getOrderById(@Param("deliveryId")UUID deliveryId);

	@Query("select s from Orders s where s.employee.empId=:empId")
	List<Orders> getOrders(@Param("empId")UUID empId);
	
	@Query("select s from Orders s where MONTH(s.date)=:value AND YEAR(s.date)=:year AND s.delivery.deliveryId=:deliveryId")
	List<Orders> getMonthOrderById(@Param("deliveryId")UUID deliveryId,@Param("value") int value, @Param("year")int year);

	@Query("select s from Orders s where s.date=:localDate AND s.delivery.deliveryId=:deliveryId AND s.deliveryOrderStatus LIKE 'DELIVERED'")
	List<Orders> dailyOrders(@Param("deliveryId")UUID deliveryId,@Param("localDate") LocalDate localDate);

	
}
