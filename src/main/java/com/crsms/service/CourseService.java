package com.crsms.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;

import com.crsms.domain.Course;

/**
 * 
 * @author Valerii Motresku
 * @author maftey
 *
 */

public interface CourseService extends BaseService<Course> {
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	void save(Course course, Long areaId, String ownerEmail);
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	void update(Course course, Long areaId, String ownerEmail);
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	void deleteById(Long courseId);
	
	List<Course> getAllByAreaId(Long areaId);
	
	@PreAuthorize("hasAnyRole('STUDENT')")
	List<Course> getAllByUserEmail(String email);
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	List<Course> getAllByOwnerEmail(String email);
	
	List<Course> searchCourses(String seachWord);
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	boolean isUserACourseOwner(Long courseId, String userEmail);

	@PreAuthorize("hasAnyRole('STUDENT')")
	Map<Long, Long> getStudentCoursesAndGroupsIds(String email);
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	void publish(Long courseId);
	
	List<Course> getAllPublished();

}
