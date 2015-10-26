package com.crsms.dao;

import java.util.List;

import com.crsms.domain.Area;

/**
 * 
 * @author Yuri Kicheriavy
 *
 */

public interface AreaDao {
	
	void saveArea(Area area);
	
	List<Area> getAllAreas();
	
	Area getAreaById(Long id);

	void updateArea(Area area);
	
	Area getAreaByName(String name);
	
	void deleteArea(Long id);
	
}
