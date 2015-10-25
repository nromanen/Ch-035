package com.crsms.dao;

import java.util.List;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;

import com.crsms.domain.User;

public interface UserDao {
	

	User saveUser(User user);
	

	User getUserById(Long id);
	
	
	User getUserByEmail(String email);
	

	void delete(Long id);
	
	
	List<User> getAllUsers();
}