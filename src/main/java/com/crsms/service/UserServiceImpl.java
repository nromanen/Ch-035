
package com.crsms.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private CourseService courseService;
	
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
		log.debug("updating user: ", user);

		return userDao.saveUser(user);
	}
	
	@Override
	@Transactional
	public boolean changePassword(String email, String currentPassword, String newPassword) {
		User user = getUserByEmail(email);
		
		if (!this.passwordEncoder.matches(currentPassword, user.getPassword())) {
			return false;
		}
		
		user.setPassword(this.passwordEncoder.encode(newPassword));
		updateUser(user);
		
		return true;
	}
	
	@Override
	public User getUserById(Long id) {
		User user = userDao.getUserById(id);
		return user;
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

	public boolean isEmailExists(String email) {
		try {
			if(!userDao.getUserByEmail(email).equals(email)){
				return true;
			}
		} catch (Exception e) {
			log.error("Email " + email + " exists " + e);
		}
		return false;
	}

	public long getRowsCount() {
		return userDao.getRowsCount();
	}

	@Override
	public List<User> getPagingUsers(int startPosition, int itemsPerPage, String sortingField, String order) {
		return userDao.getPagingUsers(startPosition, itemsPerPage, sortingField, order);
	}
	
	@Override
	public List<User> getAllWithInitializedCourses() {
		List<User> users = new ArrayList<>();
		try {
			users = userDao.getAllUsers();
			for (User user : users) {
				Hibernate.initialize(user.getCourses());
			}
		} catch (Exception e) {
			log.error("Error in get all users with initialized courses " + e);
		}
		return users;
	}

}
