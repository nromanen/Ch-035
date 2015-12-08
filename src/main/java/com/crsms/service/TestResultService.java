package com.crsms.service;

import java.util.List;

import com.crsms.domain.TestResult;
import com.crsms.dto.UserAnswerAndQuestionDto;
import com.crsms.dto.UserAnswerFormDto;

public interface TestResultService {

	TestResult getCurrent(Long testId, String email);

	void save(UserAnswerFormDto userAnswerFormDto, String string);

	void complete(Long testResultId);

	Double getScore(Long testResultId);

	TestResult getById(Long testResultId, String name);

	List<UserAnswerAndQuestionDto> getUserAnswerAndQuestionList(Long testResultId);

}
