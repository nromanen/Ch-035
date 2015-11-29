package com.crsms.service;

import com.crsms.domain.UserAnswer;
import com.crsms.dto.UserAnswerFormDto;

public interface UserAnswerService extends BaseService<UserAnswer> {

	UserAnswerFormDto getUserAnswerFormDto(Long id, Long id2);

}
