package com.crsms.dao;

import com.crsms.domain.TestResult;

import java.util.List;
import java.util.Set;

public interface TestResultDao extends BaseDao<TestResult> {

	TestResult getCurrent(Long testId, Long userId);

	TestResult getByIdAndUser(Long testResultId, Long userId);

	Long counCorrectAnswers(Long testResultId);

	Long counIncorrectAnswers(Long testResultId);

	List<TestResult> getByTestIdAndUsers(Long testId, Set<Long> users);

}
