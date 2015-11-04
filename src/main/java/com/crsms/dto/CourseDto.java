package com.crsms.dto;


import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.crsms.domain.Area;
import com.googlecode.jmapper.annotations.JMap;

public class CourseDto {
	
	@JMap
	private Long id;
	
	@JMap
	private String name;
	
	@JMap
	private String description;
	
	@JMap
	private DateTime startDate;
	
	@JMap
	private Duration duration;
	
	@JMap
	private Boolean open = false;
	
	@JMap
	private Area area;

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

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
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
}
