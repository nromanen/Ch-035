package com.crsms.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crsms.domain.Area;
import com.crsms.domain.Course;
import com.crsms.domain.Module;
import com.crsms.domain.Resource;
import com.crsms.domain.Test;
import com.crsms.dto.AreaDto;
import com.crsms.dto.CourseDto;
import com.crsms.dto.ModuleDto;
import com.crsms.dto.ResourceDto;
import com.crsms.dto.TestDto;
import com.crsms.service.AreaService;
import com.crsms.service.CourseService;
import com.crsms.service.DtoService;
import com.crsms.service.ModuleService;
import com.crsms.service.ResourceService;
import com.crsms.service.TestService;

/**
 * 
 * @author Valerii Motresku
 *
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class RestApiController {
	
	@Autowired
	AreaService areaService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	ModuleService moduleService;
	
	@Autowired
	TestService testService;
	
	@Autowired
	ResourceService resourceService;
	
	@Autowired
	DtoService dtoService;
	
	@RequestMapping(value = {"/areas"}, method = RequestMethod.GET, produces = "application/json")
	public List<AreaDto> getAreas(HttpServletResponse response) {
		List<AreaDto> dtos = dtoService.convert(areaService.getAllAreas(), AreaDto.class, Area.class);
		response.addIntHeader("X-Total-Count", dtos.size());
		return dtos;
	}
	
	@RequestMapping(value = {"/areas/{areaId}"}, method = RequestMethod.GET, produces = "application/json")
	public AreaDto getArea(@PathVariable Long areaId) {
		return dtoService.convert(areaService.getAreaById(areaId), AreaDto.class, Area.class);
	}
	
	@RequestMapping(value = {"/courses"}, method = RequestMethod.GET, produces = "application/json")
	public List<CourseDto> getCourses() {
		return dtoService.convert(courseService.getAllCourse(), CourseDto.class, Course.class);
	}
	
	@RequestMapping(value = {"/courses/{courseId}"}, method = RequestMethod.GET, produces = "application/json")
	public CourseDto getCourse(@PathVariable Long courseId) {
		return dtoService.convert(courseService.getCourseById(courseId), CourseDto.class, Course.class);
	}
	
	@RequestMapping(value = {"/courses/{courseId}/modules"}, method = RequestMethod.GET, produces = "application/json")
	public List<ModuleDto> getModules(@PathVariable Long courseId) {
		return dtoService.convert(moduleService.getAllByCourseId(courseId), ModuleDto.class, Module.class);
	}
	
	@RequestMapping(value = {"/modules/{moduleId}"}, method = RequestMethod.GET, produces = "application/json")
	public ModuleDto getModule(@PathVariable Long moduleId) {
		return dtoService.convert(moduleService.getById(moduleId), ModuleDto.class, Module.class);
	}
	
	@RequestMapping(value = {"/modules/{moduleId}/tests"}, method = RequestMethod.GET, produces = "application/json")
	public List<TestDto> getTests(@PathVariable Long moduleId) {
		return dtoService.convert(testService.getAllByModuleId(moduleId), TestDto.class, Test.class);
	}
	
	@RequestMapping(value = {"/tests/{testId}"}, method = RequestMethod.GET, produces = "application/json")
	public TestDto getTest(@PathVariable Long testId) {
		return dtoService.convert(testService.getTestById(testId), TestDto.class, Test.class);
	}
	
	@RequestMapping(value = {"/modules/{moduleId}/resources"}, method = RequestMethod.GET, produces = "application/json")
	public List<ResourceDto> getResources(@PathVariable Long moduleId) {
		return dtoService.convert(resourceService.getAllByModuleId(moduleId), ResourceDto.class, Resource.class);
	}
	
	@RequestMapping(value = {"/resources/{resourceId}"}, method = RequestMethod.GET, produces = "application/json")
	public ResourceDto getResource(@PathVariable Long resourceId) {
		return dtoService.convert(resourceService.getById(resourceId), ResourceDto.class, Resource.class);
	}
}
