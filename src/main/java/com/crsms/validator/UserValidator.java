package com.crsms.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.crsms.domain.User;
import com.crsms.service.UserService;


@Component
public class UserValidator implements Validator {
	
	@Autowired
	private UserService userService;
	
	public static final String EMAIL_PATTERN =
	      "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*\\.[A-Za-z]{2,}$";

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", 
													"crsms.error.email.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", 
													"crsms.error.password.required");
		
		User user = (User) obj;
		
		Matcher matcher = Pattern.compile(EMAIL_PATTERN).matcher(user.getEmail());
		
		if (user.getEmail() != null && !"".equals(user.getEmail().trim()) && !matcher.matches()) {
			errors.rejectValue("email", "crsms.error.email.invalid",
					new Object[] {User.BY_EMAIL},
					"Incorrect Email format");
		}
		
		if (userService.isEmailExists(user.getEmail())) {
			errors.rejectValue("email", "crsms.error.email.exists",
					new Object[] {User.BY_EMAIL},
					"Email exists");
		}

		if (user.getPassword().length() > User.MAX_PASSWORD_LENGTH) {
			errors.rejectValue("password", "crsms.error.too.long",
					new Object[] {User.MAX_PASSWORD_LENGTH}, "Password is too long");
		} else if (user.getPassword().length() < User.MIN_PASSWORD_LENGTH) {
			errors.rejectValue("password", "crsms.error.too.short", 
					new Object[] {User.MIN_PASSWORD_LENGTH}, "Password is too short");
		}

	}

}
