package com.crsms.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crsms.domain.UserInfo;

@Repository("userInfoDao")
public class UserInfoDaoImpl implements UserInfoDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger logger = LogManager.getLogger(UserDaoImpl.class);

	@Override
	public UserInfo saveUserInfo(UserInfo user) {
		logger.debug("Saving userInfo");
		if (user.getId() == null) {
			sessionFactory.getCurrentSession().save(user);
		} else {
			sessionFactory.getCurrentSession().update(user);
		}
		return user;
	}

	@Override
	public void delete(Long id) {
		logger.debug("Removing userInfo with id " + id);
		Query query = sessionFactory.getCurrentSession().createQuery(
				"DELETE FROM UserInfo uf WHERE uf.user_id=:id");
		query.setParameter("id", id).executeUpdate();
	}

}
