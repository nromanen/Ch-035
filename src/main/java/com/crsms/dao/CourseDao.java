package com.crsms.dao;

import java.util.List;

import com.crsms.domain.Course;


/**
 * 
 * @author Valerii Motresku
 *
 */

public interface CourseDao {
	
	void save(Course course);
	
	List<Course> getAll();
	
	List<Course> getAllInitialized();
	
	/**
	 * @return a Course instance or null
	 */
	Course getById(Long id);

	void update(Course course);
	
	Course get(String name);
	
	void delete(Course course);
	
	List<Course> getAllByAreaId(Long areaId);
	
	List<Course> getAllByUserId(Long userId);

	List<Course> getAllByUserEmail(String email);

	boolean hasSubscribedUsers(Long courseId);
	
	boolean hasTestResults(Long courseId);
}
