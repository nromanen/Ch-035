package com.crsms.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.CourseDao;
import com.crsms.dao.UserDao;
import com.crsms.domain.Course;
import com.crsms.domain.User;

/**
 * 
 * @author Valerii Motresku
 * @author maftey
 *
 */

@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService {
	
	@Autowired
    private CourseDao courseDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AreaService areaService;
	
	@Override
	public void saveCourse(Course course) {
		courseDao.saveCourse(course);

	}
	
	@Override
	public void saveCourse(Course course, long areaId, int sweekDuration) {
		course.setWeekDuration(sweekDuration);
		course.setArea(areaService.getAreaById(areaId));
		courseDao.saveCourse(course);

	}

	@Override
	public List<Course> getAllCourse() {
		return courseDao.getAll();
	}
	
	@Override
	public List<Course> getAllInitialized() {
		return courseDao.getAllInitialized();
	}

	@Override
	public Course getCourseById(Long id) {
		return courseDao.getCourseById(id);
	}

	@Override
	public void updateCourse(Course course) {
		courseDao.updateCourse(course);

	}
	
	@Override
	public void updateCourse(Course course, long areaId, int sweekDuration) {
		course.setWeekDuration(sweekDuration);
		course.setArea(areaService.getAreaById(areaId));
		courseDao.updateCourse(course);

	}

	@Override
	public Course getCourse(String name) {
		return courseDao.getCourse(name);
	}

	@Override
	public void deleteCourse(Course course) {
		courseDao.deleteCourse(course);
	}

	@Override
	public List<Course> getAllByAreaId(Long areaId) {
		return courseDao.getAllByAreaId(areaId);
	}
	
	@Override
	public void subscribe(Long courseId, Long userId) {
		Course course = courseDao.getCourseById(courseId);
		User user = userDao.getUserById(userId);
		course.addUser(user);
		courseDao.updateCourse(course);
	}
	
	@Override
	public void unsubscribe(Long courseId, Long userId) {
		Course course = courseDao.getCourseById(courseId);
		User user = userDao.getUserById(userId);
		course.deleteUser(user);
		courseDao.updateCourse(course);
	}
	
	public List<Course> getAllWithInitializedUsers() {
		List<Course> courses = courseDao.getAllCourse();
		for (Course course : courses) {
			Hibernate.initialize(course.getUsers());
		}
		return courses;
	}
}
