package com.crsms.service;

import java.util.List;

import com.crsms.domain.Test;
import com.crsms.domain.User;
import com.crsms.dto.TestViewDto;

/**
 * @author Andriets Petro
 */

public interface TestService extends BaseService<Test> {
	
	List<Test> getAllByModuleId(Long id);
	
	Test getTestById(Long id);

	void deleteTestById(Long id);
	
	void createTest(Long moduleId, Test test);

	void initTestViewDto(TestViewDto testViewDto, User user);

}
