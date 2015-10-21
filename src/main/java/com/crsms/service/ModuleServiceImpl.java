package com.crsms.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.ModuleDao;
import com.crsms.dao.ModuleDaoImpl;
import com.crsms.domain.Module;

/**
 * 
 * @author St. Roman
 *
 */

@Service("moduleService")
@Transactional
public class ModuleServiceImpl implements ModuleService {
	
	private static Logger log = LogManager.getLogger(ModuleServiceImpl.class);
	
	@Autowired
	private ModuleDao moduleDao;

	@Override
	public void save(Module module) {
		log.info("in moduleService save(Module)");
		moduleDao.save(module);
		log.info("out moduleService save(Module)");
	}
	
	@Override
	public void update(Module module) {
		log.info("in moduleService update(Module)");
		moduleDao.update(module);
		log.info("out moduleService update(Module)");
	}

	@Override
	public void delete(Module module) {
		log.info("in moduleService delete(Module)");
		moduleDao.delete(module);
		log.info("out moduleService delete(Module)");
	}

	@Override
	public Module getById(Long id) {
		log.info("in moduleService getById(module id)");
		log.info("trying to get module");
		return moduleDao.getById(id);
	}

	@Override
	public List<Module> getAll() {
		log.info("in moduleService getAll(Module)");
		log.info("trying to get modules");
		return moduleDao.getAll();
	}

}
