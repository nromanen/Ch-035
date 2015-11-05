package com.crsms.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.crsms.domain.Module;
import com.crsms.dto.ModuleFormDto;
import com.crsms.service.CourseService;

@Component
public class ModuleFormValidator implements Validator {
	
	@Autowired
	private CourseService courseService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ModuleFormDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "crsms.modules.error.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "crsms.error.description.required");
		
		ModuleFormDto moduleFormDto = (ModuleFormDto) target;
		
		Long id = moduleFormDto.getId();
		String name = moduleFormDto.getName();
		
		for (Module module : courseService.getCourseById(moduleFormDto.getCourseId()).getModules()) {
			//second condition allows you to edit other fields without "name already exists" error
			if (module.getName().equalsIgnoreCase(name) && (!module.getId().equals(id))) {
				errors.rejectValue("name", "crsms.error.not.unique.name");
				break;
			}
		}
		
		if (moduleFormDto.getName().length() > Module.MAX_NAME_LENGTH) {
			errors.rejectValue("name", "crsms.error.too.long", 
								new Object[]{Module.MAX_NAME_LENGTH}, "name is too long");
		}
		
		if (moduleFormDto.getDescription().length() > Module.MAX_DESCTIPTION_LENGTH) {
			errors.rejectValue("description", "crsms.error.too.long", 
								new Object[]{Module.MAX_DESCTIPTION_LENGTH}, "description is too long");
		}
	}
}
