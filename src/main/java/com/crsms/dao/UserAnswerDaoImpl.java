package com.crsms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crsms.domain.UserAnswer;

@Repository
public class UserAnswerDaoImpl extends BaseDaoImpl<UserAnswer> implements UserAnswerDao {
	public UserAnswerDaoImpl(){
		super(UserAnswer.class);
	}

	@Override
	public void deleteByTestResultAndQuestion(Long testResultId, Long questionId) {
		getSessionFactory().getCurrentSession().getNamedQuery(UserAnswer.DELETE_BY_TEST_RESULT_QUESTION)
				.setParameter("testResultId", testResultId).setParameter("questionId", questionId).executeUpdate();
	}

	@SuppressWarnings(value = "unchecked")
	@Override
	public List<Long> getCheckedAnswerIds(Long testResultId, Long questionId) {
		return getSessionFactory().getCurrentSession().getNamedQuery(UserAnswer.GET_ANSWER_IDS_BY_TEST_RESULT_AND_QUESTION)
			.setParameter("testResultId", testResultId).setParameter("questionId", questionId).list();
	}
	
	@SuppressWarnings(value = "unchecked")
	@Override
	public List<UserAnswer> getAnswers(Long testResultId, Long questionId) {
		return getSessionFactory().getCurrentSession().getNamedQuery(UserAnswer.GET_ANSWER_BY_TEST_RESULT_AND_QUESTION)
			.setParameter("testResultId", testResultId).setParameter("questionId", questionId).list();
	}

	@Override
	public Boolean hasAnswereForQuestion(Long testResultId, Long questionId) {
		return (Boolean) getSessionFactory().getCurrentSession().getNamedQuery(UserAnswer.HAS_FOR_QUESTION)
                .setParameter("testResultId", testResultId)
                .setParameter("questionId", questionId)
                .uniqueResult();
	}
	
}
