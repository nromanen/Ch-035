package com.crsms.util;

public class BreadcrumbsHelper {
	
	public static final String CRSMS = "crsms.bc.crsms";
	public static final String AREAS = "crsms.bc.areas";
	public static final String COURSES = "crsms.bc.courses";
	public static final String MODULES = "crsms.bc.modules";
	public static final String TESTS = "crsms.bc.tests";
	public static final String RESOURCES = "crsms.bc.resources";
	public static final String ADD = "crsms.bc.add";
	public static final String EDIT = "crsms.bc.edit";
	public static final String DELETE = "crsms.bc.delete";
	public static final String SIGNIN = "crsms.bc.signin";
	public static final String SIGNUP = "crsms.bc.signup";
	public static final String ADMIN = "crsms.bc.admin";
	public static final String USER_PROFILE = "crsms.bc.userprofile";
	public static final String SEARCH = "crsms.bc.search";
	
	public static final String UNKNOWN = "crsms.bc.unknown";

	public static String getCode(String label) {
		switch (label) {
			case "crsms": return CRSMS;
			case "areas": return AREAS;
			case "courses": return COURSES;
			case "modules": return MODULES;
			case "tests": return TESTS;
			case "resources": return RESOURCES;
			case "add": return ADD;
			case "edit": return EDIT;
			case "delete": return DELETE;
			case "signin": return SIGNIN;
			case "signUp": return SIGNUP;
			case "admin": return ADMIN;
			case "userProfile": return USER_PROFILE;
      case "search": return SEARCH;
			default: return UNKNOWN;
		}
	}
	
}
