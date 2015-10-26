package com.crsms.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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
	        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
	        Area d = (Area) obj;
	        if (d.getName().length() < 10) {
	            e.rejectValue("name", "too.short");
	        } else if (d.getName().length() > 100) {
	            e.rejectValue("name", "too.long");
	        }
	    }

}
