package com.infovision.canteen.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.dto.credentials.CredentialsDto;
import com.infovision.canteen.model.credentials.Credentials;
@Repository
public interface CredentialsRepository  extends JpaRepository<Credentials, UUID>{

	@Query("SELECT d FROM Credentials d WHERE d.userName=:userName")
	Credentials findByuserName(@Param("userName")String userName);

}
