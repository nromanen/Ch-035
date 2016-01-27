package com.crsms.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crsms.domain.Course;
import com.crsms.dto.CourseJsonDto;
import com.crsms.service.AreaService;
import com.crsms.service.CourseService;
import com.crsms.service.DtoService;
import com.crsms.validator.CourseFormValidator;

/**
 * 
 * @author St. Roman
 *
 */

@Controller
@RequestMapping("/private/courses")
public class PrivateCourseController {
	public static final String COURSES_VIEW = "courses_table";
	public static final String ADD_COURSE_VIEW = "newFormCourse";
	public static final String EDIT_COURSE_VIEW = "editFormCourse";
	public static final String ACCESS_DENIED_VIEW = "403";
	
	public static final String COURSES_REDIRECT = "redirect:/private/courses/";
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private DtoService dtoService;
	
	@Autowired
	private CourseFormValidator validator;
	
	@InitBinder("courseJsonDto")
	private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getCourses(Principal principal, Model model) {
		model.addAttribute("courses", courseService.getAllByOwnerEmail(principal.getName()));
		return COURSES_VIEW;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addCourse(Principal principal, Model model) {
		CourseJsonDto courseJsonDto = new CourseJsonDto();
		courseJsonDto.setOwnerEmail(principal.getName());
		model.addAttribute("courseJsonDto", courseJsonDto);
		model.addAttribute("areas", areaService.getAll());
		return ADD_COURSE_VIEW;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String submitAdding(@RequestParam Long areaId, @Validated CourseJsonDto courseJsonDto,
			BindingResult result, Principal principal, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("areas", areaService.getAll());
			return ADD_COURSE_VIEW;
		}
		Course course = dtoService.convert(courseJsonDto, Course.class, CourseJsonDto.class);
		courseService.save(course, areaId, principal.getName());
		return COURSES_REDIRECT;
	}

	@RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
	public String editCourse(@PathVariable Long courseId, Principal principal, Model model) {
		if (!courseService.isUserACourseOwner(courseId, principal.getName())) {
			return ACCESS_DENIED_VIEW;
		}
		CourseJsonDto courseJsonDto = dtoService.convert(courseService.getById(courseId),
				CourseJsonDto.class, Course.class);
		courseJsonDto.setOwnerEmail(principal.getName());
		model.addAttribute("areas", areaService.getAll());
		model.addAttribute("courseJsonDto", courseJsonDto);
		return EDIT_COURSE_VIEW;
	}

	@RequestMapping(value = "/{courseId}", method = RequestMethod.POST)
	public String submitEditing(@RequestParam Long areaId, @Validated CourseJsonDto courseJsonDto,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("areas", areaService.getAll());
			return EDIT_COURSE_VIEW;
		}
		Course course = dtoService.convert(courseJsonDto, Course.class, CourseJsonDto.class);
		courseService.update(course, areaId, courseJsonDto.getOwnerEmail());
		return COURSES_REDIRECT;
	}

	@RequestMapping(value = "/{courseId}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable Long courseId, Principal principal) {
		if (!courseService.isUserACourseOwner(courseId, principal.getName())) {
			return ACCESS_DENIED_VIEW;
		}
		courseService.deleteById(courseId);
		return COURSES_REDIRECT;
	}

	@RequestMapping(value = "/{courseId}/publish", method = RequestMethod.GET)
	public String publish(@PathVariable Long courseId, Principal principal) {
		if (!courseService.isUserACourseOwner(courseId, principal.getName())) {
			return ACCESS_DENIED_VIEW;
		}
		courseService.publish(courseId);
		return COURSES_REDIRECT;
	}
}
