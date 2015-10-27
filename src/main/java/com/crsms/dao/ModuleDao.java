package com.crsms.dao;

import java.util.List;

import com.crsms.domain.Module;

/**
 * 
 * @author St. Roman
 *
 */

public interface ModuleDao {
	
	void add(Module module);
	
	void update(Module module);
	
	void delete(Module module);
	
	Module getById(Long id);
	
	List<Module> getAll();
	
	List<Module> getAllByCourseId(Long courseId);

	void deleteById(Long id);
	
}
