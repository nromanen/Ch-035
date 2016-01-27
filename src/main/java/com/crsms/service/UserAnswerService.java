package com.crsms.service;

import java.util.List;

import com.crsms.domain.UserAnswer;
import com.crsms.dto.UserAnswerFormDto;

public interface UserAnswerService extends BaseService<UserAnswer> {

	UserAnswerFormDto getUserAnswerFormDto(Long id, Long id2);

	List<Boolean> getIsAnsweredQuestions(Long testId, Long testResultId, Long questionCount);

}
