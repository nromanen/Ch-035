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
import com.crsms.domain.Resource;
import com.crsms.domain.User;
import com.crsms.dto.ModuleViewDto;
import com.crsms.dto.TestViewDto;
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
	private CourseDao courseDao;
	
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

	@Override
	public void deleteById(Long moduleId) {
		logger.info("in moduleService deleteById()");
		logger.info("checking module id");
		
		Module module = moduleDao.getById(moduleId);
		if (module == null) {
			throw new ElementNotFoundException();
		}
		
		logger.info("trying to delete module");
		Course course = courseDao.getByModule(module.getId());
		
		module.disable();
		this.freeResource(module);
		if (!course.getPublished()) {
			course.deleteModule(module);
			moduleDao.delete(module);
		}
		logger.info("out moduleService deleteById(module id)");
	}
	
	@Override
	public List<Module> getAllByCourseId(Long courseId) {
		logger.info("in moduleService getAllByCourseId(courseId)");
		logger.info("trying to get modules");
		return moduleDao.getAllByCourseId(courseId);
	}

	
	@Override
	public void addResource(Long moduleId, Resource resource) {
		Module module = getById(moduleId);
		resourceService.save(resource);
		module.addResource(resource);
		update(module);
	}
	
	@Override
	public void addExistingResource(Long moduleId, Resource resource) {
		Module module = getById(moduleId);
		module.addResource(resource);
		update(module);
	}

	@Override
	public void removeResource(Long moduleId, Resource resource) {
		Module module = moduleDao.getById(moduleId);
		this.removeResource(module, resource);
	}
	
	@Override
	public void removeResource(Module module, Resource resource) {
		module.removeResource(resource);
	}

	@Override
	public void freeResource(Module module) {
		module.getResources().clear();
		moduleDao.update(module);
	}

	@Override
	public void initModuleViewDto(ModuleViewDto moduleViewDto, User user) {
		boolean complete = true;
		boolean pass = true;
		double score = 0;
		double totalScore = 0;
		
		for (TestViewDto testViewDto : moduleViewDto.getTests()) {
			testService.initTestViewDto(testViewDto, user);
			if (testViewDto.getHasTestResult() && testViewDto.getComplete()) {
				score += testViewDto.getScore();
				if (!testViewDto.getPass()) pass = false;
			} else {
				complete = false;
				pass = false;
			}
			
			totalScore += 100;
		}
		
		moduleViewDto.setComplete(complete);
		moduleViewDto.setScore(score);
		moduleViewDto.setTotalScore(totalScore);
		moduleViewDto.setPass(pass);
	}

	public List<Module> getAllAssociatedWithResource(Long resourceId) {
		return moduleDao.getAllAssociatedWithResource(resourceId);
	}

}
