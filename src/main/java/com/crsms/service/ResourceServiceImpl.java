package com.crsms.service;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.ResourceDao;
import com.crsms.domain.FileBucket;
import com.crsms.domain.Resource;
import com.crsms.service.hibernate.query.ResourceQueryCustomizer;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Service("resourceService")
@Transactional
public class ResourceServiceImpl extends BaseServiceImpl<Resource>  implements ResourceService {
	
	private static Logger logger = LogManager.getLogger(ResourceServiceImpl.class);
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private FileService fileService;
	
	@Override
	public void delete(Long resourceId, Long moduleId) {
		logger.info("in resourceService delete(Long resourceId, Long moduleId)");
		Resource resource = resourceDao.getById(resourceId);
		moduleService.removeResource(moduleId, resource);
		logger.info("out resourceService delete(Long resourceId, Long moduleId)");
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
	public Resource prepareFileResource(String name, String path, Resource.StorageType storageType) {
		Resource resource = new Resource();
		resource.setName(name);
		resource.setPath(path);
		resource.setStorageType(storageType);
		resource.setType(Resource.Type.FILE);
		return resource;
	}
	
	@Override
	public Resource uploadRecivedFileAndPrepareResource(FileBucket fileBucket) throws IOException {	
		Resource.StorageType storageType = fileService.getResourceStorageTypeOption();
		return prepareFileResource(
				fileBucket.getFile().getOriginalFilename(),
				fileService.uploadFile(fileBucket.getFile(), storageType), 
				storageType);
	}

	@Override
	public List<Resource> getAllNotAssociatedWithModule(Long moduleId, ResourceQueryCustomizer queryCustomizer) {
		return resourceDao.getAllNotAssociatedWithModule(moduleId, queryCustomizer);
	}
	
}
