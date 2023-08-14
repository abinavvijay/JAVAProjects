package com.infovision.canteen.repository;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.admin.MenuItem;
import com.infovision.canteen.model.feedback.Feedback;

@Repository

public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {

	@Query("select s from Feedback s where s.website.websiteName=:string")
	List<Feedback> findAllByName(@Param("string")String string);

//	@Query("select s from Feedback s where s.restaurantItem.itemId=:itemId")
//	List<Feedback> findByItemId(UUID itemId);
//
//	@Modifying
//	@Query("DELETE FROM Feedback s WHERE s.restaurantItem.restaurant.restaurantid=:restId")
//	void deleteRestItems(UUID restId);
//
//	@Query(value="select s from Feedback s where s.restaurantItem.restaurant.restaurantid=:restId",nativeQuery = true)
//	List<Feedback> getRestItems(UUID restId);

	@Modifying
	@org.springframework.transaction.annotation.Transactional
	@Query("DELETE FROM Feedback s WHERE s.feedbackId=:feedbackId")
	void delete(@Param("feedbackId")UUID feedbackId);

}
