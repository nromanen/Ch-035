package com.crsms.dao;

import com.crsms.domain.TestResult;

public interface TestResultDao extends BaseDao<TestResult> {

	TestResult getCurrent(Long testId, Long userId);

}
