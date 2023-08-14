package com.infovision.canteen.serviceimpl;

import java.security.MessageDigest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infovision.canteen.dto.admin.AdminDto;
import com.infovision.canteen.dto.credentials.CredentialsDto;
import com.infovision.canteen.exception.AdminException;
import com.infovision.canteen.model.admin.Admin;
import com.infovision.canteen.model.admin.MenuItem;
import com.infovision.canteen.model.credentials.Credentials;
import com.infovision.canteen.model.credentials.Role;
import com.infovision.canteen.model.delivery.Address;
import com.infovision.canteen.model.delivery.Delivery;
import com.infovision.canteen.model.employee.Employee;
import com.infovision.canteen.repository.AdminRepository;
import com.infovision.canteen.repository.CredentialsRepository;
import com.infovision.canteen.repository.DeliveryRepository;
import com.infovision.canteen.repository.EmployeeRepository;
import com.infovision.canteen.repository.MenuItemRepository;
import com.infovision.canteen.service.AdminService;

@Service
public class AdminserviceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CredentialsRepository credentialsRepository;

	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Autowired
	private MenuItemRepository menuItemRepository;

	@Override
	public AdminDto addAdmin(AdminDto adminDto) throws Exception {
		// TODO Auto-generated method stub
		List<Admin> admins=adminRepository.findAll();
		
		if(admins.isEmpty())
		{
		  admin(adminDto);
		}
		else
		{
			for(Admin admin:admins)
			{
				if(admin.getEmail().equals(adminDto.getEmail()))
				{
					throw new Exception("Email already exists");
				}
			}
			
			admin(adminDto);
			
		}

		return adminDto;
	}
	
	public void admin(AdminDto adminDto) throws Exception
	{
		Admin admin = new Admin();

		admin.setCity(adminDto.getCity());
		admin.setState(adminDto.getState());
		admin.setCountry(adminDto.getCountry());
		admin.setEmail(adminDto.getEmail());
		admin.setFirstName(adminDto.getFirstName());
		admin.setGender(adminDto.getGender());
		admin.setLastName(adminDto.getLastName());
		admin.setMobileNumber(adminDto.getMobileNumber());
		admin.setPassword(adminDto.getPassword());
		admin.setImageUrl(adminDto.getImageUrl());
		adminRepository.save(admin);

	}

	public AdminDto editAdmin(AdminDto adminDto, UUID id,boolean rt) throws Exception {
		// TODO Auto-generated method stub

		if (adminRepository.existsById(id)) {
			Admin admin = adminRepository.getOne(id);

			if(rt == true)
			{
			if(admin.getEmail().equals(adminDto.getEmail()))
			{
				throw new Exception("Email already exists");
			}
			admin.setEmail(adminDto.getEmail());
			}
			
			admin.setCity(adminDto.getCity());
			admin.setCountry(adminDto.getCountry());
			admin.setFirstName(adminDto.getFirstName());
			admin.setGender(adminDto.getGender());
			admin.setLastName(adminDto.getLastName());
			admin.setMobileNumber(adminDto.getMobileNumber());
			admin.setState(adminDto.getState());
			admin.setImageUrl(adminDto.getImageUrl());

			adminRepository.save(admin);

		} else
			throw new Exception("Admin Id not found");

		return adminDto;
	}

	@Override
	public Admin viewAdmin(String email) throws AdminException {
		// TODO Auto-generated method stub

		Admin admin = adminRepository.findByEmail(email);

		if (admin == null)
			throw new AdminException("Admin Details not found");

		return admin;
	}

	@Override
	@javax.transaction.Transactional
	public String deleteAdmin(String email) throws AdminException {
		// TODO Auto-generated method stub

		Admin admin = adminRepository.findByEmail(email);

		if (admin == null)
			throw new AdminException("Admin Details not found");
		
		List<MenuItem> menuItems=menuItemRepository.getByAdmin(admin.getId());
		
		if(menuItems.isEmpty()== false)
			menuItemRepository.deleteInBatch(menuItems);

		adminRepository.deleteAdmin(admin.getId());

		return "Admin Details are deleted";
	}

	@Override
	public List<Employee> getEmployeeList() throws Exception {
		List<Employee> employee = employeeRepository.findAll();

		if (employee.isEmpty()) {
			throw new Exception("employee list is empty");
		}

		return employee;
	}

	@Override
	public CredentialsDto addCredentials(CredentialsDto credentialsDto) throws Exception {

		List<Credentials> credentials=credentialsRepository.findAll();
		
		if(credentials.isEmpty())
		{
			credential(credentialsDto);
		}

		else
		{
			for(Credentials credential:credentials)
			{
				if(credential.getUserName().equals(credentialsDto.getUserName()))
					throw new Exception("UserName Already exists");
			}
			credential(credentialsDto);
			
		}
		return credentialsDto;

	}
	
	public void credential(CredentialsDto credentialsDto)
	{
		Credentials credential = new Credentials();

		credential.setUserName(credentialsDto.getUserName());
		credential.setRole(credentialsDto.getRole());
		credential.setPassword(credentialsDto.getPassword());

		credentialsRepository.save(credential);

		if (credentialsDto.getRole().equals(Role.ROLE_DELIVERY)) {
			Delivery delivery = new Delivery();

			Address address = new Address();

			delivery.setCredentials(credential);
			delivery.setAddress(address);
			deliveryRepository.save(delivery);

		}
	}

	@Override
	public CredentialsDto editCredentials(CredentialsDto credentialsDto, UUID id,boolean rt) throws Exception {

		if (credentialsRepository.existsById(id)) {

			Credentials credentials = credentialsRepository.getOne(id);

			if(rt==true)
			{
			if (credentials.getUserName().equals(credentialsDto.getUserName()))
				throw new Exception("User already existed");

			credentials.setUserName(credentialsDto.getUserName());
			}
			credentials.setPassword(credentialsDto.getPassword());
//			credentials.setRole(credentialsDto.getRole());
			credentialsRepository.save(credentials);

			return credentialsDto;
		}
		throw new Exception("credentials not found");
	}

	@Override
	public List<Credentials> getCredentialList() throws Exception {

		List<Credentials> credentials = credentialsRepository.findAll();

		if (credentials.isEmpty()) {
			throw new Exception("credentials list is empty");
		}

		return credentials;

	}

	@Override
	public String deleteCredentials(String userName) throws Exception {

		Credentials credentials = credentialsRepository.findByuserName(userName);

		if(credentials == null)
			throw new Exception("credentials not found");
		
		if (credentials.getUserName().equals(userName) && credentials.getRole().equals(Role.ROLE_DELIVERY)) {

			Delivery delivery = deliveryRepository.getCredential(credentials.getCredentialId());
			deliveryRepository.delete(delivery);
			credentialsRepository.delete(credentials);

		} 
		else if(credentials.getUserName().equals(userName)) {
			credentialsRepository.delete(credentials);
		}
			else {
			throw new Exception("credentials not found");
		}

		return "deleted";

	}
}
