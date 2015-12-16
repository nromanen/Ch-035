package com.crsms.service;

import java.util.List;
import java.util.Map;

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
	
	List<Course> getAllByAreaId(Long areaId);
	
	List<Course> getAllByUserEmail(String email);

	List<Course> getAllByOwnerEmail(String email);
	
	List<Course> searchCourses(String seachWord);
	
	boolean isUserACourseOwner(Long courseId, String userEmail);

	Map<Long, Long> getStudentCoursesAndGroupsIds(String email);

	void publish(Long courseId);

	List<Course> getAllPublished();

}
