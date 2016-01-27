package com.crsms.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Type {
	public static final int MAX_NAME_LENGTH = 20;
	
	private Long id;
	@NotNull
	@Size(min = 2, max = MAX_NAME_LENGTH)
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
