package com.crsms.service;

import java.util.List;

import com.crsms.domain.Module;
import com.crsms.domain.Resource;

/**
 * 
 * @author St. Roman
 *
 */

public interface ModuleService extends BaseService<Module> {
	
	void save(Long courseId, Module module);
	
	//void delete(Long courseId, Module module);
	
	List<Module> getAllByCourseId(Long courseId);
	
	//void deleteById(Long courseId, Long moduleId);
	
	void addResource(Long moduleId, Resource resource);
	
	void addResource(Long moduleId, String name, String path);
	
	void removeResource(Long moduleId, Resource resource);
	void removeResource(Module module, Resource resource);

	void disable(Module module);
	void disable(Long moduleId);
	
	void freeResource(Module module);
	
	
}
