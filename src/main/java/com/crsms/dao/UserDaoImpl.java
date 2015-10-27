package com.crsms.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crsms.domain.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static Logger logger = LogManager.getLogger(UserDaoImpl.class);

	@Override
	public User saveUser(User user) {
		logger.debug("Saving user");
		if (user.getId() == null) {
			sessionFactory.getCurrentSession().save(user);
		} else {
			sessionFactory.getCurrentSession().update(user);
		}
		return user;
	}

	@Override
	public void delete(Long id) {
		logger.debug("Removing user with id " + id);
		Query query = sessionFactory.getCurrentSession().createQuery(
				"DELETE FROM User u WHERE u.id=:id");
		query.setParameter("id", id).executeUpdate();
	}

	@Override
	public User getUserById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, id);
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"FROM User u WHERE u.email= :email");
		query.setParameter("email", email);
		return (User) query.list().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		Query q = sessionFactory.getCurrentSession().createQuery(
				"from User u ORDER BY u.id");
		List<User> allUsers = (List<User>) q.list();

		return allUsers;
	}

}
