package com.crsms.domain;

import java.util.Set;

import org.joda.time.DateTime;

public class Course {
	private Long id;
	private String name;
	private DateTime startDate;
	private DateTime duration;
	private Set<Module> modules; 
	private Boolean open = false;
	private Direction direction;
	
	public Course() { }

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

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getDuration() {
		return duration;
	}

	public void setDuration(DateTime duration) {
		this.duration = duration;
	}

	public Set<Module> getModules() {
		return modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
}
