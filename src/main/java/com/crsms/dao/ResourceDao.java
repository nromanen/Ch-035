package com.crsms.dao;

import java.util.List;

import com.crsms.domain.Resource;

/**
 * 
 * @author Valerii Motresku
 *
 */

public interface ResourceDao extends BaseDao<Resource> {
	
	void deleteById(Long id);
	
	List<Resource> getAllByModuleId(Long moduleId);
}
