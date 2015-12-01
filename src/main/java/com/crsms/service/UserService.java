
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

	boolean isEmailExists(String email);
	
	User saveUser(User user);
	
	User saveStudent(User user);
	
	boolean changePassword(String email, String currentPassword, String newPassword);

	User getUserByEmail(String email);
	
	User getUserByEmail(String email, List<Invocable<User>> initializers);
	
	long getRowsCount(String keyWord);
	
	List<User> getPagingUsers(int offSet, int itemsPerPage,
							String sortingField, String order,String keyWord);
	
}
