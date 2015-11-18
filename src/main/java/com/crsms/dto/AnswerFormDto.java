package com.crsms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Petro Andriets
 */

@JsonIgnoreProperties
public class AnswerFormDto {
	private Long id;
	private String text;
	private Boolean correct = false;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Boolean getCorrect() {
		return correct;
	}
	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
