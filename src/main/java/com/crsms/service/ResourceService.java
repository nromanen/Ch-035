package com.crsms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.domain.Resource;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Service("courseService")
@Transactional
public interface ResourceService {
	
	void save(Resource resource);
	
	void update(Resource resource);
	
	void delete(Resource resource);
	
	Resource getById(Long id);
	
	List<Resource> getAll();

	void deleteById(Long id);
	
	List<Resource> getAllByModuleId(Long moduleId);
	
	void save(String name, String path);
}
