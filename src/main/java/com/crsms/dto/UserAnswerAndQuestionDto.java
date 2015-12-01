package com.crsms.dto;

import com.crsms.domain.Question;

public class UserAnswerAndQuestionDto {
	private UserAnswerFormDto userAnswerForm;
	private Question question;
	public UserAnswerFormDto getUserAnswerForm() {
		return userAnswerForm;
	}
	public void setUserAnswerForm(UserAnswerFormDto userAnswerForm) {
		this.userAnswerForm = userAnswerForm;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
}
