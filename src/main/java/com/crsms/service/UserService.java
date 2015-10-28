package com.crsms.service;

import java.util.List;

import com.crsms.domain.Role;
import com.crsms.domain.User;
/**
 * 
 * @author Roman Romaniuk
 *
 */
public interface UserService {

	User createUser(String email, String password, long roleId);
	
	User saveUser(User user);

	User saveUser(User user, long roleId);
	
	User updateUser(User user, Role role);
	
	User updateUser(User user);
	
	boolean changePassword(String email, String currentPassword, String newPassword);

	User getUserById(Long id);

	User getUserByEmail(String email);
	
//	@PreAuthorize("hasAnyRole('ROLE_STUDENT')")
	void delete(Long id);

	
	List<User> getAllUsers();
}
