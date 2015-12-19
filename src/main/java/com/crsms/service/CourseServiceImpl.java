package com.crsms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.CourseDao;
import com.crsms.domain.Course;
import com.crsms.domain.Module;
import com.crsms.dto.CourseModuleNamesPairDto;

/**
 * 
 * @author Valerii Motresku
 * @author maftey
 *
 */

@Service("courseService")
@Transactional
public class CourseServiceImpl extends BaseServiceImpl<Course> implements CourseService {
	
	@Autowired
    private CourseDao courseDao;
	
	@Autowired
	private AreaService areaService;

	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void save(Course course, Long areaId, String ownerEmail) {
		course.setOwner(userService.getUserByEmail(ownerEmail));
		course.setArea(areaService.getById(areaId));
		courseDao.save(course);
	}
	
	@Override
	public void update(Course course, Long areaId, String ownerEmail) {
		course.setOwner(userService.getUserByEmail(ownerEmail));
		course.setArea(areaService.getById(areaId));
		courseDao.update(course);
	}

	@Override
	public void deleteById(Long courseId) {
		this.delete(this.getById(courseId));
	}

	@Override
	public void delete(Course course) {
		course.disable();
		for (Module module : course.getModules()) {
			moduleService.freeResource(module);
		}
		if (!course.getPublished()) {
			courseDao.delete(course);
		}
	}

	@Override
	public List<Course> getAllByAreaId(Long areaId) {
		return courseDao.getAllByAreaId(areaId);
	}
	
	@Override
	public List<Course> getAllByUserEmail(String email) {
		return courseDao.getAllByUserEmail(email);		
	}
	
	@Override
	public List<Course> getAllByOwnerEmail(String email) {
		return courseDao.getAllByOwnerEmail(email);
	}

	@Override
	public List<Course> searchCourses(String searchWord) {
		return courseDao.searchCourses(searchWord);
	}

	@Override
	public boolean isUserACourseOwner(Long courseId, String userEmail) {
		return this.getById(courseId).getOwner().getEmail().equals(userEmail);
	}

	@Override
	public Map<Long, Long> getStudentCoursesAndGroupsIds(String email) {
		return courseDao.getStudentCoursesAndGroupsIds(email);
	}

	@Override
	public void publish(Long courseId) {
		Course course = this.getById(courseId);
		course.setPublished(true);		
	}

	@Override
	public List<Course> getAllPublished() {
		return courseDao.getAllPublished();
	}

	@Override
	public List<Course> getAllAssociatedWithResource(Long resourceId) {
		return courseDao.getAllAssociatedWithResource(resourceId);
	}

	@Override
	public List<CourseModuleNamesPairDto> getAllCourseModuleNamesPairsAssociatedWithResource(
			Long resourceId) {
		return courseDao.getAllCourseModuleNamesPairsAssociatedWithResource(resourceId);
	}

}
