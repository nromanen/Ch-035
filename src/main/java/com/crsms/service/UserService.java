
package com.crsms.service;

import java.util.List;

import com.crsms.domain.User;
import com.crsms.util.Invocable;
/**
 * 
 * @author Roman Romaniuk
 *
 */
public interface UserService {

	User saveUser(User user);
	
	User updateUser(User user);
	
	boolean changePassword(String email, String currentPassword, String newPassword);

	User getUserById(Long id);

	User getUserByEmail(String email);
	
	User getUserByEmail(String email, List<Invocable<User>> initializers);
	
	void delete(Long id);

	List<User> getAllUsers();
	
	long getRowsCount();
	
	List<User> getPagingUsers(int startPosition, int itemsPerPage,
								String sortingField, String order);

}
