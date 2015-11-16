package com.crsms.controller;

import java.util.LinkedList;
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
import com.crsms.dto.CourseFormDto;
import com.crsms.service.AreaService;
import com.crsms.service.CourseService;
import com.crsms.service.DtoService;
import com.crsms.service.UserService;
import com.crsms.util.StringUtil;
import com.crsms.validator.CourseFormValidator;

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
	
	private StringUtil stringUtil = new StringUtil();
	
	@Autowired
	private AreaService areaService;
	
	//TODO: only for teacher
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private DtoService dtoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseFormValidator validator;
	
	@InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView showCourses(HttpServletRequest request,
			@RequestParam (value = "show", required = false, defaultValue = "all") String show) {
		
		ModelAndView model = new ModelAndView();
		
		String currentPrincipalEmail = null;
		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			currentPrincipalEmail = SecurityContextHolder.getContext()
														 .getAuthentication().getName();
		}
		
		List<Long> userCoursesId = null;
		if (request.isUserInRole("ROLE_STUDENT")) {
			userCoursesId = this.getAllUserCoursesId(currentPrincipalEmail);
		}
		
		model.setViewName("courses");
		
		List<Course> courses = null;
		switch (show) {
			case "my": 
				if (request.isUserInRole("ROLE_STUDENT")) {
					courses = courseService.getAllByUserEmail(currentPrincipalEmail);
				} else if (request.isUserInRole("ROLE_TEACHER")
				   		|| request.isUserInRole("ROLE_ADMIN")) {
					courses = courseService.getAllByOwnerEmail(currentPrincipalEmail);
					model.setViewName("courses_table");
				}
				break;
			case "all": 
				courses = courseService.getAll();
				break;
			default: 
				courses = courseService.getAll();
				break;
		}	
		
		courses = this.truncateNameAndDescription(courses);
		
		model.addObject("courses", courses);
		model.addObject("userCoursesId", userCoursesId);
		
		return model;
	}

	@RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
	public ModelAndView showCourse(@PathVariable Long courseId) {
		ModelAndView model = new ModelAndView();
		Course course = courseService.getById(courseId);
		model.addObject("course", course);
		model.addObject("courseEndDate", course.getStartDate().plusDays(course.getDuration()));
		model.addObject("pageTitle", course.getName());
		model.addObject("headerTitle", course.getName());
		model.setViewName("course");
		return model;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView newCourse() {
		ModelAndView model = new ModelAndView();
		
		Course course = new Course();
		CourseFormDto courseFormDto = dtoService.convert(course, CourseFormDto.class, Course.class);
		String ownerEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		courseFormDto.setOwnerEmail(ownerEmail);
		
		List<Area> areas = areaService.getAllAreas();
		
		model.addObject("courseFormDto", courseFormDto);
		model.addObject("areas", areas);
		model.setViewName("newFormCourse");
		return model;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView newCourseSubmit(
						@RequestParam("areaId") Long areaId,
						/*@Validated*/ CourseFormDto courseFormDto, BindingResult result) {
	
		ModelAndView model = new ModelAndView();
		
		validator.validate(courseFormDto, result);
		if (result.hasErrors()) {
			List<Area> areas = areaService.getAllAreas();
			model.addObject("areas", areas);
			model.setViewName("newFormCourse");
			return model;
		}
		
		Course course = dtoService.convert(courseFormDto, Course.class, CourseFormDto.class);
		String ownerEmail = courseFormDto.getOwnerEmail();
		courseService.save(course, areaId, ownerEmail);
		model.setViewName("redirect:/courses/");
		return model;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
	@RequestMapping(value = "/{courseId}/edit", method = RequestMethod.GET)
	public ModelAndView editCourse(@PathVariable("courseId") Long courseId) {
		ModelAndView model = new ModelAndView();
	
		Course course = courseService.getById(courseId); 
		
		if (!isCurrentPrincipalAnOwner(course)) {
			model.setViewName("403");
			return model;
		}
		
		String ownerEmail = course.getOwner().getEmail();
		CourseFormDto courseFormDto = dtoService.convert(course, CourseFormDto.class, Course.class);
		courseFormDto.setOwnerEmail(ownerEmail);
		
		List<Area> areas = areaService.getAllAreas();
		
		model.addObject("courseFormDto", courseFormDto);
		model.addObject("areas", areas);
		model.setViewName("editFormCourse");
		return model;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
	@RequestMapping(value = "/{courseId}/edit", method = RequestMethod.POST)
	public ModelAndView editCourseSubmit(
						@RequestParam("areaId") Long areaId,
						/*@Validated*/ CourseFormDto courseFormDto, BindingResult result) {
		
		ModelAndView model = new ModelAndView();
		
		validator.validate(courseFormDto, result);
		if (result.hasErrors()) {
			List<Area> areas = areaService.getAllAreas();
			model.addObject("areas", areas);
			model.setViewName("editFormCourse");
			return model;
		}
		
		Course course = dtoService.convert(courseFormDto, Course.class, CourseFormDto.class);
		String ownerEmail = courseFormDto.getOwnerEmail();
		
		courseService.update(course, areaId, ownerEmail);
		model.setViewName("redirect:/courses/");
		return model;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
	@RequestMapping(value = "/{courseId}/delete", method = RequestMethod.GET)
	public ModelAndView deleteCourse(@PathVariable("courseId") Long courseId) {
		ModelAndView model = new ModelAndView();
		
		Course course = courseService.getById(courseId);

		if (!isCurrentPrincipalAnOwner(course)) {
			model.setViewName("403");
			return model;
		}
		
		courseService.deleteCourse(course);
		
		model.setViewName("redirect:/courses/");
		
		return model;
	}
	
	@RequestMapping(value = "/{courseId}/enroll", method = RequestMethod.GET)
	public String subscribe(@PathVariable("courseId") Long courseId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		courseService.subscribe(courseId, email);
		return "redirect:/courses/?show=my";
	}
	
	@RequestMapping(value = "/{courseId}/leave", method = RequestMethod.GET)
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
	
	private boolean isCurrentPrincipalAnOwner(Course course) {		
		String currentPrincipalEmail = SecurityContextHolder.getContext()
										.getAuthentication().getName();
		return course.getOwner().getEmail().equals(currentPrincipalEmail);
	}
	
	private List<Course> truncateNameAndDescription(List<Course> courses) {
		for (Course course : courses) {
			course.setDescription(stringUtil.trimString(course.getDescription(),
														COURSE_DESC_LENGTH, true));
			course.setName(stringUtil.trimString(course.getName(), 
														COURSE_TITLE_LENGTH, true));
		}
		return courses;
	}
}
