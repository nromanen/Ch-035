package com.crsms.dao;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	private static Logger log = LogManager.getLogger(TestDaoImpl.class);
	
	@Override
	public void saveCourse(Course course) {
		
		try {
			sessionFactory.getCurrentSession().save(course);
		} catch (Exception e) {
			log.error("Error saveCourse: " + e);
		}

	}

	@Override
	public Set<Course> getAllCourse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course getCourseById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCourse(Course test) {
		// TODO Auto-generated method stub

	}

	@Override
	public Course getCourse(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
