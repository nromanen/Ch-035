
package com.crsms.service;

import java.util.List;

import com.crsms.domain.User;
/**
 * 
 * @author Roman Romaniuk
 *
 */
public interface UserService {

	boolean isEmailExists(String email);
	
	User saveUser(User user);
	
	User updateUser(User user);
	
	boolean changePassword(String email, String currentPassword, String newPassword);

	User getUserById(Long id);

	User getUserByEmail(String email);
	
	void delete(Long id);

	List<User> getAllUsers();
	
	long getRowsCount();
	
	List<User> getPagingUsers(int startPosition, int itemsPerPage, String sortingField, String order);

	List<User> getAllWithInitializedCourses();

}
