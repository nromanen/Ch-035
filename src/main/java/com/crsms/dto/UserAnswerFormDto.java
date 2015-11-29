package com.crsms.dto;

import java.util.ArrayList;
import java.util.List;

public class UserAnswerFormDto {
	private Long testResultId;
	private Long questionId;
	private List<Long> answerIds = new ArrayList<Long>();
	public Long getTestResultId() {
		return testResultId;
	}
	public void setTestResultId(Long testResultId) {
		this.testResultId = testResultId;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public List<Long> getAnswerIds() {
		return answerIds;
	}
	public void setAnswerIds(List<Long> answerIds) {
		this.answerIds = answerIds;
	}
	
}
