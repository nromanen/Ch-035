package com.crsms.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.RoleDao;
import com.crsms.dao.UserDao;
import com.crsms.dao.UserDaoImpl;
import com.crsms.domain.Role;
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
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Override
	@Transactional
	public User saveUser(User user) {
		try{
		userDao.saveUser(user);
		}catch (Exception e) {
			log.error("Error save user: " + e);
		}
		return user;
	}
	
	@Override
	public User saveUser(User user, long roleId) {
		try {
			log.debug("saving user: ", user);
			Role role = roleDao.getRoleById(roleId);
			user.addRole(role);
		} catch (Exception e) {
			log.error("Error saving User: " + e);
		}
		return userDao.saveUser(user);

	}

	@Override
	@Transactional
	public User updateUser(User user, Role role) {
		try {
			log.debug("updating user: ", user);
			user.addRole(role);
		} catch (Exception e) {
			log.error("Error updating User: " + e);
		}
		return userDao.saveUser(user);
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
	public User getUserById(Long id) {
		return userDao.getUserById(id);
	}

	@Override
	public User getUserByEmail(String email) {
		User user = null;
		try{
		user = userDao.getUserByEmail(email);
		}catch (Exception e) {
			log.error("Error get user by email: " + email + e);
		}
		return user;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		try{
		userDao.delete(id);
		}catch (Exception e) {
			log.error("Error deleting user by Id: " + id + e);
		}
	}

	@Override
	public List<User> getAllUsers() {
		List <User> users = new ArrayList<>();
		try{
		users = userDao.getAllUsers();
		}catch (Exception e) {
			log.error("Error get all users " + e);
		}
		return users;
	}

	

}
