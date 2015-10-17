package com.crsms.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.TestDao;
import com.crsms.domain.Test;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Service("testService")
@Transactional
public class TestServiceImpl implements TestService {
	
	@Autowired
    private TestDao testDao;
	
	@Override
	public void saveTest(Test test) {
		testDao.saveTest(test);
	}
	
	@Override
	public Set<Test> getAllTest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Test getTestById(Long id) {
		return testDao.getTestById(id);
	}

	@Override
	public void updateTest(Test test) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Test getTest(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
