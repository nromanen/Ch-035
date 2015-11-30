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
				.setParameter("testId", testId).setParameter("userId", userId).uniqueResult();
	}

}
