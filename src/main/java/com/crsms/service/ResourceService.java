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
public interface ResourceService extends BaseService<Resource> {
	
	void delete(Long resourceId, Long moduleId);

	void deleteById(Long id);
	
	List<Resource> getAllByModuleId(Long moduleId);
	
	void save(String name, String path);
}
