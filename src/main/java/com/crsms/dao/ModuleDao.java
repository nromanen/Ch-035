package com.crsms.dao;

import com.crsms.domain.Module;

/**
 * 
 * @author St. Roman
 *
 */

public interface ModuleDao {
	
	void save(Module module);
	
	void delete(Module module);
	
	Module getById(Long id);
	
}
