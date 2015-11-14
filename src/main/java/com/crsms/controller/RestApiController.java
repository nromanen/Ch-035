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
import com.crsms.dto.AreaJsonDto;
import com.crsms.dto.CourseJsonDto;
import com.crsms.dto.ModuleJsonDto;
import com.crsms.dto.ResourceJsonDto;
import com.crsms.dto.TestJsonDto;
import com.crsms.dto.VacancyJsonDto;
import com.crsms.service.AreaService;
import com.crsms.service.CourseService;
import com.crsms.service.DtoService;
import com.crsms.service.ModuleService;
import com.crsms.service.ResourceService;
import com.crsms.service.TestService;
import com.crsms.service.VacancyService;

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
	
	@Autowired
  VacancyService vacancyService;
	
	@RequestMapping(value = {"/areas"}, method = RequestMethod.GET, produces = "application/json")
	public List<AreaJsonDto> getAreas(HttpServletResponse response) {
		List<AreaJsonDto> dtos = dtoService.convert(areaService.getAllAreas(), AreaJsonDto.class, Area.class);
		response.addIntHeader("X-Total-Count", dtos.size());
		return dtos;
	}
	
	@RequestMapping(value = {"/areas/{areaId}"}, method = RequestMethod.GET, produces = "application/json")
	public AreaJsonDto getArea(@PathVariable Long areaId) {
		return dtoService.convert(areaService.getAreaById(areaId), AreaJsonDto.class, Area.class);
	}
	
	@RequestMapping(value = {"/courses"}, method = RequestMethod.GET, produces = "application/json")
	public List<CourseJsonDto> getCourses() {
		return dtoService.convert(courseService.getAll(), CourseJsonDto.class, Course.class);
	}
	
	@RequestMapping(value = {"/courses/{courseId}"}, 
			method = RequestMethod.GET, produces = "application/json")
	public CourseJsonDto getCourse(@PathVariable Long courseId) {
		return dtoService.convert(courseService.getById(courseId), CourseJsonDto.class, Course.class);
	}
	
	@RequestMapping(value = {"/courses/{courseId}/modules"}, 
			method = RequestMethod.GET, produces = "application/json")
	public List<ModuleJsonDto> getModules(@PathVariable Long courseId) {
		return dtoService.convert(moduleService.getAllByCourseId(courseId), ModuleJsonDto.class, Module.class);
	}
	
	@RequestMapping(value = {"/modules/{moduleId}", "/courses/{courseId}/modules/{moduleId}"}, 
			method = RequestMethod.GET, produces = "application/json")
	public ModuleJsonDto getModule(@PathVariable Long moduleId) {
		return dtoService.convert(moduleService.getById(moduleId), ModuleJsonDto.class, Module.class);
	}
	
	@RequestMapping(value = {"/modules/{moduleId}/tests", 
			"/courses/{courseId}/modules/{moduleId}/tests"}, 
			method = RequestMethod.GET, produces = "application/json")
	public List<TestJsonDto> getTests(@PathVariable Long moduleId) {
		return dtoService.convert(testService.getAllByModuleId(moduleId), TestJsonDto.class, Test.class);
	}
	
	@RequestMapping(value = {"/tests/{testId}", "/courses/{courseId}/modules/{moduleId}/tests/{testId}"},
			method = RequestMethod.GET, produces = "application/json")
	public TestJsonDto getTest(@PathVariable Long testId) {
		return dtoService.convert(testService.getTestById(testId), TestJsonDto.class, Test.class);
	}
	
	@RequestMapping(value = {"/modules/{moduleId}/resources", 
			"/courses/{courseId}/modules/{moduleId}/resources"}, 
			method = RequestMethod.GET, produces = "application/json")
	public List<ResourceJsonDto> getResources(@PathVariable Long moduleId) {
		return dtoService.convert(resourceService.getAllByModuleId(moduleId), ResourceJsonDto.class, Resource.class);
	}
	
	@RequestMapping(value = {"/resources/{resourceId}", 
			"/courses/{courseId}/modules/{moduleId}/resources/{resourceId}"}, 
			method = RequestMethod.GET, produces = "application/json")
	public ResourceJsonDto getResource(@PathVariable Long resourceId) {
		return dtoService.convert(resourceService.getById(resourceId), ResourceJsonDto.class, Resource.class);
	}
	
	@RequestMapping(value = {"/vacancies"}, method = RequestMethod.GET, produces = "application/json")
	public List<VacancyJsonDto> getVacatinos() {
	  return vacancyService.getAllVacancies();
	}
}
