package com.crsms.dao;

import java.util.List;
import java.util.Map;

import com.crsms.domain.Course;
import com.crsms.domain.Question;
import com.crsms.domain.Test;
import com.crsms.dto.CourseModuleNamesPairDto;


/**
 * 
 * @author Valerii Motresku
 *
 */

public interface CourseDao extends BaseDao<Course> {
	
	Course getByTest(Test test);
	Course getByTest(Long testId);
	
	Course getByQuestion(Question question);
	Course getByQuestion(Long questionId);
	
	Course getByModule(Long moduleId);
	
	Course getByAnswer(Long answerId);
	
	List<Course> getAllByAreaId(Long areaId);
	
	List<Course> getAllByUserEmail(String email);
	
	List<Course> getAllByOwnerEmail(String email);

	List<Course> searchCourses(String searchWord);

	Map<Long, Long> getStudentCoursesAndGroupsIds(String email);

	List<Course> getAllPublished();

	List<Course> getAllAssociatedWithResource(Long resourceId);

	List<CourseModuleNamesPairDto> getAllCourseModuleNamesPairsAssociatedWithResource(
			Long resourceId);
}
