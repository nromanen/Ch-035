package com.crsms.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.crsms.domain.Course;

@Component
public class CourseValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Course.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "crsms.error.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "crsms.error.description.required");
		
		Course course = (Course) target;
		
		if (course.getName().length() > Course.MAX_NAME_LENGTH) {
			errors.rejectValue("name", "crsms.error.too.long", 
								new Object[]{Course.MAX_NAME_LENGTH}, "name is too long");
		}
		
		if (course.getDescription().length() > Course.MAX_DESCTIPTION_LENGTH) {
			errors.rejectValue("description", "crsms.error.too.long", 
								new Object[]{Course.MAX_DESCTIPTION_LENGTH}, "description is too long");
		}
	}
	
}
