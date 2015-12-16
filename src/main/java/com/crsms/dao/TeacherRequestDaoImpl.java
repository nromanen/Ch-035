package com.crsms.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.crsms.domain.TeacherRequest;

@Repository
public class TeacherRequestDaoImpl extends BaseDaoImpl<TeacherRequest> implements TeacherRequestDao {

	@Override
	public Long getRequestsHistoryCount() {
		long rowsCount = 0;
		try {
			Criteria criteria = this.getSessionFactory().getCurrentSession()
					.createCriteria(TeacherRequest.class, "request")
					.add(Restrictions.isNotNull("request.reviewdDate"));
			rowsCount = (long) criteria.setProjection(Projections.rowCount())
					.uniqueResult();
		} catch (Exception e) {
			this.getLogger().error("Error get getRequestsCount " + e);
			throw e;
		}
		return rowsCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherRequest> getRequestsHistory() {
		List<TeacherRequest> requests = new ArrayList<>();
		try {
			Criteria criteria = this.getSessionFactory().getCurrentSession()
					.createCriteria(TeacherRequest.class, "request")
					.createAlias("request.user", "user")
					.createAlias("user.userInfo", "userInfo")
					.add(Restrictions.isNotNull("request.reviewdDate"));
			criteria.addOrder(Order.asc("request.reviewdDate"));
			requests.addAll(criteria.list());
		} catch (Exception e) {
			this.getLogger().error("Error getRequestsHistory " + e);
			throw e;
		}
		return requests;
	}
}
