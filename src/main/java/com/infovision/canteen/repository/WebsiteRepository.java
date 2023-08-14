package com.infovision.canteen.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.feedback.Feedback;
import com.infovision.canteen.model.feedback.Website;

@Repository
public interface WebsiteRepository extends JpaRepository<Website, UUID>{

	
	@Query("select s from Website s where s.websiteName=:string")
	Website getByName(@Param("string")String string);


}
