package com.crsms.service;

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

@Transactional
@Service("testService")
public class TestServiceImpl implements TestService {
    private static Logger logger = LogManager.getLogger(TestServiceImpl.class);
    
    @Autowired
    private TestDao testDao;
    
    @Autowired
    ModuleService moduleService;

    @Override
    public void createTest(Long moduleId, Test test) {
    	logger.info("TestService. Creating a new test.");
    	Module module = moduleService.getById(moduleId);
    	module.addTest(test);
    	testDao.saveTest(test);
    	logger.info("TestService. Creating a new test successfully.");
    }

    @Override
    public Test getTestById(Long id) {
    	logger.info("TestService. Reading test by ID: " + id + ".");
    	Test test = testDao.getTestById(id);
    	logger.info("TestService. Reading test by ID: " + id + " successfully.");
    	return test;
    }

    @Override
    public List<Test> getAllTests() {
    	logger.info("TestService. Reading all tests.");
    	List<Test> testList = testDao.getAllTests();
    	logger.info("TestService. Reading all tests successfully.");
    	return testList;
    }
    
    @Override
    public List<Test> getAllByModuleId(Long id) {
    	logger.info("TestService. Reading all tests by Module ID.");
    	return testDao.getAllByModuleId(id);
    }

    @Override
    public void editTest(Test test) {
    	logger.info("TestService. Editing test.");
    	Test existingTest = testDao.getTestById(test.getId());
    	existingTest.setName(test.getName());
    	existingTest.setAvailable(test.getAvailable());
    	testDao.updateTest(existingTest);
    	logger.info("TestService. Editing test successfully.");
    }

//    @Override
//    public void deleteTestById(Long id) {
//    	logger.info("TestService. Deleting test by ID: " + id + ".");
//    	//testDao.deleteTestById(id);
//    	this.disableTestById(id);
//    	logger.info("TestService. Deleting test by ID: " + id + " successfully.");
//    }
    
    @Override
    public void disableTestById(Long id) {
    	logger.info("TestService. Deleting test by ID: " + id + ".");
    	testDao.disableTestById(id);
    	logger.info("TestService. Deleting test by ID: " + id + " successfully.");
    }

}
