package com.crsms.dto;

import java.util.List;

import com.crsms.domain.Resource;
import com.googlecode.jmapper.annotations.JMap;

public class ModuleViewDto {
	
	@JMap
	private Long id;
	
	@JMap
	private String name;
	
	@JMap
	private String description;
	
	@JMap
	private List<Resource> resources;
	
	@JMap
	private Boolean available = false;
	
	@JMap
	private Long orderPosition;
	
	@JMap
	private Boolean disable = false;
	
	@JMap
	private List<TestViewDto> tests;
	
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

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Long getOrderPosition() {
		return orderPosition;
	}

	public void setOrderPosition(Long orderPosition) {
		this.orderPosition = orderPosition;
	}

	public Boolean getDisable() {
		return disable;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
	}

	public List<TestViewDto> getTests() {
		return tests;
	}

	public void setTests(List<TestViewDto> tests) {
		this.tests = tests;
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
