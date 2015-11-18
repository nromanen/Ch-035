
package com.crsms.service;

import java.util.List;

import com.crsms.domain.User;
import com.crsms.util.Invocable;
/**
 * 
 * @author Roman Romaniuk
 *
 */
public interface UserService extends BaseService<User> {

	User saveUser(User user);
	
	boolean changePassword(String email, String currentPassword, String newPassword);

	User getUserByEmail(String email);
	
	User getUserByEmail(String email, List<Invocable<User>> initializers);
	
	long getRowsCount();
	
	List<User> getPagingUsers(int startPosition, int itemsPerPage,
								String sortingField, String order);

}
