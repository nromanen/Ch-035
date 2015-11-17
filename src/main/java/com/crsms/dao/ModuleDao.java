package com.crsms.dao;

import java.util.List;

import com.crsms.domain.Module;

/**
 * 
 * @author St. Roman
 *
 */

public interface ModuleDao extends BaseDao<Module> {
	
	List<Module> getAllByCourseId(Long courseId);

	void deleteById(Long id);
	
	void disable(Module module);
	
}
