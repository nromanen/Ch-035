package com.crsms.service;

import java.util.List;

import com.crsms.domain.Module;
import com.crsms.domain.Resource;

/**
 * 
 * @author St. Roman
 *
 */

public interface ModuleService {
	
	void add(Long courseId, Module module);
	
	void update(Module module);
	
	void delete(Module module);
	
	Module getById(Long id);
	
	List<Module> getAll();
	
	List<Module> getAllByCourseId(Long courseId);
	
	void deleteById(Long id);
	
	void addResource(Long moduleId, Resource resource);
	
	void addResource(Long moduleId, String name, String path);
	
}
