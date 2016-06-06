package com.crsms.service;

import com.crsms.dao.CourseDao;
import com.crsms.dao.GroupDao;
import com.crsms.domain.Course;
import com.crsms.domain.Group;
import com.crsms.domain.Module;
import com.crsms.domain.User;
import com.crsms.dto.CourseModuleNamesPairDto;
import com.crsms.dto.CourseViewDto;
import com.crsms.dto.CoursesViewDto;
import com.crsms.dto.ModuleViewDto;
import com.crsms.service.hibernate.initializer.CourseModulesDeepInitializer;
import com.crsms.util.Invocable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
    private  GroupDao groupDao;
	
	@Autowired
    private CourseDao courseDao;
	
	@Autowired
	private AreaService areaService;

	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DtoService dtoService;
	
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
		course.setModules(moduleService.getAllByCourseId(course.getId()));
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
	public Course getCourseByModuleId(Long moduleId) {
		return courseDao.getByModule(moduleId);
	}

	@Override
	public List<Course> getAllPublished() {
		return courseDao.getAllPublished();
	}
	
	@Override
	public CourseViewDto getCourseViewDto(Long courseId, String email) {
		User user = null;
		
		if (email != null) user = userService.getUserByEmail(email);
		
		List<Invocable<Course>> initializers = new ArrayList<>();
		initializers.add(new CourseModulesDeepInitializer());
		Course course = this.getById(courseId, initializers);
		
		return this.getCourseViewDto(course, user);
	}
	@Override
	public CourseViewDto getCourseViewDto(Course course, User user) {
		
		CourseViewDto courseViewDto = dtoService.convert(course, CourseViewDto.class, Course.class);
		if (user == null) return courseViewDto;
		boolean complete = true;
		boolean pass = true;
		double score = 0;
		double totalScore = 0;
		Integer passedModules = 0;
		Integer allModule = 0;
		Integer failedModule = 0;
		for (ModuleViewDto moduleViewDto : courseViewDto.getModules()) {
			moduleService.initModuleViewDto(moduleViewDto, user);
			
			if (moduleViewDto.getComplete()) {
				score += moduleViewDto.getScore();
				if (moduleViewDto.getPass()) {
					passedModules++;
				} else {
					failedModule++;
					pass = false;
				}
			} else {
				complete = false;
				pass = false;
			}
			
			allModule++;
			totalScore += moduleViewDto.getTotalScore();
		}
		
		Group group = groupDao.getByCourseAndUser(course.getId(), user.getEmail());
		
		courseViewDto.setGroup(group);
		courseViewDto.setComplete(complete);
		courseViewDto.setPass(pass);
		courseViewDto.setScore(score);
		courseViewDto.setTotalScore(totalScore);
		courseViewDto.setAllModule(allModule);
		courseViewDto.setPassedModules(passedModules);
		courseViewDto.setFailedModule(failedModule);
		return courseViewDto;
	}
	
	@Override
	public CoursesViewDto getAllCourseViewDto(String email) {
		User user = userService.getUserByEmail(email);
		
		return getAllCourseViewDto(user);
	}
	
	@Override
	public CoursesViewDto getAllCourseViewDto(Long userId) {
		User user = userService.getById(userId);
		
		return getAllCourseViewDto(user);
	}

	@Override
	public CoursesViewDto getAllCourseViewDto(User user) {
		List<CourseViewDto> courseViewDtos = new ArrayList<>();
		List<Course> courses = getAllByUserEmail(user.getEmail());
		
		CourseModulesDeepInitializer initializer = new CourseModulesDeepInitializer();
		
		int allCurses = 0;
		int passedCurses = 0;
		int failedCurses = 0;
		double score = 0;
		double maxScore = 0;
		for (Course course: courses) {
			initializer.invoke(course);
			CourseViewDto courseViewDto = this.getCourseViewDto(course, user);
			courseViewDtos.add(courseViewDto);
			
			allCurses++;
			maxScore += courseViewDto.getTotalScore();
			score += courseViewDto.getScore();
			
			if (!courseViewDto.getComplete()) continue;
			
			if (courseViewDto.getPass()) {
				passedCurses++;
			} else {
				failedCurses++;
			}
			
			
		}
		
		CoursesViewDto coursesViewDto = new CoursesViewDto();
		coursesViewDto.setUser(user);
		coursesViewDto.setCourseViewDtos(courseViewDtos);
		coursesViewDto.setAllCurses(allCurses);
		coursesViewDto.setPassedCurses(passedCurses);
		coursesViewDto.setFailedCurses(failedCurses);
		coursesViewDto.setContinuedCurses(allCurses - passedCurses - failedCurses);
		coursesViewDto.setScore(score);
		coursesViewDto.setMaxScore(maxScore);
		if (maxScore > 0) {
			coursesViewDto.setProgress(score * 100 / maxScore);
		} else {
			coursesViewDto.setProgress(0.);
		}
		
		return coursesViewDto;
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
