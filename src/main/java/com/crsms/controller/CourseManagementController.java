package com.crsms.controller;

import java.util.List;

import org.joda.time.Duration;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.crsms.domain.Area;
import com.crsms.domain.Course;
import com.crsms.service.AreaService;
import com.crsms.service.CourseService;


@Controller
public class CourseManagementController {
	//TODO: only for teacher
	@Autowired
	CourseService courseService;
	
	@Autowired
	private AreaService areaService;
	
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
	@RequestMapping(value = "courses/{courseId}/delete", method = RequestMethod.GET)
	public String deleteCourse(@PathVariable("courseId") Long courseId) {
		Course course = courseService.getCourseById(courseId);
		//TODO: check permissions
		courseService.deleteCourse(course);
		
		return "redirect:/courses";

	}
	
	@RequestMapping(value = "courses/{courseId}/edit", method = RequestMethod.GET)
	public ModelAndView editCourse(@PathVariable("courseId") Long courseId) {
		ModelAndView model = new ModelAndView();
	
		
		Course course = courseService.getCourseById(courseId); 
		List<Area> areas = areaService.getAllAreas();
		model.addObject("title", course.getName());
		model.addObject("course", course);
		model.addObject("areas", areas);
		model.setViewName("courseForm");
		return model;
	}
	
	@RequestMapping(value = "courses/{courseId}/edit", method = RequestMethod.POST)
	public String editCourseSubmit(
			@RequestParam("weekDuration") int sweekDuration,
			@RequestParam("areaId") long areaId, Course course) {
		
		courseService.updateCourse(course, areaId, sweekDuration);
		
		return "redirect:/courses";
	}
	
	@RequestMapping(value = "courses/add", method = RequestMethod.GET)
	public ModelAndView newCourse() {
		List<Area> areas = areaService.getAllAreas();
		
		ModelAndView model = new ModelAndView();
		
		model.addObject("title", "new course");
		model.addObject("course", new Course());
		model.addObject("areas", areas);
		model.setViewName("courseForm");
		return model;
	}
	
	@RequestMapping(value = "courses/add" , method = RequestMethod.POST)
	public String newCourseSubmit(
			@RequestParam("weekDuration") int sweekDuration, 
			@RequestParam("areaId") long areaId, Course course) {
		courseService.saveCourse(course, areaId, sweekDuration);
		return "redirect:/courses";
	}

}
