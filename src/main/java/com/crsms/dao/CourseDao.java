package com.crsms.dao;

import java.util.Set;

import com.crsms.domain.Course;


/**
 * 
 * @author Valerii Motresku
 *
 */

public interface CourseDao {
	
	void saveCourse(Course course);
	
	Set<Course> getAllCourse();
	
	Course getCourseById(Long id);

	void updateCourse(Course course);
	
	Course getCourse(String name);
}
