package com.crsms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.crsms.domain.Area;
import com.crsms.domain.Course;
import com.crsms.domain.User;
import com.crsms.dto.CourseJsonDto;
import com.crsms.service.AreaService;
import com.crsms.service.CourseService;
import com.crsms.service.DtoService;
import com.crsms.service.UserService;
import com.crsms.service.hibernate.initializer.CourseModulesDeepInitializer;
import com.crsms.service.hibernate.initializer.UserCoursesInitializer;
import com.crsms.util.Invocable;
import com.crsms.util.StringUtil;
import com.crsms.validator.CourseJsonValidator;

/**
 * 
 * @author maftey
 * @author Valerii Motresku
 * @author St. Roman
 *
 */

@Controller
@RequestMapping(value = "/courses")
public class CourseController {
	
	private static final int COURSE_DESC_LENGTH = 300;
	private static final int COURSE_TITLE_LENGTH = 40;
	
	private static final String COURSE_VIEW = "course";
	private static final String COURSES_VIEW = "courses";
	private static final String COURSES_TABLE_VIEW = "courses_table";
	private static final String ADD_COURSE_VIEW = "newFormCourse";
	private static final String EDIT_COURSE_VIEW = "editFormCourse";
	private static final String ACCESS_DENIED_VIEW = "403";
	
	private static final String MY_COURSES_REDIRECT = "redirect:/courses/?show=my";
	private static final String ALL_COURSES_REDIRECT = "redirect:/courses/?show=all";
	
	private static final String SHOW_ALL = "all";
	private static final String SHOW_MY = "my";
	
	private StringUtil stringUtil = new StringUtil();
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private DtoService dtoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseJsonValidator validator;

	@InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getCourses(HttpServletRequest request,
			@RequestParam (value = "show", required = false, defaultValue = SHOW_ALL) String show) {
		
		ModelAndView model = new ModelAndView();
		
		String currentPrincipalEmail = null;
		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			currentPrincipalEmail = this.getCurrentPrincipalEmail();
		}
		
		List<Long> userCoursesId = null;
		if (request.isUserInRole("STUDENT")) {
			userCoursesId = courseService.getUserCoursesIds(currentPrincipalEmail);
		}
		
		List<Course> courses = this.setUpCoursesAndView(request, show, model, 
														currentPrincipalEmail);
		
		truncateNameAndDescription(courses);
		
		model.addObject("courses", courses);
		model.addObject("userCoursesId", userCoursesId);
		
