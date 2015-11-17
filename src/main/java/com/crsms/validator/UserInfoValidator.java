package com.crsms.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.crsms.domain.UserInfo;

@Component
public class UserInfoValidator implements Validator{

	final static String NAME_PATTERN = "^[A-ZА-Я][a-zа-я]*";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserInfo.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "crsms.error.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "crsms.error.name.required");
		
		UserInfo userInfo = (UserInfo) obj;
		
		Matcher fNameMatch = Pattern.compile(NAME_PATTERN).matcher(userInfo.getFirstName());
		Matcher lNameMatch = Pattern.compile(NAME_PATTERN).matcher(userInfo.getLastName());
		
		if(!fNameMatch.matches()){
			errors.rejectValue("firstName", "crsms.error.description.required");
		}
		
		if(!lNameMatch.matches()){
			errors.rejectValue("lastName", "crsms.error.description.required");
		}
		
		if (userInfo.getFirstName().length() > UserInfo.MAX_NAME_LENGTH) {
			errors.rejectValue("firstName", "crsms.error.too.long",
					new Object[] { UserInfo.MAX_NAME_LENGTH },
					"First Name is too long");
		} else if (userInfo.getLastName().length() > UserInfo.MAX_NAME_LENGTH) {
			errors.rejectValue("lastName", "crsms.error.too.long",
					new Object[] { UserInfo.MAX_NAME_LENGTH },
					"Last Name is too long");
		}
		
	}

}
