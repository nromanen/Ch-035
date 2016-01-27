package com.crsms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.RoleDao;
import com.crsms.domain.Role;

/**
 * 
 * @author Roman Romaniuk
 *
 */
@Service("roleService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roledao;

	@Override
	public List<Role> getAllRoles() {
		return roledao.getAllRoles();
	}

	@Override
	public Role getRoleById(Long id) {
		return roledao.getRoleById(id);
	}

	@Override
	public Role getRoleByName(String name) {
		return roledao.getRoleByName(name);
	}

}
