package com.crsms.dto;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.googlecode.jmapper.annotations.JMap;

public class GroupFormDto {
	@JMap
	private Long id;
	
	@JMap
	private String name;
	
	@JMap
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private DateTime startDate;
	
	private Long courseId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
}
