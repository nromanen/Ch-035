package com.crsms.dao;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.crsms.domain.Resource;
import com.crsms.service.hibernate.query.ResourceQueryCustomizer;

/**
 * 
 * @author Valerii Motresku
 *
 */

public interface ResourceDao extends BaseDao<Resource> {
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	void deleteById(Long id);
	
	List<Resource> getAllByModuleId(Long moduleId);

	List<Resource> getAllNotAssociatedWithModule(Long moduleId, ResourceQueryCustomizer queryCustomizer);
	
}
