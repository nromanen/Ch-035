package com.crsms.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.CourseDao;
import com.crsms.dao.UserDao;
import com.crsms.domain.Course;
import com.crsms.domain.Module;
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
	
	@Autowired
	private ModuleService moduleService;
	
	@Override
	public void save(Course course) {
		courseDao.save(course);

	}
	
	@Override
	public void save(Course course, long areaId, int sweekDuration) {
		course.setWeekDuration(sweekDuration);
		course.setArea(areaService.getAreaById(areaId));
		courseDao.save(course);

	}

	@Override
	public List<Course> getAll() {
		return courseDao.getAll();
	}
	
	@Override
	public List<Course> getAllInitialized() {
		return courseDao.getAllInitialized();
	}

	@Override
	public Course getById(Long id) {
		return courseDao.getById(id);
	}

	@Override
	public void update(Course course) {
		courseDao.update(course);

	}
	
	@Override
	public void update(Course course, long areaId, int sweekDuration) {
		course.setWeekDuration(sweekDuration);
		course.setArea(areaService.getAreaById(areaId));
		courseDao.update(course);

	}

	@Override
	public Course get(String name) {
		return courseDao.get(name);
	}

	@Override
	public void deleteCourse(Course course) {
		//ToDO: delete all(module, test ...)
		if(courseDao.hasSubscribedUsers(course.getId()) && courseDao.hasTestResults(course.getId())) {
			course.setDisable(true);
			courseDao.update(course);
		} else {
			/*for(Module module : course.getModules()){
				moduleService.delete(module);
			}*/
			courseDao.delete(course);
		}
	}

	@Override
	public List<Course> getAllByAreaId(Long areaId) {
		return courseDao.getAllByAreaId(areaId);
	}
	
	@Override
	public void subscribe(Long courseId, Long userId) {
		Course course = courseDao.getById(courseId);
		User user = userDao.getUserById(userId);
		course.addUser(user);
		courseDao.update(course);
	}
	
	@Override
	public void unsubscribe(Long courseId, Long userId) {
		Course course = courseDao.getById(courseId);
		User user = userDao.getUserById(userId);
		course.deleteUser(user);
		courseDao.update(course);
	}
	
	public List<Course> getAllWithInitializedUsers() {
		List<Course> courses = courseDao.getAll();
		for (Course course : courses) {
			Hibernate.initialize(course.getUsers());
		}
		return courses;
	}
	
	
}
