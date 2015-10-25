package com.crsms.service;

import java.util.List;
import java.util.Set;

import com.crsms.domain.Course;

/**
 * 
 * @author Valerii Motresku
 * @author maftey
 *
 */

public interface CourseService {
	void saveCourse(Course course);
	
	List<Course> getAllCourse();
	
	Course getCourseById(Long id);

	void updateCourse(Course course);
	
	Course getCourse(String name);
	
	void deleteCourse(Course course);
}