		return model;
	}

	@RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
	public ModelAndView getCourse(@PathVariable Long courseId) {
		ModelAndView model = new ModelAndView();
		List<Invocable<Course>> initializers = new ArrayList<>();
		initializers.add(new CourseModulesDeepInitializer());
		Course course = courseService.getById(courseId, initializers);
		model.addObject("course", course);
		model.addObject("courseEndDate", course.getStartDate().plusDays(course.getDuration()));
		model.addObject("pageTitle", course.getName());
		model.addObject("headerTitle", course.getName());
		model.setViewName(COURSE_VIEW);
		return model;
	}
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addCourse() {
		ModelAndView model = new ModelAndView();
		
		Course course = new Course();
		CourseJsonDto courseJsonDto = dtoService.convert(course, CourseJsonDto.class, Course.class);
		String ownerEmail = this.getCurrentPrincipalEmail();
		courseJsonDto.setOwnerEmail(ownerEmail);
		
		List<Area> areas = areaService.getAllAreas();
		
		model.addObject("courseJsonDto", courseJsonDto);
		model.addObject("areas", areas);
		model.setViewName(ADD_COURSE_VIEW);
		return model;
	}
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addCourseSubmit(
						@RequestParam("areaId") Long areaId,
						/*@Validated*/ CourseJsonDto courseJsonDto, BindingResult result) {
	
		ModelAndView model = new ModelAndView();
		
		validator.validate(courseJsonDto, result);
		if (result.hasErrors()) {
			List<Area> areas = areaService.getAllAreas();
			model.addObject("areas", areas);
			model.setViewName(ADD_COURSE_VIEW);
			return model;
		}
		
		Course course = dtoService.convert(courseJsonDto, Course.class, CourseJsonDto.class);
		String ownerEmail = courseJsonDto.getOwnerEmail();
		courseService.save(course, areaId, ownerEmail);
		model.setViewName(MY_COURSES_REDIRECT);
		return model;
	}
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	@RequestMapping(value = "/{courseId}/edit", method = RequestMethod.GET)
	public ModelAndView editCourse(@PathVariable("courseId") Long courseId) {
		ModelAndView model = new ModelAndView();
		
		if (!courseService.isUserACourseOwner(courseId, this.getCurrentPrincipalEmail())) {
			model.setViewName(ACCESS_DENIED_VIEW);
			return model;
		}
		
		Course course = courseService.getById(courseId);
		String ownerEmail = course.getOwner().getEmail();
		CourseJsonDto courseJsonDto = dtoService.convert(course, CourseJsonDto.class, Course.class);
		courseJsonDto.setOwnerEmail(ownerEmail);
		
		List<Area> areas = areaService.getAllAreas();
		
		model.addObject("courseJsonDto", courseJsonDto);
		model.addObject("areas", areas);
		model.setViewName(EDIT_COURSE_VIEW);
		return model;
	}
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	@RequestMapping(value = "/{courseId}/edit", method = RequestMethod.POST)
	public ModelAndView editCourseSubmit(
						@RequestParam("areaId") Long areaId,
						/*@Validated*/ CourseJsonDto courseJsonDto, BindingResult result) {
		ModelAndView model = new ModelAndView();
		
		validator.validate(courseJsonDto, result);
		if (result.hasErrors()) {
			List<Area> areas = areaService.getAllAreas();
			model.addObject("areas", areas);
			model.setViewName(EDIT_COURSE_VIEW);
			return model;
		}
		
		Course course = dtoService.convert(courseJsonDto, Course.class, CourseJsonDto.class);
		String ownerEmail = courseJsonDto.getOwnerEmail();
		
		courseService.update(course, areaId, ownerEmail);
		model.setViewName(MY_COURSES_REDIRECT);
		return model;
	}
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	@RequestMapping(value = "/{courseId}/delete", method = RequestMethod.GET)
	public ModelAndView deleteCourse(@PathVariable("courseId") Long courseId) {
		ModelAndView model = new ModelAndView();
		
		if (!courseService.isUserACourseOwner(courseId, this.getCurrentPrincipalEmail())) {
			model.setViewName(ACCESS_DENIED_VIEW);
			return model;
		}
		
		courseService.deleteById(courseId);
		model.setViewName(MY_COURSES_REDIRECT);
		return model;
	}
	
	@RequestMapping(value = "/{courseId}/enroll", method = RequestMethod.GET)
	public ModelAndView subscribe(@PathVariable("courseId") Long courseId) {
		ModelAndView model = new ModelAndView();
		String email = this.getCurrentPrincipalEmail();
		courseService.subscribe(courseId, email);
		model.setViewName(MY_COURSES_REDIRECT);
		return model;
	}
	
	@RequestMapping(value = "/{courseId}/leave", method = RequestMethod.GET)
	public ModelAndView unsubscribe(@PathVariable("courseId") Long courseId) {
		ModelAndView model = new ModelAndView();
		String email = this.getCurrentPrincipalEmail();
		courseService.unsubscribe(courseId, email);
		
		List<Invocable<User>> invocables = new ArrayList<>();
		invocables.add(new UserCoursesInitializer());
		if (userService.getUserByEmail(email, invocables).getCourses().isEmpty()) {
			model.setViewName(ALL_COURSES_REDIRECT);
		} else {
			model.setViewName(MY_COURSES_REDIRECT);
		}
		return model;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchCourses(@RequestParam("searchWord") String searchWord) {
		ModelAndView model = new ModelAndView();
		List<Course> courses = courseService.searchCourses(searchWord);
		model.addObject("courses", courses);
		model.setViewName(COURSES_VIEW);
		return model;
	}
	
	private String getCurrentPrincipalEmail() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	private void truncateNameAndDescription(List<Course> courses) {
		for (Course course : courses) {
			course.setDescription(stringUtil.trimString(course.getDescription(),
														COURSE_DESC_LENGTH, true));
			course.setName(stringUtil.trimString(course.getName(), 
														COURSE_TITLE_LENGTH, true));
		}
	}
	
	private List<Course> setUpCoursesAndView(HttpServletRequest request,
			String show, ModelAndView model, String currentPrincipalEmail) {
		model.setViewName(COURSES_VIEW);
		List<Course> courses = null;
		switch (show) {
			case SHOW_MY: 
				if (request.isUserInRole("STUDENT")) {
					courses = courseService.getAllByUserEmail(currentPrincipalEmail);
				} else if (request.isUserInRole("TEACHER")
				   		|| request.isUserInRole("ADMIN")) {
					courses = courseService.getAllByOwnerEmail(currentPrincipalEmail);
					model.setViewName(COURSES_TABLE_VIEW);
				}
				break;
			case SHOW_ALL: 
				courses = courseService.getAll();
				break;
			default: 
				courses = courseService.getAll();
				break;
		}
		return courses;
	}
}
