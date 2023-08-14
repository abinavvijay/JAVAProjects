package com.infovision.canteen.repository;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.feedback.Feedback;
import com.infovision.canteen.model.feedback.ItemFeedback;

@Repository
@Transactional
public interface ItemFeedbackRepository extends JpaRepository<ItemFeedback, UUID>{

	@Query("select s from ItemFeedback s where s.restaurantItem.itemId=:itemId")
	List<ItemFeedback> findByItemId(@Param("itemId")UUID itemId);

	@Query("select s from ItemFeedback s where s.restaurantItem.restaurant.restaurantid=:restId")
	List<ItemFeedback> getRestItems(@Param("restId")UUID restId);

//	void delete(UUID feedbackId);

	@Modifying
	
	@Query("DELETE FROM ItemFeedback s WHERE s.feedbackId=:feedbackId")
	void delete(@Param("feedbackId")UUID feedbackId);

}
