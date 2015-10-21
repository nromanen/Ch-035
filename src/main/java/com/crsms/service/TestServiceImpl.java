package com.crsms.service;

import com.crsms.dao.TestDao;
import com.crsms.dao.TestDaoImpl;
import com.crsms.domain.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author Petro Andriets
 */

@Service("testService")
public class TestServiceImpl implements TestService {
    private static Logger log = LogManager.getLogger(TestDaoImpl.class);
    @Autowired
    private TestDao testDao;

    public TestServiceImpl() {}

    @Override
    @Transactional
    public void createTest(Test test) {
        try {
            log.info("Creating new test.");
            testDao.saveTest(test);
        } catch (Exception e) {
            log.error("Test creating error: " + e);
        }
    }

    @Override
    @Transactional
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
    @Transactional
    public Set<Test> getAllTests() {
        Set<Test> testSet = null;
        try {
            log.info("Reading all tests.");
            testSet = testDao.getAllTests();
        } catch (Exception e) {
            log.error("All tests reading error: " + e);
        }
        return testSet;
    }

    @Override
    @Transactional
    public void editTest(Test test) {
        try {
            log.info("Updating test.");
            testDao.updateTest(test);
        } catch (Exception e) {
            log.error("Test updating error: " + e);
        }
    }

    @Override
    @Transactional
    public void deleteTest(Test test) {
        try {
            log.info("Deleting test.");
            testDao.deleteTest(test);
        } catch (Exception e) {
            log.error("Test deleting error: " + e);
        }
    }

    @Override
    @Transactional
    public void deleteTestById(Long id) {
        try {
            log.info("Deleting test by ID.");
            testDao.deleteTestById(id);
        } catch (Exception e) {
            log.error("Test by ID deleting error: " + e);
        }
    }

}
