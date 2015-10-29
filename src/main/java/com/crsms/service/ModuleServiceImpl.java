package com.crsms.service;

import java.io.File;
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
import com.crsms.domain.Resource;
import com.crsms.exception.ElementNotFoundException;

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
		
		Module module = moduleDao.getById(id);
		if (module == null) {
			throw new ElementNotFoundException();
		}
		
		logger.info("out moduleService getById(module id)");
		return module;
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
		logger.info("checking course id");
		if (courseDao.getCourseById(courseId) == null) {
			throw new ElementNotFoundException();
		}
		
		logger.info("trying to get modules");
		List<Module> modules = moduleDao.getAllByCourseId(courseId);
		
		logger.info("out moduleService getAllByCourseId(courseId)");
		return modules;
	}

	@Override
	public void deleteById(Long id) {
		logger.info("in moduleService deleteById(module id)");
		logger.info("checking module id");
		if (moduleDao.getById(id) == null) {
			throw new ElementNotFoundException();
		}
		
		logger.info("trying to delete module");
		moduleDao.deleteById(id);
		logger.info("out moduleService deleteById(module id)");
	}
	
	@Override
	public void addResource(Long moduleId, Resource resource) {
		Module module = this.getById(moduleId);
		module.addResource(resource);
		this.update(module);
	}
	
	@Override
	public void addResource(Long moduleId, String name, String path) {
		Module module = this.getById(moduleId);
		Resource resource = new Resource();
        resource.setName(name);
        resource.setType(Resource.Type.FILE);
        resource.setUrl(path + File.separator + name);
		module.addResource(resource);
		this.update(module);
	}

}
