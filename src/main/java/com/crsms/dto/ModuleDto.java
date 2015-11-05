package com.crsms.dto;

import com.googlecode.jmapper.annotations.JMap;

public class ModuleDto {
	
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
	
}
