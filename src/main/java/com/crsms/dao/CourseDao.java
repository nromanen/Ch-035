package com.crsms.dao;

import java.util.List;

import com.crsms.domain.Course;


/**
 * 
 * @author Valerii Motresku
 *
 */

public interface CourseDao {
	
	void saveCourse(Course course);
	
	List<Course> getAll();
	
	List<Course> getAllInitialized();
	
	/**
	 * @return a Course instance or null
	 */
	Course getCourseById(Long id);

	void updateCourse(Course course);
	
	Course getCourse(String name);
	
	void deleteCourse(Course course);
	
	List<Course> getAllByAreaId(Long areaId);
	
	List<Course> getAllByUserId(Long userId);

	List<Course> getAllByUserEmail(String email);

	boolean hasSubscribedUsers(Long courseId);
	
	boolean hasTestResults(Long courseId);
}
