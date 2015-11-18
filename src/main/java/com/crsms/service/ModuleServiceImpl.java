package com.crsms.service;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ModuleServiceImpl extends BaseServiceImpl<Module> implements ModuleService {
	
	private static Logger logger = LogManager.getLogger(ModuleServiceImpl.class);
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private ModuleDao moduleDao;
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private TestService testService;

	@Override
	public void save(Long courseId, Module module) {
		logger.info("in moduleService save(courseId, Module)");
		Course course = courseService.getById(courseId);
		course.addModule(module);
		moduleDao.save(module);
		logger.info("out moduleService save(courseId, Module)");
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

	/*@Override
	public void delete(Long courseId, Module module) {
		logger.info("in moduleService delete(Module)");
		if(moduleDao.hasTestResults(module.getId())){
			module.setDisable(true);
			moduleDao.update(module);
		} else {
			
//			for(Resource resource : module.getResources()){
//				resourceService.delete(resource.getId(), module.getId());
//			}
			//TODO: delete tests
			Course course = courseDao.getById(courseId);//TODO: mybe to DAO
			course.deleteModule(module);
			moduleDao.delete(module);
		}
		
		logger.info("out moduleService delete(Module)");
	}*/
	
	@Override
	public List<Module> getAllByCourseId(Long courseId) {
		logger.info("in moduleService getAllByCourseId(courseId)");
		logger.info("checking course id");
		if (courseService.getById(courseId) == null) {
			throw new ElementNotFoundException();
		}
		
		logger.info("trying to get modules");
		List<Module> modules = moduleDao.getAllByCourseId(courseId);
		
		logger.info("out moduleService getAllByCourseId(courseId)");
		return modules;
	}

	/*@Override
	public void deleteById(Long courseId, Long moduleId) {
		logger.info("in moduleService deleteById()");
		logger.info("checking module id");
		
		Module module = moduleDao.getById(moduleId);
		if (module == null) {
			throw new ElementNotFoundException();
		}
		
		logger.info("trying to delete module");
		//moduleDao.deleteById(moduleId);
		delete(courseId, module);
		logger.info("out moduleService deleteById(module id)");
	}*/
	
	@Override
	public void addResource(Long moduleId, Resource resource) {
		Module module = this.getById(moduleId);
		resourceService.save(resource);
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
        resourceService.save(resource);
		module.addResource(resource);
		this.update(module);
	}
	
	@Override
	public void removeResource(Long moduleId, Resource resource) {
		Module module = moduleDao.getById(moduleId);
		this.removeResource(module, resource);
	}
	
	@Override
	public void removeResource(Module module, Resource resource) {
		module.removeResource(resource);
		moduleDao.update(module);
		//TODO:check for delete
		//resourceService.delete(resource);
	}

	@Override
	public void disable(Module module) {
		moduleDao.disable(module);
		//TODO:
//		for(Test test : module.getTests()){
//			testService.disable(test);
//		}
		
	}

	@Override
	public void disable(Long moduleId) {
		Module module = moduleDao.getById(moduleId);
		this.disable(module);
		
	}

	@Override
	public void freeResource(Module module) {
		for (Resource resource : module.getResources()) {
			this.removeResource(module, resource);
		}
		
	}

}
