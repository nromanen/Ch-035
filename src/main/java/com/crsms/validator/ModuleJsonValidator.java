package com.crsms.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.crsms.domain.Course;
import com.crsms.domain.Module;
import com.crsms.dto.ModuleJsonDto;
import com.crsms.service.CourseService;
import com.crsms.service.hibernate.initializer.CourseModulesInitializer;
import com.crsms.util.Invocable;

@Component
public class ModuleJsonValidator implements Validator {
	
	@Autowired
	private CourseService courseService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ModuleJsonDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", 
													"crsms.modules.error.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
													"crsms.error.description.required");
		
		ModuleJsonDto moduleJsonDto = (ModuleJsonDto) target;
		
		Long moduleId = moduleJsonDto.getId();
		Long courseId = moduleJsonDto.getCourseId();
		
		String name = moduleJsonDto.getName();
		
		List<Module> modules = getModules(courseId);
		
		for (Module module : modules) {
			//second condition allows you to edit other fields without "name already exists" error
			if (module.getName().equalsIgnoreCase(name) && (!module.getId().equals(moduleId))) {
				errors.rejectValue("name", "crsms.error.not.unique.name");
				break;
			}
		}
		
		if (name.length() > Module.MAX_NAME_LENGTH) {
			errors.rejectValue("name", "crsms.error.too.long", 
								new Object[]{Module.MAX_NAME_LENGTH},
								"name is too long");
		}
	}

	private List<Module> getModules(Long courseId) {
		List<Invocable<Course>> initializers = new ArrayList<>();
		initializers.add(new CourseModulesInitializer());
		List<Module> modules = courseService.getById(courseId, initializers).getModules();
		return modules;
	}
}
