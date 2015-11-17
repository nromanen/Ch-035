package com.crsms.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.crsms.domain.Course;
import com.crsms.dto.CourseJsonDto;
import com.crsms.service.CourseService;

@Component
public class CourseJsonValidator implements Validator {
	
	@Autowired
	private CourseService courseService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CourseJsonDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "crsms.error.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
												"description", "crsms.error.description.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "crsms.error.date.required");
		
		CourseJsonDto courseJsonDto = (CourseJsonDto) target;
		
		Long courseId = courseJsonDto.getId();
		String name = courseJsonDto.getName();
		
		List<Course> courses = courseService.getAll();
		
		for (Course course : courses) {
			if (course.getName().equalsIgnoreCase(name) && (!course.getId().equals(courseId))) {
				errors.rejectValue("name", "crsms.error.not.unique.name");
				break;
			}
		}
		
		if (courseJsonDto.getName().length() > Course.MAX_NAME_LENGTH) {
			errors.rejectValue("name", "crsms.error.too.long", 
								new Object[]{Course.MAX_NAME_LENGTH}, "name is too long");
		}
	}
}
