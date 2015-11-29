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
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().getNamedQuery(UserAnswer.DELETE_BY_TEST_RESULT_QUESTION)
				.setParameter("testResultId", testResultId).setParameter("questionId", questionId).executeUpdate();
	}

	@Override
	public List<Long> getAnswerIds(Long testResultId, Long questionId) {
		return getSessionFactory().getCurrentSession().getNamedQuery(UserAnswer.GET_ANSWER_IDS_BY_TEST_RESULT_AND_QUESTION)
			.setParameter("testResultId", testResultId).setParameter("questionId", questionId).list();
	}
}
