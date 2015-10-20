package com.crsms.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.crsms.domain.User;

public interface UserService {

	User saveUser(User user);

	User updateUser(User user);

	User getUserById(Long id);

	User getUserByEmail(String email);

	void delete(Long id);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	List<User> getAllUsers();
}
