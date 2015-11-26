package com.crsms.dao;

import com.crsms.domain.UserAnswer;

public interface UserAnswerDao extends BaseDao<UserAnswer> {

	void deleteByTestResultAndQuestion(Long testResultId, Long questionId);

}
