package com.crsms.service.hibernate.initializer;

import org.hibernate.Hibernate;

import com.crsms.domain.Course;
import com.crsms.util.Invocable;

public class CourseModulesInitializer implements Invocable<Course> {

	@Override
	public void invoke(Course course) {
		Hibernate.initialize(course.getModules());
	}

}
