package com.crsms.service;

import java.util.List;

import com.crsms.domain.Module;

/**
 * 
 * @author St. Roman
 *
 */

public interface ModuleService {
	
	void save(Module module);
	
	void update(Module module);
	
	void delete(Module module);
	
	Module getById(Long id);
	
	List<Module> getAll();
	
	void deleteById(Long id);
	
}
