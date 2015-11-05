package com.crsms.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.crsms.domain.Area;

@Component
public class AreaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Area.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		//ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
	    Area a = (Area) obj;
	    if (a.getName().length() == 0) {
	        e.rejectValue("name", "crsms.error.field.required");
	    } else if (a.getName().length() > 100) {
	        e.rejectValue("name", "crsms.error.too.longer");
	    } else if (a.getName().length() < 2) {
	    	e.rejectValue("name", "crsms.error.too.shorter");
	    }
	}

}
