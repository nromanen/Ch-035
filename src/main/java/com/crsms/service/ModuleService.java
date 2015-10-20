package com.crsms.service;

import com.crsms.domain.Module;

/**
 * 
 * @author St. Roman
 *
 */

public interface ModuleService {
	
	void save(Module module);
	
	void delete(Module module);
	
	Module getById(Long id);
	
}
