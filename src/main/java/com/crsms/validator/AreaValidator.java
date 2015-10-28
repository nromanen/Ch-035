package com.crsms.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.crsms.domain.Area;

@Component
public class AreaValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return Area.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		//ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
	    Area a= (Area) obj;
	    if (a.getName().length() == 0) {
	        e.rejectValue("name", "crsms.area.empty");
	    } else if (a.getName().length() > 50) {
	        e.rejectValue("name", "crsms.area.long");
	    } else if (a.getName().length() < 5) {
	    	e.rejectValue("name", "crsms.area.short");
	    }
	}

}
