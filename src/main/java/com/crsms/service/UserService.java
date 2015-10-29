package com.crsms.service;

import java.util.List;

import com.crsms.domain.User;
/**
 * 
 * @author Roman Romaniuk
 *
 */
public interface UserService {

	User createUser(String email, String password, long roleId);
	
	User saveUser(User user);
	
	User updateUser(User user);
	
	boolean changePassword(String email, String currentPassword, String newPassword);

	User getUserById(Long id);

	User getUserByEmail(String email);
	
//	@PreAuthorize("hasAnyRole('ROLE_STUDENT')")
	void delete(Long id);

	
	List<User> getAllUsers();
}
