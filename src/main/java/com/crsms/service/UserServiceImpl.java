
package com.crsms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.UserDao;
import com.crsms.domain.User;
import com.crsms.util.Invocable;

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
			userDao.save(user);
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
	public User getUserByEmail(String email) {
		User user = null;
			user = userDao.getUserByEmail(email);
		return user;
	}
	
	@Override
	public User getUserByEmail(String email, List<Invocable<User>> initializers) {
		User user = null;
		try {
			user = userDao.getUserByEmail(email);
			for (Invocable<User> initalizer : initializers) {
				initalizer.invoke(user);
			}
		} catch (Exception e) {
			log.error("Error get user by email: " + email + e);
		}
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
