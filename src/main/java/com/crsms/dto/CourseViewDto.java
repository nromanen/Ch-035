package com.crsms.dto;

import java.util.List;

import com.crsms.domain.Area;
import com.crsms.domain.User;
import com.googlecode.jmapper.annotations.JMap;

public class CourseViewDto {
	@JMap
	private Long id;
	
	@JMap
	private String name;
	
	@JMap
	private String description;
	
	@JMap
	private Integer duration;
	
	@JMap
	private Boolean open = false;
	
	@JMap
	private Boolean disable = false;
	
	@JMap
	private Boolean published = false;
	
	@JMap
	private Area area;
	
	@JMap
	private User owner;
	
	@JMap
	private List<ModuleViewDto> modules;
	
	private Boolean complete = false;
	
	private Double score;
	
	private Double totalScore;

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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getDisable() {
		return disable;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<ModuleViewDto> getModules() {
		return modules;
	}

	public void setModules(List<ModuleViewDto> modules) {
		this.modules = modules;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}
	
	
}
