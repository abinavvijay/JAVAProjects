package com.infovision.canteen.service;

import java.util.List;
import java.util.UUID;

import com.infovision.canteen.dto.admin.AdminDto;
import com.infovision.canteen.dto.credentials.CredentialsDto;
import com.infovision.canteen.exception.AdminException;
import com.infovision.canteen.model.admin.Admin;
import com.infovision.canteen.model.credentials.Credentials;
import com.infovision.canteen.model.employee.Employee;


public interface AdminService {

	AdminDto addAdmin(AdminDto adminDto) throws Exception;

	AdminDto editAdmin(AdminDto adminDto, UUID id,boolean rt) throws Exception;

	List<Employee> getEmployeeList() throws Exception;

	CredentialsDto addCredentials(CredentialsDto credentialsDto) throws Exception;

	CredentialsDto editCredentials(CredentialsDto credentialsDto, UUID id,boolean rt) throws Exception;

	List<Credentials> getCredentialList() throws Exception;

	String deleteCredentials(String userName) throws Exception;



	Admin viewAdmin(String email) throws AdminException;

	String deleteAdmin(String email) throws AdminException;

}
