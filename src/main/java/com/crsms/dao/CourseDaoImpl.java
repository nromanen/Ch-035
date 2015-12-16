package com.crsms.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllByAreaId(Long areaId) {
		try {
			return getSessionFactory().getCurrentSession()
									  .getNamedQuery(Course.GET_BY_AREA_ID)
							  		  .setParameter("id", areaId)
							  		  .list();
		} catch (Exception e) {
			getLogger().error("Error in getting all courses by area id: " + e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllByUserEmail(String email) {
		try {
			return getSessionFactory().getCurrentSession()
									  .getNamedQuery(Course.GET_BY_USER_EMAIL)
									  .setParameter("email", email).list();
		} catch (Exception e) {
			getLogger().error("Error in getting all courses by user email: " + e);
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllByOwnerEmail(String email) {
		try {
			return getSessionFactory().getCurrentSession()
									  .getNamedQuery(Course.GET_BY_OWNER_EMAIL)
									  .setParameter("email", email).list();
		} catch (Exception e) {
			getLogger().error("Error in getting all courses by owner email: " + e);
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> searchCourses(String searchWord) {
		try {
			return getSessionFactory().getCurrentSession()
									  .getNamedQuery(Course.SEARCH)
									  .setParameter("s", "%" + searchWord + "%")
									  .list();
		} catch (HibernateException e) {
			getLogger().error("Error searchCourses: " + e);
			throw e;
		}
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
		try {
			List<List<Long>> list = this.getSessionFactory().getCurrentSession()
										.getNamedQuery(Course.GET_STUDENT_COURSES_AND_GROUPS_IDS)
									 	.setParameter("email", email)
									 	.setResultTransformer(Transformers.TO_LIST)
									 	.list();
			return transformListToMap(list);
		} catch (Exception e) {
			this.getLogger().error("Error in getting student's courses and groups id's: " + e);
			throw e;
		}
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllPublished() {
		try {
			return this.getSessionFactory()
					   .getCurrentSession()
					   .getNamedQuery(Course.GET_ALL_PUBLISHED)
					   .list();
		} catch (Exception e) {
			this.getLogger().error("Error in getting all published courses: " + e);
			throw e;
		}
	}

}
