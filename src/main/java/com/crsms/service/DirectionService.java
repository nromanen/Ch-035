/**
 * 
 */
package com.crsms.service;

import java.util.List;

import com.crsms.domain.Direction;

/**
 * @author Yuri Kucheriavy
 *
 */
public interface DirectionService {
void saveDirection(Direction direction);
	
	List<Direction> getAllDirections();
	
	Direction getDirectionById(Long id);

	void updateDirection(Direction direction);
	
	Direction getDirectionByName(String name);
	
	void deleteDirection(Long id);
	
}
