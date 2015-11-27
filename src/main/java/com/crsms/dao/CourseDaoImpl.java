package com.crsms.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.crsms.domain.Course;
import com.crsms.domain.Question;
import com.crsms.domain.Test;

/**
 * 
 * @author Valerii Motresku, maftey, St. Roman
 *
 */

@Repository
public class CourseDaoImpl extends BaseDaoImpl<Course> implements CourseDao {

	public CourseDaoImpl() {
		super(Course.class);
	}
	
	@Override
	public Course get(String name) {
		try {
			this.getSessionFactory().getCurrentSession()
				.createQuery("FROM Course c WHERE c.name=:name")
				.setString("name", name).uniqueResult();
		} catch (Exception e) {
			this.getLogger().error("Error getCourse: " + e);
			throw e;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllByAreaId(Long areaId) {
		List<Course> list = new ArrayList<>();
		try {
			String hql = "from Course where area_id = :id order by id asc";
			Query query = this.getSessionFactory()
							  .getCurrentSession()
							  .createQuery(hql).setParameter("id", areaId);
			list = query.list();
		} catch (Exception e) {
			this.getLogger().error("Error in getting all courses by area id: " + e);
			throw e;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllByUserId(Long userId) {
		List<Course> list = new ArrayList<>();
		try {
			list = this.getSessionFactory().getCurrentSession()
										   .getNamedQuery(Course.GET_BY_USER_ID)
									 	   .setParameter("userId", userId).list();
		} catch (Exception e) {
			this.getLogger().error("Error in getting all courses by user id: " + e);
			throw e;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllByUserEmail(String email) {
		List<Course> list = new ArrayList<>();
		try {
			list = this.getSessionFactory().getCurrentSession()
										   .getNamedQuery(Course.GET_BY_USER_EMAIL)
									 	   .setParameter("email", email).list();
		} catch (Exception e) {
			this.getLogger().error("Error in getting all courses by user email: " + e);
			throw e;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllByOwnerEmail(String email) {
		List<Course> list = new ArrayList<>();
		try {
			list = this.getSessionFactory().getCurrentSession()
										   .getNamedQuery(Course.GET_BY_OWNER_EMAIL)
									 	   .setParameter("email", email).list();
		} catch (Exception e) {
			this.getLogger().error("Error in getting all courses by owner email: " + e);
			throw e;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> searchCourses(String searchWord) {
		try {
			return (List<Course>) this.getSessionFactory().getCurrentSession()
									  .getNamedQuery(Course.SEARCH)
									  .setParameter("s", "%" + searchWord + "%").list();
		} catch (HibernateException e) {
			this.getLogger().error("Error searchCourses: " + e);
		}
		return null;
	}

	@Override
	public Course getByTest(Test test) {
		return (Course) getSessionFactory().getCurrentSession().getNamedQuery(Course.GET_BY_TEST)
				.setParameter("id", test.getId()).uniqueResult();
	}
	
	@Override
	public Course getByTest(Long testId) {
		return (Course) getSessionFactory().getCurrentSession().getNamedQuery(Course.GET_BY_TEST)
				.setParameter("id", testId).uniqueResult();
	}
	
	@Override
	public Course getByQuestion(Question question) {
		return (Course) getSessionFactory()
					   .getCurrentSession()
					   .getNamedQuery(Course.GET_BY_QUESTION)
					   .setParameter("id", question.getId()).uniqueResult();
	}
	
	@Override
	public Course getByQuestion(Long questionId) {
		return (Course) getSessionFactory()
					   .getCurrentSession()
					   .getNamedQuery(Course.GET_BY_QUESTION)
					   .setParameter("id", questionId).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Long, Long> getStudentCoursesAndGroupsIds(String email) {
		List<List<Long>> list = new ArrayList<>();
		try {
			list = this.getSessionFactory().getCurrentSession()
										   .getNamedQuery(Course.GET_STUDENT_COURSES_AND_GROUPS_IDS)
									 	   .setParameter("email", email)
									 	   .setResultTransformer(Transformers.TO_LIST)
									 	   .list();
		} catch (Exception e) {
			this.getLogger().error("Error in getting student's courses and groups id's: " + e);
			throw e;
		}
		return this.transformListToMap(list);
	}
	
	/**
	 * Query Course.GET_STUDENT_COURSES_AND_GROUPS_IDS with result transformer Transformers.TO_LIST
	 * returns List<List<Long>> with only two values in the inner List<Long>:
	 * get(0) - course ID, get(1) - group ID.
	 * Method takes that values from all the inner lists and puts them into Map<Long, Long>, where:
	 * key - course ID, value - group ID. 
	 * @param list result of executing query Course.GET_STUDENT_COURSES_AND_GROUPS_IDS
	 * @return converted List as a Map
	 */
	private Map<Long, Long> transformListToMap(List<List<Long>> list) {
		Map<Long, Long> map = new HashMap<>();
		for (List<Long> innerList : list) {
			map.put(innerList.get(0), innerList.get(1));
		}
		return map;
	}
	
	@Override
	public Course getByModule(Long moduleId) {
		return (Course) getSessionFactory().getCurrentSession().getNamedQuery(Course.GET_BY_MODULE)
				.setParameter("id", moduleId).uniqueResult();
	}

	@Override
	public Course getByAnswer(Long answerId) {
		return (Course) getSessionFactory().getCurrentSession().getNamedQuery(Course.GET_BY_ANSWER)
				.setParameter("id", answerId).uniqueResult();
	}

}
