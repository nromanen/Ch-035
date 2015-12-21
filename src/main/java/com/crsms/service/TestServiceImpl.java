package com.crsms.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.CourseDao;
import com.crsms.dao.ModuleDao;
import com.crsms.dao.TestDao;
import com.crsms.domain.Course;
import com.crsms.domain.Module;
import com.crsms.domain.Test;
import com.crsms.domain.TestResult;
import com.crsms.domain.User;
import com.crsms.dto.TestViewDto;
import com.crsms.exception.ElementNotFoundException;

/**
 * @author Petro Andriets
 */

@Transactional
@Service("testService")
public class TestServiceImpl extends BaseServiceImpl<Test> implements TestService {
    private static Logger logger = LogManager.getLogger(TestServiceImpl.class);
    
    @Autowired
    private TestDao testDao;
    
    @Autowired
    private ModuleService moduleService;
    
    @Autowired
    private CourseDao courseDao;
    
    @Autowired
    private ModuleDao moduleDao;
    
    @Autowired
	private TestResultService testResultService;
    
    @Override
    public void createTest(Long moduleId, Test test) {
    	logger.info("TestService. Creating a new test.");
    	Module module = moduleService.getById(moduleId);
    	module.addTest(test);
    	testDao.save(test);
    	logger.info("TestService. Creating a new test successfully.");
    }
    
    @Override
    public List<Test> getAllByModuleId(Long id) {
    	logger.info("TestService. Reading all tests by Module ID.");
    	return testDao.getAllByModuleId(id);
    }
    
    @Override
    public Test getTestById(Long id) {
    	logger.info("TestService. Reading test by ID: " + id + ".");
    	Test test = testDao.getTestById(id);
    	logger.info("TestService. Reading test by ID: " + id + " successfully.");
    	return test;
    }

    @Override
    public void deleteTestById(Long testId) {
    	logger.info("TestService. Deleting test by ID: " + testId + ".");

    	Course course = courseDao.getByTest(testId);
    	if (course == null || course.getDisable()) {
			throw new ElementNotFoundException();
		}
    	
    	Module module = moduleDao.getByTest(testId);
    	if (course == null || course.getDisable()) {
			throw new ElementNotFoundException();
		}
    	
    	Test test = testDao.getById(testId);
    	if (test == null || test.getDisable()) {
			throw new ElementNotFoundException();
		}
    	
    	test.disable();
    	if(!course.getPublished()) {
    		module.removeTest(test);
    		testDao.delete(test);
    	}
    	logger.info("TestService. Deleting test by ID: " + testId + " successfully.");
    }

	@Override
	public void initTestViewDto(TestViewDto testViewDto, User user) {
		TestResult testResult = testResultService.getByTest(testViewDto.getId(), user.getId());
		if(testResult != null) {
			testViewDto.setHasTestResult(true);
			testViewDto.setScore(testResult.getScore());
			testViewDto.setComplete(testResult.getComplete());
			testViewDto.setPass(testResult.getPass());
		} else {
			testViewDto.setHasTestResult(false);
		}
	}


}
