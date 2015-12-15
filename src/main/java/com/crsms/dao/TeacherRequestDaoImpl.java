package com.crsms.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.crsms.domain.TeacherRequest;
import com.crsms.domain.User;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TeacherRequestDaoImpl extends BaseDaoImpl<TeacherRequest> implements TeacherRequestDao {

	@Override
	@Transactional
	public User getUserByRequestId(Long requestId) {
		User user = null;
		try {
			Criteria criteria = this.getSessionFactory().getCurrentSession()
					.createCriteria(User.class, "user")
					.createAlias("user.teacherRequest", "request")
					.add(Restrictions.eq("request.id", requestId));
			user = (User) criteria.uniqueResult();
			System.out.println("-------requestdao--------" + user);
		} catch (Exception e) {
			this.getLogger().error("Error get user by teacher request " + e);
			throw e;
		}
		return user;
	}

	@Override
	@Transactional
	public Long getRequestsCount() {
		long rowsCount = 0;
		try {
			Criteria criteria = this.getSessionFactory().getCurrentSession()
					.createCriteria(TeacherRequest.class, "teacherRequest");
			rowsCount = (long) criteria.setProjection(Projections.rowCount())
					.uniqueResult();
		} catch (Exception e) {
			this.getLogger().error("Error get getRequestsCount " + e);
			throw e;
		}
		return rowsCount;
	}
}
