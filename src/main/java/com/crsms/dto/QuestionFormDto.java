package com.crsms.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Petro Andriets
 */

/**
 * @JsonIgnoreProperties annotation ignore properties that do not match the DTO.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionFormDto {
	private String text;
	private Long id;
	
	private List<AnswerFormDto> answers = new ArrayList<>();

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<AnswerFormDto> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerFormDto> answers) {
		this.answers = answers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
