package com.crsms.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.CourseDao;
import com.crsms.dao.ModuleDao;
import com.crsms.domain.Course;
import com.crsms.domain.Module;

/**
 * 
 * @author St. Roman
 *
 */

@Service("moduleService")
@Transactional
public class ModuleServiceImpl implements ModuleService {
	
	private static Logger logger = LogManager.getLogger(ModuleServiceImpl.class);
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private ModuleDao moduleDao;

	@Override
	public void add(Long courseId, Module module) {
		logger.info("in moduleService save(Module)");
		Course course = courseDao.getCourseById(courseId);
		module.setCourse(course);
		moduleDao.add(module);
		logger.info("out moduleService save(Module)");
	}
	
	@Override
	public void update(Module module) {
		logger.info("in moduleService update(Module)");
		Module existingModule = moduleDao.getById(module.getId());
		existingModule.setName(module.getName());
		existingModule.setDescription(module.getDescription());
		existingModule.setAvailable(module.getAvailable());
		moduleDao.update(existingModule);
		logger.info("out moduleService update(Module)");
	}

	@Override
	public void delete(Module module) {
		logger.info("in moduleService delete(Module)");
		moduleDao.delete(module);
		logger.info("out moduleService delete(Module)");
	}

	@Override
	public Module getById(Long id) {
		logger.info("in moduleService getById(module id)");
		logger.info("trying to get module");
		return moduleDao.getById(id);
	}

	@Override
	public List<Module> getAll() {
		logger.info("in moduleService getAll(Module)");
		logger.info("trying to get modules");
		return moduleDao.getAll();
	}
	
	@Override
	public List<Module> getAllByCourseId(Long courseId) {
		logger.info("in moduleService getAllByCourseId(courseId)");
		logger.info("trying to get modules");
		return moduleDao.getAllByCourseId(courseId);
	}

	@Override
	public void deleteById(Long id) {
		logger.info("in moduleService deleteById(module id)");
		moduleDao.deleteById(id);
		logger.info("out moduleService deleteById(module id)");
	}

}
