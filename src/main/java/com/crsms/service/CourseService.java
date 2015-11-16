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
	
	void save(Course course, long areaId, int sweekDuration);
	
	List<Course> getAllInitialized();
	
	Course getInitializedById(Long id, Course.LazyField ... lazyFields);
	
	void update(Course course, long areaId, int sweekDuration);
	
	Course get(String name);
	
	List<Course> getAllByAreaId(Long areaId);
	
	void subscribe(Long courseId, String email);
	
	void unsubscribe(Long courseId, String email);
	
	List<Course> getAllByUserId(Long userId);
	
	List<Course> getAllByUserEmail(String email);


}
