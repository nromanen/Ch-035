package com.crsms.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.crsms.service.AreaService;
import com.crsms.service.CourseService;
import com.crsms.service.UserService;
import com.crsms.util.StringUtil;
import com.crsms.validator.CourseValidator;

/**
 * 
 * @author maftey
 * @author Valerii Motresku
 *
 */

@Controller
@RequestMapping(value = "/courses")
public class CourseController {
	
	private static final int COURSE_DESC_LENGTH = 300;
	private static final int COURSE_TITLE_LENGTH = 40;
	
	@Autowired
	private StringUtil stringUtil;
	
	//TODO: only for teacher
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseValidator validator;
	
	@InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView showCourses(HttpServletRequest request,
			@RequestParam (value = "show", required = false, defaultValue = "all") String show) {
		
		ModelAndView model = new ModelAndView();
		
		String email = null;
		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			email = SecurityContextHolder.getContext().getAuthentication().getName();
		}
		
		List<Long> userCoursesId = null;
		if (request.isUserInRole("ROLE_STUDENT")) {
			email = SecurityContextHolder.getContext().getAuthentication().getName();
			userCoursesId = this.getAllUserCoursesId(email);
		}
		
		List<Course> courses = null;
		switch (show) {
			case "my": 
				courses = courseService.getAllByUserEmail(email);
				break;
			case "all": 
				courses = courseService.getAllCourse();
				break;
			default: 
				courses = courseService.getAllCourse();
				break;
		}
		
		for (Course course : courses) {
			course.setDescription(stringUtil.trimString(course.getDescription(),
														COURSE_DESC_LENGTH, true));
			course.setName(stringUtil.trimString(course.getName(), COURSE_TITLE_LENGTH, true));
		}
		
		model.addObject("courses", courses);
		model.addObject("userCoursesId", userCoursesId);
		model.setViewName("courses");
		return model;
	}
	
	
	
	//TODO: only for teacher
	@RequestMapping(value = "/{courseId}/delete", method = RequestMethod.GET)
	public String deleteCourse(@PathVariable("courseId") Long courseId) {
		Course course = courseService.getCourseById(courseId);
		//TODO: check permissions
		courseService.deleteCourse(course);
		
		return "redirect:/courses/";

	}
	
	@RequestMapping(value = "/{courseId}/edit", method = RequestMethod.GET)
	public ModelAndView editCourse(@PathVariable("courseId") Long courseId) {
		ModelAndView model = new ModelAndView();
	
		Course course = courseService.getCourseById(courseId); 
		List<Area> areas = areaService.getAllAreas();
		model.addObject("course", course);
		model.addObject("areas", areas);
		model.setViewName("editFormCourse");
		return model;
	}
	
	@RequestMapping(value = "/{courseId}/edit", method = RequestMethod.POST)
	public ModelAndView editCourseSubmit(
			@RequestParam("weekDuration") int sweekDuration,
			@RequestParam("areaId") long areaId, @Validated Course course, 
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		
		if (result.hasErrors()) {
			List<Area> areas = areaService.getAllAreas();
			model.addObject("course", course);
			model.addObject("areas", areas);
			model.setViewName("editFormCourse");
			return model;
		}
		courseService.updateCourse(course, areaId, sweekDuration);
		model.setViewName("redirect:/courses/");
		return model;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView newCourse() {
		List<Area> areas = areaService.getAllAreas();
		
		ModelAndView model = new ModelAndView();
		
		model.addObject("course", new Course());
		model.addObject("areas", areas);
		model.setViewName("newFormCourse");
		return model;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView newCourseSubmit(
			@RequestParam("weekDuration") int sweekDuration, 
			@RequestParam("areaId") long areaId, @Validated Course course, 
			BindingResult result) {
		
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			List<Area> areas = areaService.getAllAreas();
			model.addObject("course", course);
			model.addObject("areas", areas);
			model.setViewName("newFormCourse");
			return model;
		}
		courseService.saveCourse(course, areaId, sweekDuration);
		model.setViewName("redirect:/courses/");
		return model;
	}
	
	@RequestMapping(value = "/{courseId}/enroll", method = RequestMethod.GET)
	public String subscribe(@PathVariable("courseId") Long courseId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		courseService.subscribe(courseId, email);
		return "redirect:/courses/?show=my";
	}
	
	@RequestMapping(value = "/{courseId}/unsubscribe", method = RequestMethod.GET)
	public String unsubscribe(@PathVariable("courseId") Long courseId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		courseService.unsubscribe(courseId, email);
		return "redirect:/courses/?show=my";
	}
	
	private List<Long> getAllUserCoursesId(String email) {
		List<Long> list = new LinkedList<Long>();
		for (Course course : courseService.getAllByUserEmail(email)) {
			list.add(course.getId());
		}
		return list;
	}
}
