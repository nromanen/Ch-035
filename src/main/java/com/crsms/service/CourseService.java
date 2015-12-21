package com.crsms.service;

import java.util.List;
import java.util.Map;

import com.crsms.domain.Course;
import com.crsms.domain.User;
import com.crsms.dto.CourseViewDto;
import com.crsms.dto.CoursesViewDto;
import com.crsms.dto.CourseModuleNamesPairDto;
import org.springframework.security.access.prepost.PreAuthorize;



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

	CourseViewDto getCourseViewDto(Long courseId, String name);

	CoursesViewDto getAllCourseViewDto(String name);

	/**
	 * 
	 * @param course with initialized modules and tests
	 * 
	 */
	CourseViewDto getCourseViewDto(Course course, User user);

	List<Course> getAllAssociatedWithResource(Long resourceId);

	List<CourseModuleNamesPairDto> getAllCourseModuleNamesPairsAssociatedWithResource(
			Long resourceId);

}
