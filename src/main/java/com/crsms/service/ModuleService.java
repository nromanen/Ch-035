package com.crsms.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.crsms.domain.Module;
import com.crsms.domain.Resource;
import com.crsms.domain.User;
import com.crsms.dto.ModuleViewDto;

/**
 * 
 * @author St. Roman
 *
 */

public interface ModuleService extends BaseService<Module> {
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	void save(Long courseId, Module module);
	
	List<Module> getAllByCourseId(Long courseId);
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	void deleteById(Long moduleId);
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	void addResource(Long moduleId, Resource resource);
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	void addExistingResource(Long moduleId, Resource resource);
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	void removeResource(Long moduleId, Resource resource);
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	void removeResource(Module module, Resource resource);
	
	@PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
	void freeResource(Module module);


	void initModuleViewDto(ModuleViewDto moduleViewDto, User user);

	List<Module> getAllAssociatedWithResource(Long resourceId);

}
