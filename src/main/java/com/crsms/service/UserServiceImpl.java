package com.crsms.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.RoleDao;
import com.crsms.dao.UserDao;
import com.crsms.domain.User;

/**
 * 
 * @author Roman Romaniuk
 *
 */
@Service("userService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	private static Logger log = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	
	@Override
	@Transactional
	public User createUser(String email, String password, long roleId) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(roleDao.getRoleById(roleId));
		
		return userDao.saveUser(user);
	}

	@Override
	@Transactional
	public User saveUser(User user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userDao.saveUser(user);
		} catch (Exception e) {
			log.error("Error save user: " + e);
		}
		return user;
	}

	@Override
	@Transactional
	public User updateUser(User user) {
		try {
			log.debug("updating user: ", user);
		} catch (Exception e) {
			log.error("Error updating User: " + e);
		}
		return userDao.saveUser(user);
	}
	
	@Override
	@Transactional
	public boolean changePassword(String email, String currentPassword, String newPassword) {
		User user = getUserByEmail(email);
		
		if (!user.getPassword().equals(currentPassword)) {
			return false;
		}
		
		user.setPassword(newPassword);
		updateUser(user);
		
		return true;
	}
	
	@Override
	public User getUserById(Long id) {
		return userDao.getUserById(id);
	}

	@Override
	public User getUserByEmail(String email) {
		User user = null;
		try {
			user = userDao.getUserByEmail(email);
		} catch (Exception e) {
			log.error("Error get user by email: " + email + e);
		}
		return user;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		try {
			userDao.delete(id);
		} catch (Exception e) {
			log.error("Error deleting user by Id: " + id + e);
		}
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		try {
			users = userDao.getAllUsers();
		} catch (Exception e) {
			log.error("Error get all users " + e);
		}
		return users;
	}

	@Override
	public long getRowsCount() {
		return userDao.getRowsCount();
	}

	@Override
	public List<User> getPagingUsers(int currentPos, int itemsPerPage, String sortBy) {
		return userDao.getPagingUsers(currentPos, itemsPerPage, sortBy);
	}

}
