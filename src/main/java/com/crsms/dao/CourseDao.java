package com.crsms.dao;

import java.util.List;

import com.crsms.domain.Course;
import com.crsms.domain.Question;
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
	
	Course getByQuestion(Question question);
	Course getByQuestion(Long questionId);
	
	Course getByModule(Long moduleId);
	
	Course getByAnswer(Long answerId);
	
	List<Course> getAllByAreaId(Long areaId);
	
	List<Course> getAllByUserId(Long userId);

	List<Course> getAllByUserEmail(String email);
	
	List<Course> getAllByOwnerEmail(String email);

	List<Course> searchCourses(String searchWord);
	
	List<Long> getUserCoursesIds(String email);
}
