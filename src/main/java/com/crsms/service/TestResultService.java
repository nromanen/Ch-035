package com.crsms.service;

import com.crsms.domain.TestResult;
import com.crsms.dto.UserAnswerAndQuestionDto;
import com.crsms.dto.UserAnswerFormDto;

import java.util.List;

public interface TestResultService {

	TestResult getCurrent(Long testId, String email);

	void save(UserAnswerFormDto userAnswerFormDto, String string);

	void complete(Long testResultId);

	TestResult getById(Long testResultId, String name);

	List<UserAnswerAndQuestionDto> getUserAnswerAndQuestionList(Long testResultId);

	TestResult getByTest(Long testId, Long userId);

	Double getScore(TestResult testResult);

	List<TestResult> getAllByTestIdAndGroupId(Long testId, Long groupId);

	void clearScore(Long testResultId);
}
