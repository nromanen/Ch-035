package com.crsms.dao;

import java.util.Set;

import com.crsms.domain.Test;

/**
 * @author Petro Andriets
 *
 */

public interface TestDao {

	public void saveTest(Test test);
	
	public Test getTestById(Long id);
	
	public Set<Test> getAllTest();

	public void updateTest(Test test);
	
	public void deleteTest(Test test);
	
	public void delteTestById(Long id);
	
}
