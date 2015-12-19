package com.crsms.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.crsms.domain.Group;
import com.crsms.dto.UserIdFNameLNameEmailDto;

@Repository
public class GroupDaoImpl extends BaseDaoImpl<Group> implements GroupDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getAllByCourseId(Long courseId) {
		try {
			return getSessionFactory().getCurrentSession()
									  .getNamedQuery(Group.GET_ALL_BY_COURSE_ID)
									  .setParameter("courseId", courseId)
									  .list();
		} catch (Exception e) {
			this.getLogger().error("Error in getting all groups by course id: " + e);
			throw e;
		}
	}

	@Override
	public void deleteById(Long groupId) {
		try {
			this.getSessionFactory().getCurrentSession()
									.getNamedQuery(Group.DELETE_BY_ID)
									.setParameter("id", groupId)
									.executeUpdate();
		} catch (Exception e) {
			getLogger().error("Error in delete group by id: " + e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserIdFNameLNameEmailDto> getStudentsFromGroup(Long groupId) {
		try {
			return getSessionFactory().getCurrentSession()
									  .getNamedQuery(Group.GET_STUDENTS_FROM_GROUP)
									  .setParameter("id", groupId)
									  .list();
		} catch (Exception e) {
			getLogger().error("Error in get students id and email from group: " + e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> selectAlreadySubscribedUsers(Long courseId, Set<String> emails) {
		try {
			return getSessionFactory().getCurrentSession()
									  .getNamedQuery(Group.SELECT_ALREADY_SUBSCRIBED_USERS)
									  .setParameter("courseId", courseId)
									  .setParameterList("emails", emails)
									  .list();
		} catch (Exception e) {
			getLogger().error("Error in select already subscribed users: " + e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserIdFNameLNameEmailDto> searchStudents(String textToSearch) {
		try {
			return getSessionFactory().getCurrentSession()
									  .getNamedQuery(Group.SEARCH_STUDENTS)
									  .setParameter("textToSearch", "%" + textToSearch + "%")
									  .list();
		} catch (Exception e) {
			getLogger().error("Error in search students: " + e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserIdFNameLNameEmailDto> getStudentsFromGroupPaginated(Long groupId,
			String sortBy, String sortOrder, Integer offset, Integer count) {
		try {
			Criteria criteria = getSessionFactory()
								.getCurrentSession()
								.createCriteria(Group.class, "g")
								.createAlias("g.users", "u")
								.createAlias("u.userInfo", "ui")
								.setProjection(Projections.projectionList()
									.add(Projections.property("u.id"), "id")
									.add(Projections.property("ui.firstName"), "firstName")
									.add(Projections.property("ui.lastName"), "lastName")
									.add(Projections.property("u.email"), "email"))
								.add(Restrictions.eq("g.id", groupId))
								.setFirstResult(offset)
								.setMaxResults(count)
								.setResultTransformer(
										Transformers.aliasToBean(UserIdFNameLNameEmailDto.class));
			if (sortBy != null && sortOrder != null && sortOrder.equals("desc")) {
				criteria.addOrder(Order.desc(sortBy));
			} else {
				criteria.addOrder(Order.asc(sortBy));
			}
			return criteria.list();
		} catch (Exception e) {
			getLogger().error("Error in get students from group paginated " + e);
			throw e;
		}
	}

}
