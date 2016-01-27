package com.crsms.dto;

import com.crsms.domain.Resource;
import com.googlecode.jmapper.annotations.JMap;

public class ResourceJsonDto {

	@JMap
	private Long id;

	@JMap
	private String name;

	@JMap
	private String path;

	@JMap
	private Resource.Type type;
	
	@JMap
	private Resource.StorageType storageType;

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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Resource.Type getType() {
		return type;
	}

	public void setType(Resource.Type type) {
		this.type = type;
	}

	public Resource.StorageType getStorageType() {
		return storageType;
	}

	public void setStorageType(Resource.StorageType storageType) {
		this.storageType = storageType;
	}

}
