
package com.crsms.dao;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import com.crsms.domain.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger log = LogManager.getLogger(UserDaoImpl.class);
	
	public User saveUser(User user) {
		if (user.getId() == null) {
			sessionFactory.getCurrentSession().save(user);
		} else {
			sessionFactory.getCurrentSession().update(user);
		}
		return user;
	}
	
	@Override
	public void delete(Long id) {
		Query query = sessionFactory.getCurrentSession()
				.getNamedQuery(User.DELETE).setLong("id", id);
		query.executeUpdate();
	}

	@Override
	public User getUserById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, id);
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByEmail(String email) {

//		Query query = sessionFactory.getCurrentSession()
//				.getNamedQuery(User.BY_EMAIL).setString("email", email);
//		User user = (User) query.uniqueResult();
		
		List<User>users = sessionFactory.getCurrentSession()
				.getNamedQuery(User.BY_EMAIL).setString("email", email).list();
		return users.size() == 0 ? null : DataAccessUtils.requiredSingleResult(users); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		Query query = sessionFactory.getCurrentSession().getNamedQuery(
				User.ALL_SORTED);
		return query.list();
	}

	
	
}
