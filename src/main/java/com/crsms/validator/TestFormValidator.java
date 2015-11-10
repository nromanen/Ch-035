package com.crsms.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.crsms.domain.Test;

/**
 * @author Petro Andriets
 */

@Component
public class TestFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Test.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "crsms.createtest.error.name.required");
		Test module = (Test) target;
		if (module.getName().length() > Test.MAX_NAME_LENGTH) {
			errors.rejectValue("name", "crsms.error.too.long", new Object[]{Test.MAX_NAME_LENGTH}, "name is too long");
		}
	}
	
}
