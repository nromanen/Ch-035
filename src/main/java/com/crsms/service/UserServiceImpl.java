package com.crsms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.RoleDao;
import com.crsms.dao.UserDao;
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
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;

	@Override
	@Transactional
	public User saveUser(User user) {
		return userDao.saveUser(user);
	}

	@Override
	@Transactional
	public User updateUser(User user, long roleId) {
		 Role role = roleDao.getRoleById(roleId);
		 user.setRole(role);
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
