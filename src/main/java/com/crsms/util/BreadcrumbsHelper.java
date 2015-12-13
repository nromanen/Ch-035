package com.crsms.util;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.web.context.ContextLoader;

import com.crsms.service.AreaService;
import com.crsms.service.CourseService;
import com.crsms.service.GroupService;
import com.crsms.service.ModuleService;
import com.crsms.service.ResourceService;
import com.crsms.service.TestService;

public class BreadcrumbsHelper {
	public static final String LABEL_CRSMS = "crsms";
	public static final String CODE_CRSMS = "crsms.bc.crsms";
	public static final String LABEL_AREAS = "areas";
	public static final String CODE_AREAS = "crsms.bc.areas";
	public static final String LABEL_COURSES = "courses";
	public static final String CODE_COURSES = "crsms.bc.courses";
	public static final String LABEL_MODULES = "modules";
	public static final String CODE_MODULES = "crsms.bc.modules";
	public static final String LABEL_GROUPS = "groups";
	public static final String CODE_GROUPS = "crsms.bc.groups";
	public static final String LABEL_TESTS = "tests";
	public static final String CODE_TESTS = "crsms.bc.tests";
	public static final String LABEL_RESOURCES = "resources";
	public static final String CODE_RESOURCES = "crsms.bc.resources";
	public static final String LABEL_ADD = "add";
	public static final String CODE_ADD = "crsms.bc.add";
	public static final String LABEL_EDIT = "edit";
	public static final String CODE_EDIT = "crsms.bc.edit";
	public static final String LABEL_DELETE = "delete";
	public static final String CODE_DELETE = "crsms.bc.delete";
	public static final String LABEL_SIGNIN = "signin";
	public static final String CODE_SIGNIN = "crsms.bc.signin";
	public static final String LABEL_SIGNUP = "signup";
	public static final String CODE_SIGNUP = "crsms.bc.signup";
	public static final String LABEL_ADMIN = "admin";
	public static final String CODE_ADMIN = "crsms.bc.admin";
	public static final String LABEL_USER_PROFILE = "userprofile";
	public static final String CODE_USER_PROFILE = "crsms.bc.userprofile";
	public static final String LABEL_SEARCH = "search";
	public static final String CODE_SEARCH = "crsms.bc.search";

	public static final String CODE_UNKNOWN = "crsms.bc.unknown";
	
	private static final String MESSAGE_SOURCE_BEAN_NAME = "messageSource";
	private static final String AREA_SERVICE_BEAN_NAME = "areaService";
	private static final String COURSE_SERVICE_BEAN_NAME = "courseService";
	private static final String MODULE_SERVICE_BEAN_NAME = "moduleService";
	private static final String TEST_SERVICE_BEAN_NAME = "testService";
	private static final String RESOURCE_SERVICE_BEAN_NAME = "resourceService";
	private static final String GROUP_SERVICE_BEAN_NAME = "groupService";
	
	private static MessageSource messageSource = (MessageSource)
			ContextLoader.getCurrentWebApplicationContext().getBean(MESSAGE_SOURCE_BEAN_NAME);
	
	private static AreaService areaService = (AreaService)
			ContextLoader.getCurrentWebApplicationContext().getBean(AREA_SERVICE_BEAN_NAME);
	
	private static CourseService courseService = (CourseService)
			ContextLoader.getCurrentWebApplicationContext().getBean(COURSE_SERVICE_BEAN_NAME);
	
	private static ModuleService moduleService = (ModuleService)
			ContextLoader.getCurrentWebApplicationContext().getBean(MODULE_SERVICE_BEAN_NAME);
	
	private static TestService testService = (TestService)
			ContextLoader.getCurrentWebApplicationContext().getBean(TEST_SERVICE_BEAN_NAME);
	
	private static ResourceService resourceService = (ResourceService)
			ContextLoader.getCurrentWebApplicationContext().getBean(RESOURCE_SERVICE_BEAN_NAME);
	
	private static GroupService groupService = (GroupService)
			ContextLoader.getCurrentWebApplicationContext().getBean(GROUP_SERVICE_BEAN_NAME);

	public static String getCode(String label) {
		switch (label) {
			case LABEL_CRSMS: return CODE_CRSMS;
			case LABEL_AREAS: return CODE_AREAS;
			case LABEL_COURSES: return CODE_COURSES;
			case LABEL_MODULES: return CODE_MODULES;
			case LABEL_TESTS: return CODE_TESTS;
			case LABEL_RESOURCES: return CODE_RESOURCES;
			case LABEL_GROUPS: return CODE_GROUPS;
			case LABEL_ADD: return CODE_ADD;
			case LABEL_EDIT: return CODE_EDIT;
			case LABEL_DELETE: return CODE_DELETE;
			case LABEL_SIGNIN: return CODE_SIGNIN;
			case LABEL_SIGNUP: return CODE_SIGNUP;
			case LABEL_ADMIN: return CODE_ADMIN;
			case LABEL_USER_PROFILE: return CODE_USER_PROFILE;
			case LABEL_SEARCH: return CODE_SEARCH;
			default: return CODE_UNKNOWN;
		}
	}
	
	public static String getName(String target, String id) {
		Long targetId = Long.parseLong(id);
		switch (target) {
			case LABEL_AREAS: return areaService.getById(targetId).getName();
			case LABEL_COURSES: return courseService.getById(targetId).getName();
			case LABEL_MODULES: return moduleService.getById(targetId).getName();
			case LABEL_TESTS: return testService.getById(targetId).getName();
			case LABEL_RESOURCES: return resourceService.getById(targetId).getName();
			case LABEL_GROUPS: return groupService.getById(targetId).getName();
			default: return "";
		}
	}
	
	public static String getLabel(String target, Locale locale) {
		return messageSource.getMessage(getCode(target), new Object[] {}, locale);
	}
	
}
