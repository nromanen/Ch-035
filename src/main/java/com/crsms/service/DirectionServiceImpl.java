/**
 * 
 */
package com.crsms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.DirectionDao;
import com.crsms.domain.Direction;

/**
 * @author Yuri Kucheriavy
 *
 */

@Service("directionService")
@Transactional
public class DirectionServiceImpl implements DirectionService {
	
	@Autowired
    private DirectionDao directionDao;

	@Override
	public void saveDirection(Direction direction) {
		directionDao.saveDirection(direction);
		
	}

	@Override
	public List<Direction> getAllDirections() {
		return directionDao.getAllDirections();
	}

	@Override
	public Direction getDirectionById(Long id) {
		return directionDao.getDirectionById(id);
	}

	@Override
	public void updateDirection(Direction direction) {
		directionDao.updateDirection(direction);
	}

	@Override
	public Direction getDirectionByName(String name) {
		return directionDao.getDirectionByName(name);
	}

	@Override
	public void deleteDirection(Long id) {
		directionDao.deleteDirection(id);
		
	}

}
