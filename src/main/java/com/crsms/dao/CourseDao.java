package com.crsms.dao;

import java.util.List;
import java.util.Set;

import com.crsms.domain.Course;


/**
 * 
 * @author Valerii Motresku
 *
 */

public interface CourseDao {
	
	void saveCourse(Course course);
	
	List<Course> getAllCourse();
	
	/**
	 * @return a Course instance or null
	 */
	Course getCourseById(Long id);

	void updateCourse(Course course);
	
	Course getCourse(String name);
	
	void deleteCourse(Course course);
}
