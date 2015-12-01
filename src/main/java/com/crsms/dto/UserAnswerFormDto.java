package com.crsms.dto;

import java.util.ArrayList;
import java.util.List;

public class UserAnswerFormDto {
	private Long testResultId;
	private Long questionId;
	private List<Long> checkedAnswerIds = new ArrayList<Long>();
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
	public List<Long> getCheckedAnswerIds() {
		return checkedAnswerIds;
	}
	public void setCheckedAnswerIds(List<Long> checkedAnswerIds) {
		this.checkedAnswerIds = checkedAnswerIds;
	}

	
}
