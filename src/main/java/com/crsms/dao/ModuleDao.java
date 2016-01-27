package com.crsms.dao;

import java.util.List;

import com.crsms.domain.Module;
import com.crsms.domain.Test;

/**
 * 
 * @author St. Roman
 *
 */

public interface ModuleDao extends BaseDao<Module> {
	
	List<Module> getAllByCourseId(Long courseId);

	void deleteById(Long id);
	
	Module getByTest(Test test);
	Module getByTest(Long testId);

	List<Module> getAllAssociatedWithResource(Long resourceId);
	
}
