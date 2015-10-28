package com.crsms.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.crsms.domain.Module;

public class ModuleFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Module.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "crsms.modules.error.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "crsms.modules.error.description.required");
		
		Module module = (Module) target;
	}
}
