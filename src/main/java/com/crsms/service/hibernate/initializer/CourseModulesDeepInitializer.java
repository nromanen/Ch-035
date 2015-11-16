package com.crsms.service.hibernate.initializer;

import org.hibernate.Hibernate;

import com.crsms.domain.Course;
import com.crsms.domain.Module;

public class CourseModulesDeepInitializer extends BaseInitializer<Course> {
	
	public CourseModulesDeepInitializer(Course course) {
		super(course);
	}
	
	@Override
	public void execute() {
		Hibernate.initialize(this.getEntity().getModules());
		for (Module module : this.getEntity().getModules()) {
			Hibernate.initialize(module.getTests());
			Hibernate.initialize(module.getResources());		
		}
	}
}
