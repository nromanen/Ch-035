package com.crsms.domain;

public class Answer {
	private Long id;
	private Question question;
	private String text;
	private Boolean isRight = false;
	
	public Answer() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getIsRight() {
		return isRight;
	}

	public void setIsRight(Boolean isRight) {
		this.isRight = isRight;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
