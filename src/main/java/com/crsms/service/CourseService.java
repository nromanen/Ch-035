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
	
	void saveCourse(Course course);
	
	void saveCourse(Course course, long areaId, int sweekDuration);
	
	List<Course> getAllCourse();
	
	List<Course> getAllInitialized();
	
	Course getCourseById(Long id);

	void updateCourse(Course course);
	
	void updateCourse(Course course, long areaId, int sweekDuration);
	
	Course getCourse(String name);
	
	//@PreAuthorize("hasAnyRole('ROLE_TEACHER')")
	void deleteCourse(Course course);
	
	List<Course> getAllByAreaId(Long areaId);
	
	void subscribe(Long courseId, Long userId);
	
	void unsubscribe(Long courseId, Long userId);
	
	List<Course> getAllWithInitializedUsers();
	

}
