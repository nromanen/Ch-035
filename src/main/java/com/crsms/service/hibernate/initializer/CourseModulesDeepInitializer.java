package com.crsms.service.hibernate.initializer;

import org.hibernate.Hibernate;

import com.crsms.domain.Course;
import com.crsms.domain.Module;

public class CourseModulesDeepInitializer extends BaseInitializer<Course> {
	
	@Override
	public void invoke(Course course) {
		Hibernate.initialize(course.getModules());
		for (Module module : course.getModules()) {
			Hibernate.initialize(module.getTests());
			Hibernate.initialize(module.getResources());		
		}
	}
	
}
