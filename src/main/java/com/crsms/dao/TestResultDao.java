package com.crsms.dao;

import com.crsms.domain.TestResult;

public interface TestResultDao extends BaseDao<TestResult> {

	TestResult getCurrent(Long testId, Long userId);

	TestResult getByIdAndUser(Long testResultId, Long userId);

	Long counCorrectAnswers(Long testResultId);

	Long counIncorrectAnswers(Long testResultId);

}
