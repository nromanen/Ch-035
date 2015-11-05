package com.crsms.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.crsms.domain.UserInfo;

@Component
public class UserInfoValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return UserInfo.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "crsms.error.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "crsms.error.name.required");
		
		UserInfo userInfo = (UserInfo) obj;
		
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
