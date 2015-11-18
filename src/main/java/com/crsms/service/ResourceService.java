package com.crsms.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
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
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	void delete(Long resourceId, Long moduleId);

	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	void deleteById(Long id);
	
	List<Resource> getAllByModuleId(Long moduleId);
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	void save(String name, String path);
	
}
