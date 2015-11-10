package com.crsms.dao;

import java.util.List;

import com.crsms.domain.Resource;

/**
 * 
 * @author Valerii Motresku
 *
 */

public interface ResourceDao {
	
	void save(Resource resource);
	
	void update(Resource resource);
	
	void delete(Resource resource);
	
	void deleteById(Long id);
	
	Resource getById(Long id);
	
	List<Resource> getAll();
	
	List<Resource> getAllByModuleId(Long moduleId);
	
	public boolean onlyForModule(Long moduleId);
}
