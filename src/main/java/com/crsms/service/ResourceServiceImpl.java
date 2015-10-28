package com.crsms.service;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.ResourceDao;
import com.crsms.domain.Resource;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Service("resourceService")
@Transactional
public class ResourceServiceImpl implements ResourceService {
	
	private static Logger logger = LogManager.getLogger(ResourceServiceImpl.class);
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Override
	public void save(Resource resource) {
		logger.info("in resourceService save(Resource)");
		resourceDao.save(resource);
		logger.info("out resourceService save(Resource)");
	}
	
	@Override
	public void update(Resource resource) {
		logger.info("in resourceService update(Resource)");
		resourceDao.update(resource);
		logger.info("out resourceService update(Resource)");
	}

	@Override
	public void delete(Resource resource) {
		logger.info("in resourceService delete(Resource)");
		resourceDao.delete(resource);
		logger.info("out resourceService delete(Resource)");
	}

	@Override
	public Resource getById(Long id) {
		logger.info("in resourceService getById(resource id)");
		logger.info("trying to get resource");
		return resourceDao.getById(id);
	}

	@Override
	public List<Resource> getAll() {
		logger.info("in resourceService getAll(Resource)");
		logger.info("trying to get resources");
		return resourceDao.getAll();
	}

	@Override
	public void deleteById(Long id) {
		logger.info("in resourceService deleteById(resource id)");
		resourceDao.deleteById(id);
		logger.info("out resourceService deleteById(resource id)");
	}
	
	@Override
	public List<Resource> getAllByModuleId(Long moduleId) {
		logger.info("in resourceService getAllByModuleId(Resource)");
		logger.info("trying to get resources");
		return resourceDao.getAllByModuleId(moduleId);
	}
	
	@Override
	public void save(String name, String path) {
		Resource resource = new Resource();
        resource.setName(name);
        resource.setType(Resource.Type.FILE);
        resource.setUrl(path + File.separator + name);
        this.save(resource);
	}
}
