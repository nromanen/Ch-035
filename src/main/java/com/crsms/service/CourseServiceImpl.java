package com.crsms.service;

import java.util.List;
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
	public List<Course> getAllCourse() {
		return courseDao.getAllCourse();
	}

	@Override
	public Course getCourseById(Long id) {
		return courseDao.getCourseById(id);
	}

	@Override
	public void updateCourse(Course course) {
		courseDao.updateCourse(course);

	}

	@Override
	public Course getCourse(String name) {
		return courseDao.getCourse(name);
	}

	@Override
	public void deleteCourse(Course course) {
		courseDao.deleteCourse(course);
	}

}
