package com.crsms.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crsms.domain.Course;
import com.crsms.dto.CourseViewDto;
import com.crsms.dto.CoursesViewDto;
import com.crsms.service.CourseService;
import com.crsms.util.StringUtil;

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
	
	public static final String COURSE_VIEW = "course";
	public static final String COURSES_VIEW = "courses";
	public static final String COURSES_PROGRESS = "coursesProgress";
	
	public static final String ALL_COURSES_REDIRECT = "redirect:/courses/";
	public static final String MY_COURSES_REDIRECT = "redirect:/courses/?show=my";
	  
	@Autowired
	private CourseService courseService;
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN', 'STUDENT')")
	@RequestMapping(value = "/progress", method = RequestMethod.GET)
	public String getProgress(Principal principal, Model model) {
		CoursesViewDto courses = courseService.getAllCourseViewDto(principal.getName());
		
		model.addAttribute("courses", courses);
		return COURSES_PROGRESS;
	}
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN', 'STUDENT')")
	@RequestMapping(value = "/progress/{userId}", method = RequestMethod.GET)
	public String getProgressForUser(@PathVariable Long userId, Principal principal, Model model) {
		CoursesViewDto courses = courseService.getAllCourseViewDto(userId);
		
		model.addAttribute("courses", courses);
		return COURSES_PROGRESS;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAllCourses(HttpServletRequest request, Principal principal, Model model) {
		if (request.isUserInRole("STUDENT")) {
			model.addAttribute("studentCoursesAndGroupsIds",
				courseService.getStudentCoursesAndGroupsIds(principal.getName()));
		}
		model.addAttribute("courses", truncateNameAndDescription(courseService.getAllPublished()));
		return COURSES_VIEW;
	}
	
	@RequestMapping(value = "/", params = "show=my", method = RequestMethod.GET)
	public String getMyCourses(HttpServletRequest request, Principal principal, Model model) {
		if (!request.isUserInRole("STUDENT")) {
			return ALL_COURSES_REDIRECT;
		}
		model.addAttribute("studentCoursesAndGroupsIds",
				courseService.getStudentCoursesAndGroupsIds(principal.getName()));
		model.addAttribute("courses",
				truncateNameAndDescription(courseService.getAllByUserEmail(principal.getName())));
		return COURSES_VIEW;
	}
	
	@RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
	public String getCourse(@PathVariable Long courseId, Principal principal, Model model) {
		CourseViewDto course;
		if(principal != null) {
			course = courseService.getCourseViewDto(courseId, principal.getName());
		} else {
			course = courseService.getCourseViewDto(courseId, null);
		}
		model.addAttribute("course", course);
		model.addAttribute("pageTitle", course.getName());
		model.addAttribute("headerTitle", course.getName());
		return COURSE_VIEW;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchCourses(@RequestParam("searchWord") String searchWord, Model model) {
		List<Course> courses = courseService.searchCourses(searchWord);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("courses", courses);
		return COURSES_VIEW;
	}
	
	private List<Course> truncateNameAndDescription(List<Course> courses) {
		for (Course course : courses) {
			course.setDescription(StringUtil.trimString(course.getDescription(),
														COURSE_DESC_LENGTH, true));
			course.setName(StringUtil.trimString(course.getName(), 
														COURSE_TITLE_LENGTH, true));
		}
		return courses;
	}
	
}