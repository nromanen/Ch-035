package com.crsms.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crsms.domain.UserInfo;

@Repository("userInfoDao")
public class UserInfoDaoImpl implements UserInfoDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public UserInfo saveUserInfo(UserInfo user) {
		if (user.getUserId() == null) {
			sessionFactory.getCurrentSession().save(user);
		} else {
			sessionFactory.getCurrentSession().update(user);
		}
		return user;
	}

	@Override
	public void delete(Long id) {
			Query query = sessionFactory.getCurrentSession()
					.createQuery("DELETE FROM UserInfo uf WHERE uf.user_id=:id");
			query.setParameter("id", id).executeUpdate();
	}

}
