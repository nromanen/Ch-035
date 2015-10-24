package com.crsms.service;

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
		
		

	}

	@Override
	public void update(Resource resource) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Resource resource) {
		// TODO Auto-generated method stub

	}

	@Override
	public Resource getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resource> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

}
