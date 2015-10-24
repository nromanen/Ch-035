package com.crsms.service;

import com.crsms.domain.Test;

import java.util.List;

/**
 * @author Andriets Petro
 */

public interface TestService {

	public void createTest(Test test);

	public Test getTestById(Long id);

	public List<Test> getAllTests();

	public void editTest(Test test);

	public void deleteTest(Test test);

	public void deleteTestById(Long id);

}
