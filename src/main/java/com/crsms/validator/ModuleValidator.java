package com.crsms.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.crsms.domain.Module;

@Component
public class ModuleValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Module.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "crsms.modules.error.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "crsms.error.description.required");
		
		Module module = (Module) target;
		
		if (module.getName().length() > Module.MAX_NAME_LENGTH) {
			errors.rejectValue("name", "crsms.error.too.long", 
								new Object[]{Module.MAX_NAME_LENGTH}, "name is too long");
		}
		
		if (module.getDescription().length() > Module.MAX_DESCTIPTION_LENGTH) {
			errors.rejectValue("description", "crsms.error.too.long", 
								new Object[]{Module.MAX_DESCTIPTION_LENGTH}, "description is too long");
		}
	}
}
