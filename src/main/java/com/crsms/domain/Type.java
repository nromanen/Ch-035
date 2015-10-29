package com.crsms.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Type {
	private Long id;
	@NotNull
	@Size(min = 2, max = 20)
	private String name;
	
	public Type() {
		super();
	}

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
