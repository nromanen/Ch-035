package com.crsms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.ModuleDao;
import com.crsms.domain.Module;

/**
 * 
 * @author St. Roman
 *
 */

@Service("moduleService")
@Transactional
public class ModuleServiceImpl implements ModuleService {
	
	@Autowired
	private ModuleDao moduleDao;

	@Override
	public void save(Module module) {
		moduleDao.save(module);
	}

	@Override
	public void delete(Module module) {
		moduleDao.delete(module);
	}

	@Override
	public Module getById(Long id) {
		return moduleDao.getById(id);
	}

}
