package com.crsms.dao;

import java.util.List;

import com.crsms.domain.Course;


/**
 * 
 * @author Valerii Motresku
 *
 */

public interface CourseDao extends BaseDao<Course> {
	
	List<Course> getAllInitialized();
	
	Course get(String name);
	
	List<Course> getAllByAreaId(Long areaId);
	
	List<Course> getAllByUserId(Long userId);

	List<Course> getAllByUserEmail(String email);

	boolean hasSubscribedUsers(Long courseId);
	
	boolean hasTestResults(Long courseId);
}
