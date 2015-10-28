package com.crsms.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.crsms.domain.Role;
import com.crsms.domain.User;
/**
 * 
 * @author Roman Romaniuk
 *
 */
public interface UserService {

	User saveUser(User user);

	User saveUser(User user, long roleId);
	
	User updateUser(User user, Role role);
	
	User updateUser(User user);

	User getUserById(Long id);

	User getUserByEmail(String email);
	
//	@PreAuthorize("hasAnyRole('ROLE_STUDENT')")
	void delete(Long id);

	
	List<User> getAllUsers();
}
