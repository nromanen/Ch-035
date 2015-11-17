package com.crsms.dto;

import com.crsms.domain.Resource;
import com.googlecode.jmapper.annotations.JMap;

public class ResourceJsonDto {

	@JMap
	private Long id;

	@JMap
	private String name;

	@JMap
	private String url;

	@JMap
	private Resource.Type type;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Resource.Type getType() {
		return type;
	}

	public void setType(Resource.Type type) {
		this.type = type;
	}

}
