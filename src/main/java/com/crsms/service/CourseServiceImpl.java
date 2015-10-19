package com.crsms.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.CourseDao;
import com.crsms.domain.Course;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService {
	
	@Autowired
    private CourseDao courseDao;
	
	@Override
	public void saveCourse(Course course) {
		courseDao.saveCourse(course);

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
	public void updateCourse(Course course) {
		// TODO Auto-generated method stub

	}

	@Override
	public Course getCourse(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
