package com.crsms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "setup_option")
public class Option {
	
	public static final int MAX_KEY_LENGTH = 255;
	
	// option keys
	public static final String STORAGE_TYPE_OPTION_KEY = "storage.type";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "option_key")
	@Size(min = 1, max = MAX_KEY_LENGTH)
	private String key;
	
	@Column(nullable = false, name = "option_value")
	private String value;

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
