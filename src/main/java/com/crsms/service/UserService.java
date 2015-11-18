
package com.crsms.service;

import java.util.List;

import com.crsms.domain.User;
/**
 * 
 * @author Roman Romaniuk
 *
 */
public interface UserService extends BaseService<User> {

	User saveUser(User user);
	
	boolean changePassword(String email, String currentPassword, String newPassword);

	User getUserByEmail(String email);
	
	long getRowsCount();
	
	List<User> getPagingUsers(int startPosition, int itemsPerPage,
								String sortingField, String order);

}
