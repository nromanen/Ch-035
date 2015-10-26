/**
 * 
 */
package com.crsms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.AreaDao;
import com.crsms.domain.Area;

/**
 * @author Yuri Kucheriavy
 *
 */

@Service("areaService")
@Transactional
public class AreaServiceImpl implements AreaService {
	
	@Autowired
    private AreaDao areaDao;

	@Override
	public void saveArea(Area area) {
		areaDao.saveArea(area);
		
	}

	@Override
	public List<Area> getAllAreas() {
		return areaDao.getAllAreas();
	}

	@Override
	public Area getAreaById(Long id) {
		return areaDao.getAreaById(id);
	}

	@Override
	public void updateArea(Area area) {
		areaDao.updateArea(area);
	}

	@Override
	public Area getAreaByName(String name) {
		return areaDao.getAreaByName(name);
	}

	@Override
	public void deleteArea(Long id) {
		areaDao.deleteArea(id);
		
	}

}
