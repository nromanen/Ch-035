package com.crsms.dto;

import com.googlecode.jmapper.annotations.JMap;

public class AreaJsonDto {
	
	@JMap
	private Long id;
	
	@JMap
	private String name;

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
	
}
