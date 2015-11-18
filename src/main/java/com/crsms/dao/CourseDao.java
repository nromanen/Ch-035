package com.crsms.dao;

import java.util.List;

import com.crsms.domain.Course;
import com.crsms.domain.Test;


/**
 * 
 * @author Valerii Motresku
 *
 */

public interface CourseDao extends BaseDao<Course> {
	
	Course get(String name);
	
	Course getByTest(Test test);
	Course getByTest(Long testId);
	
	List<Course> getAllByAreaId(Long areaId);
	
	List<Course> getAllByUserId(Long userId);

	List<Course> getAllByUserEmail(String email);
	
	List<Course> getAllByOwnerEmail(String email);
	
	void disable(Course course);

	List<Course> searchCourses(String searchWord);
	
	List<Long> getUserCoursesIds(String email);
}
