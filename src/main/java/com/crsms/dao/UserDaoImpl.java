package com.crsms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crsms.domain.User;
/**
 * 
 * @author Roman Romaniuk
 *
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static Logger log = LogManager.getLogger(UserDaoImpl.class);
	
//	SELECT setval('users_id_seq', (SELECT MAX(id) FROM users)+1)

	public User saveUser(User user) {
		try {
			if (user.getId() == null) {
				log.info("saving user: ", user);
				sessionFactory.getCurrentSession().save(user);
			} else {
				log.info("updating user: ", user);
				sessionFactory.getCurrentSession().update(user);
			}
		} catch (Exception e) {
			log.error("Error saveUser: " + e);
		}
		return user;
	}

	@Override
	public void delete(Long id) {
		try {
			log.info("delete " + id);
			Query query = sessionFactory.getCurrentSession()
					.getNamedQuery(User.DELETE).setLong("id", id);
			query.executeUpdate();
		} catch (Exception e) {
			log.error("Error deleting user by Id: " + id + e);
		}
	}

	@Override
	public User getUserById(Long id) {
		User user = new User();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			user = (User) session.get(User.class, id);
		} catch (Exception e) {
			log.error("Error get user by Id: " + id + e);
		} finally {
			session.clear();
		}
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = new User();
		try {
			log.info("getUserById email: ", user);
			Query query = sessionFactory.getCurrentSession()
					.getNamedQuery(User.BY_EMAIL).setString("email", email);
			user = (User) query.uniqueResult();
		} catch (Exception e) {
			log.error("Error get user by email: " + email + e);
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		List <User> users = new ArrayList<>();
		try{
			log.info("get all users");
		Query query = sessionFactory.getCurrentSession().getNamedQuery(
				User.ALL_SORTED);
		users = query.list();
		}catch (Exception e) {
			log.error("Error get all users " + e);
		}
		return users;
	}
}
