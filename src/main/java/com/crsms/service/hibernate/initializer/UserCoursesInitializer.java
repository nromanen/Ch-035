package com.crsms.service.hibernate.initializer;

import org.hibernate.Hibernate;

import com.crsms.domain.User;
import com.crsms.util.Invocable;

public class UserCoursesInitializer implements Invocable<User> {
	
	@Override
	public void invoke(User user) {
		Hibernate.initialize(user.getCourses());
	}
	
}
