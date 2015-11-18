package com.crsms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.googlecode.jmapper.annotations.JMap;

public class ModuleJsonDto {
	@JMap
	private Long id;
	
	@JMap
	private String name;

	@JMap
	private String description;

	@JMap
	private Boolean available;

	@JMap
	private Long orderPosition;
	
	@JsonIgnore
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Long getOrderPosition() {
		return orderPosition;
	}

	public void setOrderPosition(Long orderPosition) {
		this.orderPosition = orderPosition;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	
}
