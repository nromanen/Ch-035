package com.crsms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Valerii Motresku, Yuri Kucheriavy
 *
 */

@Entity
@Table(name = "area")
public class Area {
	public static final int MAX_NAME_LENGTH = 100;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
	@SequenceGenerator(name = "crsms_gen", sequenceName = "area_id_seq", allocationSize = 1)
	private Long id;
	
	@Column(nullable = false)
	@NotEmpty
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
