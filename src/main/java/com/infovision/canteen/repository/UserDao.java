package com.infovision.canteen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.DAOUser;




@Repository
public interface UserDao extends JpaRepository<DAOUser, Long> {
	
	@Query("Select s from DAOUser s where s.username=:username")
	DAOUser findByUsername(String username);
	
}