/**
 * 
 */
package com.crsms.service;

import com.crsms.domain.Area;

/**
 * @author Yuri Kucheriavy
 *
 */
public interface AreaService extends BaseService<Area> {

	Area getByName(String name);
	
	void deleteById(Long id);
	
}
