package com.crsms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.UserDao;
import com.crsms.domain.User;

@Service("userService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public User saveUser(User user) {
		return userDao.saveUser(user);
	}

	@Override
	@Transactional
	public User updateUser(User user) {
		return userDao.saveUser(user);
	}

	@Override
	public User getUserById(Long id) {
		return userDao.getUserById(id);
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		userDao.delete(id);

	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

}
