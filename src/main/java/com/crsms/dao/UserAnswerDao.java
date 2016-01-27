package com.crsms.dao;

import java.util.List;

import com.crsms.domain.UserAnswer;

public interface UserAnswerDao extends BaseDao<UserAnswer> {

	void deleteByTestResultAndQuestion(Long testResultId, Long questionId);

	List<Long> getCheckedAnswerIds(Long testResultId, Long questionId);

	Boolean hasAnswereForQuestion(Long testResultId, Long questionId);

	List<UserAnswer> getAnswers(Long testResultId, Long questionId);

}
