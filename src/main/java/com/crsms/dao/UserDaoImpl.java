
package com.crsms.dao;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crsms.domain.User;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger log = LogManager.getLogger(TestDaoImpl.class);
	
	public void saveUser(User user) {

		try {
			sessionFactory.getCurrentSession().save(user);
		} catch (Exception e) {
			log.error("Error saveUser: " + e);
		}

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
