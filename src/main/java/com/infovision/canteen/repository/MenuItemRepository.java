package com.infovision.canteen.repository;

import java.awt.Menu;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.admin.MenuItem;
import com.infovision.canteen.model.employee.Employee;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, UUID>{

	@Query("select s from MenuItem s where s.admin.id=:id")
	List<MenuItem> getByAdmin(@Param("id")UUID id);


}
