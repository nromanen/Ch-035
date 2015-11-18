package com.crsms.dao;

import com.crsms.domain.Area;

/**
 * 
 * @author Yuri Kicheriavy
 *
 */

public interface AreaDao extends BaseDao<Area> {
	
	Area getByName(String name);
	
	void deleteById(Long id);
	
}
