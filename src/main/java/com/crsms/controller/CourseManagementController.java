package com.crsms.controller;

import java.util.List;

import org.joda.time.Duration;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.crsms.domain.Course;
import com.crsms.service.CourseService;


@Controller
public class CourseManagementController {
	//TODO: only for teacher
	@Autowired
	CourseService courseService;
	
	@RequestMapping(value = "courses", method = RequestMethod.GET)
	public ModelAndView courseManagmentList() {
		ModelAndView model = new ModelAndView();
		List<Course> courses = courseService.getAllCourse();
		model.addObject("title", "Course Management System");
		model.addObject("courses", courses);
		model.setViewName("courseManagmentList");
		return model;
	}
	
	
	
	//TODO: only for teacher
	@RequestMapping(value = { "courses/{courseId}/delete" }, method = RequestMethod.GET)
	public String deleteCourse(@PathVariable("courseId") Long courseId) {
		Course course = courseService.getCourseById(courseId);
		//TODO: check permissions
		courseService.deleteCourse(course);
		
		return "redirect:/courses";

	}
	
	@RequestMapping(value = { "courses/{courseId}/edit" }, method = RequestMethod.GET)
	public ModelAndView editCourse(@PathVariable("courseId") Long courseId) {
		ModelAndView model = new ModelAndView();
	
		
		Course course = courseService.getCourseById(courseId); 
		model.addObject("title", course.getName());
		model.addObject("course", course);
		model.setViewName("editCourse");
		return model;
	}
	
	@RequestMapping(value = { "courses/add" }, method = RequestMethod.GET)
	public ModelAndView editCourse() {
		ModelAndView model = new ModelAndView();
	
		model.addObject("title", "new course");
		model.addObject("course", new Course());
		model.setViewName("newCourse");
		return model;
	}
	
	@RequestMapping(value = { "courses/add" }, method = RequestMethod.POST)
	public ModelAndView editCourseSubmit(Course course) {
		ModelAndView model = new ModelAndView();
		model.setViewName("newCourse");
		return model;
	}

}
