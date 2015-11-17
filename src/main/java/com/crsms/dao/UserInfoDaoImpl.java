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
		try {
			if (user.getId() == null) {
				sessionFactory.getCurrentSession().save(user);
			} else {
				sessionFactory.getCurrentSession().update(user);
			}
		} catch (Exception e) {
			logger.debug("Error with saving userInfo" + e);
		}
		return user;
	}
	
	@Override
	public void delete(Long id) {
		try {
			Query query = sessionFactory.getCurrentSession()
					.getNamedQuery(UserInfo.DELETE).setLong("id", id);
			query.executeUpdate();
		} catch (Exception e) {
			logger.debug("Error wirh deleting userInfo by Id: " + id + e);
		}
	}

}
