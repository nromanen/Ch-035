package com.crsms.service;

import com.crsms.domain.TestResult;
import com.crsms.dto.UserAnswerFormDto;

public interface TestResultService {

	TestResult getCurrent(Long testId, String email);

	void save(UserAnswerFormDto userAnswerFormDto);

}
