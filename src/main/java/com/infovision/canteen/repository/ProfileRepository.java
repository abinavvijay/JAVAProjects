package com.infovision.canteen.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.admin.Admin;
import com.infovision.canteen.model.employee.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID>{

}
