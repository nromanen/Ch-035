package com.crsms.service;

import java.util.List;

import com.crsms.domain.Test;

/**
 * @author Andriets Petro
 */

public interface TestService extends BaseService<Test> {
	
	List<Test> getAllByModuleId(Long id);
	
	public Test getTestById(Long id);
	
	void disableTestById(Long id);
	
	void createTest(Long moduleId, Test test);

}
