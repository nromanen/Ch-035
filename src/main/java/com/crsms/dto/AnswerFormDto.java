package com.crsms.dto;

public class AnswerFormDto {
	private String text;
	private Boolean correct;
	
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

}
