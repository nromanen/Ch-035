package com.crsms.service;

import com.crsms.dao.ModuleDao;
import com.crsms.dao.TestDao;
import com.crsms.domain.Module;
import com.crsms.domain.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Petro Andriets
 */

@Service("testService")
public class TestServiceImpl implements TestService {
    private static Logger logger = LogManager.getLogger(TestServiceImpl.class);
    @Autowired
    private TestDao testDao;

    @Autowired
    private ModuleDao moduleDao;

    public TestServiceImpl() {
    }

    @Override
    @Transactional
    public void createTest(Long moduleId, Test test) {
        try {
            logger.info("TestService. Creating a new test.");
            Module module = moduleDao.getById(moduleId);
            test.setModule(module);
            testDao.saveTest(test);
            logger.info("TestService. Creating a new test successfully.");
        } catch (Exception e) {
            logger.error("TestService. Test creating error: " + e);
        }
    }

    @Override
    @Transactional
    public Test getTestById(Long id) {
    	Test test = null;
        try {
            logger.info("TestService. Reading test by ID: " + id + ".");
            test = testDao.getTestById(id);
            logger.info("TestService. Reading test by ID: " + id + " successfully.");
        } catch (Exception e) {
            logger.error("TestService. Test reading by ID " + id + " error: " + e);
        }
        return test;
    }

    @Override
    @Transactional
    public List<Test> getAllTests() {
        List<Test> testList = null;
        try {
            logger.info("TestService. Reading all tests.");
            testList = testDao.getAllTests();
            logger.info("TestService. Reading all tests successfully.");
        } catch (Exception e) {
            logger.error("TestService. All tests reading error: " + e);
        }
        return testList;
    }

    @Override
    @Transactional
    public void editTest(Test test) {
        try {
            logger.info("TestService. Editing test.");
            Test existingTest = testDao.getTestById(test.getId());
            existingTest.setName(test.getName());
            existingTest.setAvailable(test.getAvailable());
            testDao.updateTest(existingTest);
            logger.info("TestService. Editing test successfully.");
        } catch (Exception e) {
            logger.error("TestService. Test editing error: " + e);
        }
    }

    @Override
    @Transactional
    public void deleteTest(Test test) {
        try {
            logger.info("TestService. Deleting test.");
            testDao.deleteTest(test);
            logger.info("TestService. Deleting test successfully.");
        } catch (Exception e) {
            logger.error("TestService. Test deleting error: " + e);
        }
    }

    @Override
    @Transactional
    public void deleteTestById(Long id) {
        try {
            logger.info("TestService. Deleting test by ID: " + id + ".");
            testDao.deleteTestById(id);
            logger.info("TestService. Deleting test by ID: " + id + " successfully.");
        } catch (Exception e) {
            logger.error("TestService. Test by ID " + id + " deleting error: " + e);
        }
    }

}
