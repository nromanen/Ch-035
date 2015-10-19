package com.crsms.service;

import java.util.Set;

import com.crsms.domain.Test;

/**
 * 
 * @author Valerii Motresku
 *
 */

public interface TestService {
	
	void saveTest(Test test);
	
	Set<Test> getAllTest();
	
	Test getTestById(Long id);

	void updateTest(Test test);
	
	Test getTest(String name);
}
