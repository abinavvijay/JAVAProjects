//package com.infovision.canteen.serviceimpl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.infovision.canteen.config.CustomUserDetail;
//import com.infovision.canteen.model.DAOUser;
//import com.infovision.canteen.repository.UserDao;
//
//
//
//
//
//
//@Service
//public class JwtUserDetailsService implements UserDetailsService {
//	
//	@Autowired
//	private UserDao userDao;
//
//	@Autowired
//	private PasswordEncoder bcryptEncoder;
//
//	@Override
//	public CustomUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
//		DAOUser user = userDao.findByUsername(username);
//		if (user == null) {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
//		
//		CustomUserDetail customUserDetail=new CustomUserDetail(user);
//		return customUserDetail;
//	}
//	
//	public DAOUser save(String email,String password) {
//		DAOUser newUser = new DAOUser();
//		newUser.setUsername(email);
//		newUser.setPassword(bcryptEncoder.encode(password));
//		return userDao.save(newUser);
//	}
//}