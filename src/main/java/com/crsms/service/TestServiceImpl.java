package com.crsms.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.TestDao;
import com.crsms.domain.Module;
import com.crsms.domain.Test;

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
    public void disableTestById(Long id) {
    	logger.info("TestService. Deleting test by ID: " + id + ".");
    	testDao.disableTestById(id);
    	logger.info("TestService. Deleting test by ID: " + id + " successfully.");
    }

}
