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
	public static final String LOGIN = "crsms.bc.login";
	public static final String SIGNUP = "crsms.bc.signup";
	
	public static final String UNKNOWN = "crsms.bc.unknown";

	public String getCode(String label) {
		switch (label) {
			case "crsms": return CRSMS;
			case "areas": return AREAS;
			case "courses": return COURSES;
			case "modules": return MODULES;
			case "tests": return TESTS;
			case "resources": return RESOURCES;
			case "add": return ADD;
			case "edit": return EDIT;
			case "login": return LOGIN;
			case "signUp": return SIGNUP;
			default: return UNKNOWN;
		}
	}
	
}
