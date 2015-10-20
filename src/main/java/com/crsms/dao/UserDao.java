package com.crsms.dao;

import java.util.List;
import java.util.Set;

import com.crsms.domain.User;

public interface UserDao {

	User saveUser(User user);

	User getUserById(Long id);

	User getUserByEmail(String email);

	void delete(Long id);

	List<User> getAllUsers();
}