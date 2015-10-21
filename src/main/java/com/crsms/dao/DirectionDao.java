package com.crsms.dao;

import java.util.List;

import com.crsms.domain.Direction;

/**
 * 
 * @author Yuri Kicheriavy
 *
 */

public interface DirectionDao {
	
	void saveDirection(Direction direction);
	
	List<Direction> getAllDirection();
	
	Direction getDirectionById(Long id);

	void updateDirection(Direction direction);
	
	Direction getDirectionByName(String name);
	
	void deleteDirection(Long id);
	
}
