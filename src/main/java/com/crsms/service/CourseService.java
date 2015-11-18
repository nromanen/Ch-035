package com.crsms.service;

import java.util.List;

import com.crsms.domain.Course;

/**
 * 
 * @author Valerii Motresku
 * @author maftey
 *
 */

public interface CourseService extends BaseService<Course> {
	
	void save(Course course, Long areaId, String ownerEmail);
	
	void update(Course course, Long areaId, String ownerEmail);
	
	void deleteById(Long courseId);
	
	Course get(String name);
	
	List<Course> getAllByAreaId(Long areaId);
	
	void subscribe(Long courseId, String email);
	
	void unsubscribe(Long courseId, String email);
	
	List<Course> getAllByUserId(Long userId);
	
	List<Course> getAllByUserEmail(String email);

	List<Course> getAllByOwnerEmail(String email);
	
	List<Course> searchCourses(String seachWord);
	
	List<Long> getUserCoursesIds(String email);
	
	boolean isUserACourseOwner(Long courseId, String userEmail);

}
