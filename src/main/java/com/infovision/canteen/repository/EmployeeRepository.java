package com.infovision.canteen.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.admin.Admin;
import com.infovision.canteen.model.employee.Employee;

@Repository

public interface EmployeeRepository extends JpaRepository<Employee, UUID>{

	@Query("select s from Employee s where s.profile.mobileNumber=:mobNo")
	Employee findByMobNo(@Param("mobNo")long mobNo);

	@Query("select s from Employee s where s.profile.employeeId=:empId")
	Employee findByEmpId(@Param("empId")long empId);
	
	@Query("select s from Employee s where s.profile.email=:email AND s.profile.password=:password")
	Employee findByMail(@Param("email")String email,@Param("password")String password);

}
