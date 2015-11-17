package com.crsms.dto;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.crsms.domain.Area;
import com.googlecode.jmapper.annotations.JMap;

public class CourseFormDto {
	@JMap
	private Long id;
	
	@JMap
	private String name;
	
	@JMap
	private String description;
		
	@JMap
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private DateTime startDate;
	
	@JMap
	private Integer duration;
	
	@JMap
	private Boolean open = false;
	
	@JMap
	private Area area;
	
	private String ownerEmail;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
}
