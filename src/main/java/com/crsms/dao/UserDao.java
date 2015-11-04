package com.crsms.dao;

import java.util.List;

import com.crsms.domain.User;

/**
 * 
 * @author Roman Romaniuk
 *
 */

public interface UserDao {

	User saveUser(User user);
	
	User getUserById(Long id);

	User getUserByEmail(String email);

	void delete(Long id);

	List<User> getAllUsers();
	
	long getRowsCount();
	
	List<User> getPagingUsers(int page, int itemsPerPage);
}