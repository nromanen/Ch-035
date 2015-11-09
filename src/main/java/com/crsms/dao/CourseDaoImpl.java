package com.crsms.dao;

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
	public List<Course> getAll() {
		try {
			return (List<Course>)sessionFactory.getCurrentSession().createQuery("FROM Course").list();

		} catch (HibernateException e) {
			logger.error("Error getAllCourse: " + e);
		}
		
		return null;
	}
	
	@Override
	public List<Course> getAllInitialized() {
		try {
			List<Course> courses = null;
			courses = (List<Course>)sessionFactory.getCurrentSession().createQuery("FROM Course").list();
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
	public Course getCourseById(Long id) {
		Course course = null;
		try {
			course = (Course)sessionFactory.getCurrentSession().
					get(Course.class, id);
			Hibernate.initialize(course.getModules());
			return course;
		} catch (HibernateException e) {
			logger.error("Error getCourseById: " + e);
		}
		return course;
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

	@Override
	public List<Course> getAllByAreaId(Long areaId) {
		List<Course> list = new ArrayList<Course>();
		try {
			String hql = "from Course where area_id = :id order by id asc";
			Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", areaId);
			list = query.list();
		} catch (Exception e) {
			logger.error("Error in getting all courses by area id: " + e);
		}
		return list;
	}

}
