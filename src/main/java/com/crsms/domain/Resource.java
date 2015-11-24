package com.crsms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Entity
@Table(name = "resource")
public class Resource {
	
	public static final int NAME_MIN = 1;
	public static final int NAME_MAX = 100;
	public static final int URL_MAX = 255;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
	@SequenceGenerator(name = "crsms_gen", sequenceName = "resource_id_seq", allocationSize = 1)
	private Long id;
	
	@Column(nullable = false)
	@NotNull
	@Size(min = NAME_MIN, max = NAME_MAX)
	private String name;
	
	@Column(nullable = false)
	@NotNull
	@Size(max = URL_MAX)
	private String url;
	
	@Column(nullable = false)
	private Resource.Type type;
	
	@Column(name="storage_type", nullable = false)
	private Resource.StorageType storageType;
	
	// implicitly static
	public enum Type {
		FILE, EMBEDDED
	}
	
	// implicitly static
	public enum StorageType {
		CATALINA, GOOGLE_DRIVE
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
	public Resource.StorageType getStorageType() {
		return storageType;
	}
	public void setStorageType(Resource.StorageType storageType) {
		this.storageType = storageType;
	}	
	
}
