package com.crsms.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import com.crsms.domain.User;

import org.joda.time.DateTime;
/**
 * 
 * @author Roman Romaniuk
 *
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}

	@Override
	public User getById(Long id) {
		User user = new User();
		try {
			user = (User) this.getSessionFactory().getCurrentSession()
					.get(User.class, id);
			if (user != null) {
				Hibernate.initialize(user.getRole());
			}

		} catch (Exception e) {
			this.getLogger().error("Error get user by Id: " + e);
			throw e;
		}
		Hibernate.initialize(user.getRole());
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = null;
		try {
			this.getLogger().info("getUserByEmail email: ", user);
			Query query = this.getSessionFactory().getCurrentSession()
					.getNamedQuery(User.BY_EMAIL).setString("email", email);
			user = (User) query.uniqueResult();
		} catch (Exception e) {
			this.getLogger().error("Error get user by email: " + email + e);
			throw e;
		}
		return user;
	}

	@Override
	public long getRowsCount(String keyWord) {
		long rowsCount = 0;
		try {
			Criteria criteria = this.getSessionFactory().getCurrentSession()
					.createCriteria(User.class, "user")
					.createAlias("user.role", "role")
					.createAlias("user.userInfo", "userInfo");
			if (!keyWord.equals("")) 
				criteria.add(setDisjunction(keyWord));
			rowsCount = (long) criteria.setProjection(Projections.rowCount())
					.uniqueResult();
		} catch (Exception e) {
			this.getLogger().error("Error get rowsCount " + e);
			throw e;
		}
		return rowsCount;
	}
	
	@Override
	public long getUsersToApproveCount() {
		long rowsCount = 0;
		try {
			Criteria criteria = this.getSessionFactory().getCurrentSession()
					.createCriteria(User.class, "user")
					.createAlias("user.teacherRequest", "teacherRequest")
					.add(Restrictions.isNull("teacherRequest.reviewdDate"));
			rowsCount = (long) criteria.setProjection(Projections.rowCount())
					.uniqueResult();
		} catch (Exception e) {
			this.getLogger().error("Error get approvedUsersCount " + e);
			throw e;
		}
		return rowsCount;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getPagingUsers(int offSet, int itemsPerPage,
			String sortingField, String order, String keyWord) {
		List<User> users = new ArrayList<>();
		try {
			Criteria criteria = this.getSessionFactory().getCurrentSession()
					.createCriteria(User.class, "user")
					.createAlias("user.role", "role")
					.createAlias("user.userInfo", "userInfo")
					.createAlias("user.teacherRequest", "teacherRequest", JoinType.LEFT_OUTER_JOIN);
			if (!keyWord.equals("")) {
					 criteria.add(setDisjunction(keyWord));
				}
			if (sortingField != null && order.equals("asc")) {
				criteria.addOrder(Order.asc(sortingField));
			} else {
				criteria.addOrder(Order.desc(sortingField));
			}
			criteria.setFirstResult(offSet);
			criteria.setMaxResults(itemsPerPage);
			users.addAll(criteria.list());
		} catch (Exception e) {
			this.getLogger().error("Error getPagingUsers " + e);
			throw e;
		}
		return users;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersToApprove() {
		List<User> users = new ArrayList<>();
		try {
			Criteria criteria = this.getSessionFactory().getCurrentSession()
					.createCriteria(User.class, "user")
					.createAlias("user.role", "role")
					.createAlias("user.userInfo", "userInfo")
					.createAlias("user.teacherRequest", "teacherRequest")
					.add(Restrictions.isNull("teacherRequest.reviewdDate"));
			criteria.addOrder(Order.asc("teacherRequest.requestedDate"));
			users.addAll(criteria.list());
		} catch (Exception e) {
			this.getLogger().error("Error getUsersToApprove " + e);
			throw e;
		}
		return users;
	}
	
	private Disjunction setDisjunction(String keyWord) {
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.ilike("user.email", keyWord,
				MatchMode.ANYWHERE));
		or.add(Restrictions.ilike("role.name", keyWord, 
				MatchMode.ANYWHERE));
		or.add(Restrictions.ilike("userInfo.firstName", keyWord,
				MatchMode.ANYWHERE));
		or.add(Restrictions.ilike("userInfo.lastName", keyWord,
				MatchMode.ANYWHERE));
		return or;
	}

	
}