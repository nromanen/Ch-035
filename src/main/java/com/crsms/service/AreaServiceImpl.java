/**
 * 
 */
package com.crsms.service;

import org.hibernate.HibernateException;
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
public class AreaServiceImpl extends BaseServiceImpl<Area> implements AreaService {
	
	@Autowired
    private AreaDao areaDao;
	
	@Override
	public Area getByName(String name) {
		return areaDao.getByName(name);
	}

	@Override
	public void deleteById(Long id) throws RuntimeException {
		try {
		  areaDao.deleteById(id);
		} catch (HibernateException e) {
		  throw new RuntimeException("Can't delete area. At least one course is attached to it.");
		}
	}
}