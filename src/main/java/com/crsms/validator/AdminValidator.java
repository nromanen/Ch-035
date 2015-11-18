package com.crsms.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.crsms.domain.Course;
import com.crsms.domain.User;

@Component
public class AdminValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Course.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
													"crsms.error.password.required");
		User user = (User) target;
		if (user.getPassword().length() > User.MAX_PASSWORD_LENGTH) {
			errors.rejectValue("password", "crsms.error.too.long", 
								new Object[]{User.MAX_PASSWORD_LENGTH}, "Password is too long");
		} else if (user.getPassword().length() < User.MIN_PASSWORD_LENGTH) {
			errors.rejectValue("password", "crsms.error.too.short", 
								new Object[]{User.MIN_PASSWORD_LENGTH}, "Password is too short");
		}
	
	}
	
}
