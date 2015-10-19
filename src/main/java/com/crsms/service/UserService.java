package com.crsms.service;

import java.util.Set;

import com.crsms.domain.User;

/**
 * 
 * @author Valerii Motresku
 *
 */

public interface UserService {
	
	void saveUser(User user);
		
	Set<User> getAllUser();
	
	User getUserById(Long id);

	void updateUser(User user);
	
	User getUser(String name);
}
