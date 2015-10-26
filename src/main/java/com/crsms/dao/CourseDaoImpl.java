package com.crsms.dao;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crsms.domain.Course;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Repository("courseDao")
public class CourseDaoImpl implements CourseDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger logger = LogManager.getLogger(TestDaoImpl.class);
	
	@Override
	public void saveCourse(Course course) {
		
		try {
			if(course.getId() == null) {
				//sessionFactory.getCurrentSession().persist(course);
				sessionFactory.getCurrentSession().save(course);
				logger.info("DAO:create course:" + course.getName());
			} else {
				sessionFactory.getCurrentSession().update(course);
				logger.info("DAO:create update:" + course.getName());
			}
		} catch (HibernateException e) {
			logger.error("Error saveCourse: " + e);
		}
	}

	@Override
	public List<Course> getAllCourse() {
		try {
			return (List<Course>)sessionFactory.getCurrentSession().createQuery("FROM Course").list();

		} catch (HibernateException e) {
			logger.error("Error getAllCourse: " + e);
		}
		
		return null;
	}

	@Override
	public Course getCourseById(Long id) {
		try {
			return (Course)sessionFactory.getCurrentSession().
					get(Course.class, id);
		} catch (HibernateException e) {
			logger.error("Error getCourseById: " + e);
		}
		return null;
	}

	@Override
	public void updateCourse(Course course) {
		try {
			sessionFactory.getCurrentSession().update(course);
			logger.info("DAO:create update:" + course.getName());
		} catch (Exception e) {
			logger.error("Error updateCourse: " + e);
		}

	}
	
	@Override
	public Course getCourse(String name) {
		try {
			sessionFactory.getCurrentSession()
				.createQuery("FROM Course c WHERE c.name=:name")
				.setString("name", name).uniqueResult();
		} catch (Exception e) {
			logger.error("Error getCourse: " + e);
		}
		return null;
	}

	@Override
	public void deleteCourse(Course course) {
		try {
			sessionFactory.getCurrentSession().delete(course);
		} catch (HibernateException e) {
			logger.error("Error delete: " + e);
		}
		
	}

}
