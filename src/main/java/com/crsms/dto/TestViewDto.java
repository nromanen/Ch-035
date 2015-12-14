package com.crsms.dto;

import java.util.Set;

import com.crsms.domain.Question;
import com.googlecode.jmapper.annotations.JMap;

public class TestViewDto {
	
	@JMap
	private Long id;
	
	@JMap
	private String name;
	
	@JMap
	private Boolean available = false;
	
	@JMap
	private Boolean disable = false;
	
	@JMap
	private Set<Question> questions;
	
	private Double score;
	
	private Boolean complete;
	
	private Boolean hasTestResult;
	
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
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	public Boolean getDisable() {
		return disable;
	}
	public void setDisable(Boolean disable) {
		this.disable = disable;
	}
	public Set<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Boolean getComplete() {
		return complete;
	}
	public void setComplete(Boolean complete) {
		this.complete = complete;
	}
	public Boolean getHasTestResult() {
		return hasTestResult;
	}
	public void setHasTestResult(Boolean hasTestResult) {
		this.hasTestResult = hasTestResult;
	}
	
	
}
