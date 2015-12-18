package com.crsms.dto;

public class CourseModuleNamesPairDto {
	
	private String courseName;
	private String moduleName;
	
	public CourseModuleNamesPairDto(String courseName, String moduleName) {
		this.courseName = courseName;
		this.moduleName = moduleName;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

}
