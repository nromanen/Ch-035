package com.crsms.service;

import com.crsms.domain.Course;
import com.crsms.domain.Test;

import java.util.List;

/**
 * @author Andriets Petro
 */

public interface TestService extends BaseService<Test> {
	
	public List<Test> getAllByModuleId(Long id);
	
	public void disableTestById(Long id);
	
	public void createTest(Long moduleId, Test test);

}
