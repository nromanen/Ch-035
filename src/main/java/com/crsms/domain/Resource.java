package com.crsms.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Entity
@Table(name = "resource")
@NamedQueries({
	@NamedQuery(name = Resource.GET_ALL_BY_MODULE_ID, 
				query = "select mr from Module m join m.resources mr where m.id = :moduleId"),
				
	@NamedQuery(name = Resource.GET_ALL_NOT_ASSOCIATED_WITH_MODULE, 
				query = "select r from Resource r left join r.modules rm where r.id not in "
						+ "(select mr.id from Module m join m.resources mr where m.id = :moduleId)"),
})
public class Resource {
	
	public static final String GET_ALL_BY_MODULE_ID = "Resource.getAllByModuleId";
	public static final String GET_ALL_NOT_ASSOCIATED_WITH_MODULE = "Resource.getAllNotAssociatedWithModule";
	
	public static final int NAME_MIN = 1;
	public static final int NAME_MAX = 100;
	public static final int PATH_MAX = 255;
	
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
	@Size(max = PATH_MAX)
	private String path;
	
	@Column(nullable = false)
	private Resource.Type type;
	
	@Column(name="storage_type", nullable = false)
	private Resource.StorageType storageType;
	
	@ManyToMany(mappedBy="resources")
	private List<Module> modules;
	
	// implicitly static
	public enum Type {
		FILE, EMBEDDED
	}
	
	// implicitly static
	public enum StorageType {
		DB, CATALINA, GOOGLE_DRIVE,
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
