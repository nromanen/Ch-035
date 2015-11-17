package com.crsms.dto;

import com.googlecode.jmapper.annotations.JMap;

public class TestJsonDto {

	public  TestJsonDto() { }

	@JMap
    private Long id;

	@JMap
    private String name;

	@JMap
    private Boolean available;

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

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

}
