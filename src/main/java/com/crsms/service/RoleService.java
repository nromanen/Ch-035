package com.crsms.service;

import java.util.List;

import com.crsms.domain.Role;
/**
 * 
 * @author Roman Romaniuk
 *
 */
public interface RoleService {
	
	List<Role> getAllRoles();
	
	Role getRoleById(Long id);
}
