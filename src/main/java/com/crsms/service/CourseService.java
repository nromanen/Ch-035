package com.crsms.service;

import java.util.List;

import com.crsms.domain.Course;

/**
 * 
 * @author Valerii Motresku
 * @author maftey
 *
 */

public interface CourseService {
	
	void save(Course course);
	
	void save(Course course, long areaId, String ownerEmail);
	
	List<Course> getAll();
	
	List<Course> getAllInitialized();
	
	Course getById(Long id);

	void update(Course course);
	
	void update(Course course, long areaId, String ownerEmail);
	
	Course get(String name);
	
	void deleteCourse(Course course);
	
	List<Course> getAllByAreaId(Long areaId);
	
	void subscribe(Long courseId, String email);
	
	void unsubscribe(Long courseId, String email);
	
	List<Course> getAllByUserId(Long userId);
	
	List<Course> getAllByUserEmail(String email);

	List<Course> getAllByOwnerEmail(String email);
}
