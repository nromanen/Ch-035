package com.crsms.service;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.TestDao;
import com.crsms.dao.TestDaoImpl;
import com.crsms.domain.Test;

/**
 * @author Petro Andriets
 *
 */

@Service("testService")
@Transactional
public class TestServiceImpl implements TestService {
	private static Logger log = LogManager.getLogger(TestDaoImpl.class);
	
	@Autowired
    private TestDao testDao;

	@Override
	public void createTest(Test test) {
		try {
			log.info("Creating new test.");
			testDao.saveTest(test);
		} catch (Exception e) {
			log.error("Test creating error: " + e);
		}
	}

	@Override
	public Test getTestById(Long id) {
		Test test = null;
		try {
			log.info("Reading test by ID.");
			test = testDao.getTestById(id);
		} catch (Exception e) {
			log.error("Test reading by ID error: " + e);
		}
		return test;
	}

	@Override
	public Set<Test> getAllTest() {
		Set<Test> testSet = null;
		try {
			log.info("Reading all tests.");
			testSet = testDao.getAllTest();
		} catch (Exception e) {
			log.error("All tests reading error: " + e);
		}
		return testSet;
	}

	@Override
	public void updateTest(Test test) {
		try {
			log.info("Updating test.");
			testDao.updateTest(test);
		} catch (Exception e) {
			log.error("Test updating error: " + e);
		}
	}

	@Override
	public void deleteTest(Test test) {
		try {
			log.info("Deleting test.");
			testDao.deleteTest(test);
		} catch (Exception e) {
			log.error("Test deleting error: " + e);
		}
	}

	@Override
	public void delteTestById(Long id) {
		try {
			log.info("Deleting test by ID.");
			testDao.delteTestById(id);
		} catch (Exception e) {
			log.error("Test by ID deleting error: " + e);
		}
	}

}
