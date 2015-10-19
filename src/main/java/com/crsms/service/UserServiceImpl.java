package com.crsms.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.UserDao;
import com.crsms.domain.User;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
    private UserDao userDao;
	
	@Override
	public void saveUser(User user) {
		
		userDao.saveUser(user);

	}

	@Override
	public Set<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUser(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
