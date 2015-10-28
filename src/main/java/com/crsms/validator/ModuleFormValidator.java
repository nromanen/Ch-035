package com.crsms.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.crsms.domain.Module;

@Component
public class ModuleFormValidator implements Validator {
	
	private static final int MAX_NAME_LENGTH = 30;
	private static final int MAX_DESCTIPTION_LENGTH = 100;

	@Override
	public boolean supports(Class<?> clazz) {
		return Module.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "crsms.modules.error.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "crsms.error.description.required");
		
		Module module = (Module) target;
		
		if (module.getName().length() > MAX_NAME_LENGTH) {
			errors.rejectValue("name", "crsms.error.too.long", 
								new Object[]{MAX_NAME_LENGTH}, "name is too long");
		}
		
		if (module.getDescription().length() > MAX_DESCTIPTION_LENGTH) {
			errors.rejectValue("description", "crsms.error.too.long", 
								new Object[]{MAX_DESCTIPTION_LENGTH}, "description is too long");
		}
	}
}
