package com.crsms.service;

import com.crsms.domain.Test;

import java.util.List;

/**
 * @author Andriets Petro
 */

public interface TestService {

	public void createTest(Long moduleId, Test test);

	public Test getTestById(Long id);

	public List<Test> getAllTests();
	
	public List<Test> getAllByModuleId(Long id);

	public void editTest(Test test);

	//public void deleteTestById(Long id);
	
	public void disableTestById(Long id);

}
