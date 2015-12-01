package com.crsms.dao;

import org.springframework.stereotype.Repository;

import com.crsms.domain.TestResult;

@Repository
public class TestResultDaoImpl extends BaseDaoImpl<TestResult> implements TestResultDao {
	
	public TestResultDaoImpl(){
		super(TestResult.class);
	}

	@Override
	public TestResult getCurrent(Long testId, Long userId) {
		return ( TestResult) getSessionFactory().getCurrentSession().getNamedQuery(TestResult.GET_CURRENT)
				.setParameter("testId", testId).setParameter("userId", userId).setMaxResults(1).uniqueResult();
	}

	@Override
	public TestResult getByIdAndUser(Long testResultId, Long userId) {
		return ( TestResult) getSessionFactory().getCurrentSession().getNamedQuery(TestResult.GET_BY_ID_AND_USER)
				.setParameter("testResultId", testResultId).setParameter("userId", userId).uniqueResult();
	}

	@Override
	public Long counCorrectAnswers(Long testResultId) {
		return (Long) getSessionFactory().getCurrentSession().getNamedQuery(TestResult.COUNT_CORRECT_ANSWER)
				.setParameter("testResultId", testResultId).uniqueResult();
	}
	
	@Override
	public Long counIncorrectAnswers(Long testResultId) {
		return (Long) getSessionFactory().getCurrentSession().getNamedQuery(TestResult.COUNT_INCORRECT_ANSWER)
				.setParameter("testResultId", testResultId).uniqueResult();
	}

}
