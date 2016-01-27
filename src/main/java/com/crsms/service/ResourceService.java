package com.crsms.service;

import java.io.IOException;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.domain.FileBucket;
import com.crsms.domain.Resource;
import com.crsms.service.hibernate.query.ResourceQueryCustomizer;

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
	Resource prepareFileResource(String name, String path, Resource.StorageType storageType);
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	Resource uploadRecivedFileAndPrepareResource(FileBucket fileBucket) throws IOException;

	List<Resource> getAllNotAssociatedWithModule(Long moduleId, ResourceQueryCustomizer queryCustomizer);
	
}
