package com.crsms.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crsms.domain.Course;

/**
 * 
 * @author Valerii Motresku, maftey, Roman S
 *
 */

@Repository("courseDao")
public class CourseDaoImpl extends BaseDaoImpl<Course> implements CourseDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger logger = LogManager.getLogger(TestDaoImpl.class);

	public CourseDaoImpl() {
		super(Course.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllInitialized() {
		try {
			List<Course> courses = null;
			courses = (List<Course>) sessionFactory.getCurrentSession()
													.createQuery("FROM Course").list();
			for (Course course : courses) {
				Hibernate.initialize(course.getModules());
			}
			return courses;

		} catch (HibernateException e) {
			logger.error("Error getAllCourse: " + e);
			throw e;
		}
		
	}
	
	@Override
	public Course get(String name) {
		try {
			sessionFactory.getCurrentSession()
				.createQuery("FROM Course c WHERE c.name=:name")
				.setString("name", name).uniqueResult();
		} catch (Exception e) {
			logger.error("Error getCourse: " + e);
			throw e;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllByAreaId(Long areaId) {
		List<Course> list = new ArrayList<Course>();
		try {
			String hql = "from Course where area_id = :id order by id asc";
			Query query = sessionFactory.getCurrentSession()
										.createQuery(hql).setParameter("id", areaId);
			list = query.list();
		} catch (Exception e) {
			logger.error("Error in getting all courses by area id: " + e);
			throw e;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllByUserId(Long userId) {
		List<Course> list = new ArrayList<Course>();
		try {
			list = sessionFactory.getCurrentSession()
								 .getNamedQuery(Course.GET_BY_USER_ID)
							 	 .setParameter("userId", userId).list();
		} catch (Exception e) {
			logger.error("Error in getting all courses by user id: " + e);
			throw e;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllByUserEmail(String email) {
		List<Course> list = new ArrayList<Course>();
		try {
			list = sessionFactory.getCurrentSession()
								 .getNamedQuery(Course.GET_BY_USER_EMAIL)
							 	 .setParameter("email", email).list();
		} catch (Exception e) {
			logger.error("Error in getting all courses by user email: " + e);
			throw e;
		}
		return list;
	}

	@Override
	public boolean hasSubscribedUsers(Long courseId) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery(
			"SELECT COUNT(*) as count FROM course_users WHERE courses_id = :courses_id LIMIT 1"
		).setParameter("courses_id", courseId);
		long count = ((BigInteger) query.uniqueResult()).longValue();
		if (count > 0) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public boolean hasTestResults(Long courseId) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery(
			"SELECT COUNT(*) FROM test_result "
			+ "JOIN test ON test.id = test_result.test_id "
			+ "JOIN module_test ON test.id = module_test.tests_id "
			+ "JOIN module ON module.id = module_test.module_id "
			+ "JOIN course_module ON module.id = course_module.modules_id "
			//+ "JOIN course ON course.id = course_module.course_id "
			+ "WHERE course_module.course_id = :course_id LIMIT 1"
		).setParameter("course_id", courseId);
		
		/*
		Query query = sessionFactory.getCurrentSession().createQuery(
			"SELECT COUNT(*) FROM TestResult test_result, Course course"
			+ "JOIN course.modules modules "
			+ "JOIN modules.tests test "
			+ "JOIN module"
			+ "WHERE test_result.test.id = test.id course.id = :courses_id LIMIT 1"
		).setParameter("courses_id", 2); 
		*/
		long count = ((BigInteger) query.uniqueResult()).longValue();
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

}
