package com.crsms.dao;

import java.util.List;

import com.crsms.domain.Role;
import com.crsms.domain.User;
/**
 * 
 * @author Roman Romaniuk
 *
 */
public interface RoleDao {

	List<Role> getAllRoles();
	
	Role getRoleById(Long id);
	
	Role getRoleByName(String name);
}
