package com.crsms.dao;

import java.util.List;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;

import com.crsms.domain.User;

public interface UserDao {
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_TEACHER','ROLE_STUDENT')")
	User saveUser(User user);
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_TEACHER','ROLE_STUDENT')")
	User getUserById(Long id);
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_TEACHER','ROLE_STUDENT')")
	User getUserByEmail(String email);
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_TEACHER','ROLE_STUDENT')")
	void delete(Long id);
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_TEACHER','ROLE_STUDENT')")
	List<User> getAllUsers();
}