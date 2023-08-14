package com.infovision.canteen.dto.credentials;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.infovision.canteen.dto.admin.AdminDto;
import com.infovision.canteen.model.credentials.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredentialsDto {

	
	private String userName;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
}
