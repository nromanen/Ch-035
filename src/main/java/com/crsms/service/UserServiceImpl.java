
package com.crsms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.UserDao;
import com.crsms.domain.User;

/**
 * 
 * @author Roman Romaniuk
 *
 */
@Service("userService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public User saveUser(User user) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userDao.update(user);
		return user;
	}
	
	@Override
	@Transactional
	public boolean changePassword(String email, String currentPassword, String newPassword) {
		User user = getUserByEmail(email);
		
		if (!this.passwordEncoder.matches(currentPassword, user.getPassword())) {
			return false;
		}
		
		user.setPassword(this.passwordEncoder.encode(newPassword));
		userDao.update(user);
		
		return true;
	}
	
	@Override
	public User getUserById(Long id) {
		User user = userDao.getById(id);
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = null;
			user = userDao.getUserByEmail(email);
		return user;
	}

	@Override
	public long getRowsCount() {
		return userDao.getRowsCount();
	}

	@Override
	public List<User> getPagingUsers(int startPosition, int itemsPerPage, 
										String sortingField, String order) {
		return userDao.getPagingUsers(startPosition, itemsPerPage, sortingField, order);
	}
}
