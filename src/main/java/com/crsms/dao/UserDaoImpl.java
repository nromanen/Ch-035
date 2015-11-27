package com.crsms.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.crsms.domain.User;

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
	public long getRowsCount() {
		long rowsCount = 0;
		try {
			rowsCount = (long) this.getSessionFactory().getCurrentSession()
					.createCriteria(User.class)
					.setProjection(Projections.rowCount()).uniqueResult();
		} catch (Exception e) {
			this.getLogger().error("Error get rowsCount " + e);
			throw e;
		}
		return rowsCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getPagingUsers(int startPosition, int itemsPerPage,
			String sortingField, String order) {
		List<User> users = new ArrayList<>();

		try {
			Criteria criteria = this.getSessionFactory().getCurrentSession()
					.createCriteria(User.class, "user");
			criteria.createAlias("role", "role")
					.createAlias("userInfo", "userInfo");
//			criteria.setProjection(
//				Projections.projectionList()
//					.add(Projections.property("user.id"), "id")
//					.add(Projections.property("user.email"), "email")
//					.add(Projections.property("user.isEnabled"), "isEnabled")
//					.add(Projections.property("role.name"), "name")
//					.add(Projections.property("userInfo.lastName"), "lastName")
//					.add(Projections.property("userInfo.firstName"), "firstName")
//				);
//			criteria.setResultTransformer(Criteria.ROOT_ENTITY);
					
			if (sortingField != null && order.equals("asc")) {
				criteria.addOrder(Order.asc(sortingField));
			} else {
				criteria.addOrder(Order.desc(sortingField));
			}
			criteria.setFirstResult(startPosition);
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
	public List<User> search(String keyWord) {
		List<User> users = new ArrayList<>();
		if (!keyWord.equals("")) {
			try {
				Criteria criteria = this.getSessionFactory()
						.getCurrentSession().createCriteria(User.class);
				// Disjunction or = Restrictions.disjunction();
				// or.add(Restrictions.ilike("email", keyWord,
				// MatchMode.ANYWHERE));
				// or.add(Restrictions.ilike("role", keyWord,
				// MatchMode.ANYWHERE));
				// or.add(Restrictions.ilike("userInfo", keyWord,
				// MatchMode.ANYWHERE));
				// or.add(Restrictions.ilike("isEnabled", keyWord,
				// MatchMode.ANYWHERE));
				// criteria.add(or);
				criteria.add(Restrictions.ilike("email", keyWord,
						MatchMode.ANYWHERE));
				users.addAll(criteria.list());
			} catch (Exception e) {
				this.getLogger().error("Search error " + e);
				throw e;
			}
		}
		return users;
	}
}