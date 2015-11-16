package com.crsms.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.crsms.domain.Course;

/**
 * 
 * @author Valerii Motresku
 * @author maftey
 *
 */

public interface CourseService {
	
	void save(Course course);
	
	void save(Course course, long areaId, int sweekDuration);
	
	List<Course> getAll();
	
	List<Course> getAllInitialized();
	
	Course getById(Long id);

	void update(Course course);
	
	void update(Course course, long areaId, int sweekDuration);
	
	Course get(String name);
	
	//@PreAuthorize("hasAnyRole('ROLE_TEACHER')")
	void deleteCourse(Course course);
	
	List<Course> getAllByAreaId(Long areaId);
	
	void subscribe(Long courseId, String email);
	
	void unsubscribe(Long courseId, String email);
	
	List<Course> getAllByUserId(Long userId);
	
	List<Course> getAllByUserEmail(String email);
	
	List<Course> searchCourses(String seachWord);

}
