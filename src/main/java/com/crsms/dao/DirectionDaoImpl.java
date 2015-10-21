/**
 * 
 */
package com.crsms.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crsms.domain.Direction;

/**
 * @author Yuri Kucheriavy
 *
 */

@Repository("directionDao")
public class DirectionDaoImpl implements DirectionDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger logger = LogManager.getLogger(DirectionDaoImpl.class);

	@Override
	public void saveDirection(Direction direction) {
		try {
			sessionFactory.getCurrentSession().save(direction);
		} catch (Exception e) {
			logger.error("Error saveDirection: " + e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Direction> getAllDirections() {
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Direction d ORDER BY d.id");
		return query.list();
	}

	@Override
	public Direction getDirectionById(Long id) {
		Direction direction = null;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			direction = (Direction) session.get(Direction.class, id);
		} catch (Exception e) {
			logger.error("Error getTest: " + e);
		}
		session.clear();
		return direction;
	}

	@Override
	public void updateDirection(Direction direction) {
		try {
			sessionFactory.getCurrentSession().update(direction);
		} catch (Exception e) {
			logger.error("Error updateDirection: " + e);
		}
	}
	
	@Override
	public Direction getDirectionByName(String name) {
		Direction direction = null;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			direction = (Direction) session.get(Direction.class, name);
		} catch (Exception e) {
			logger.error("Error getTest: " + e);
		}
		session.clear();
		return direction;
		
	}

	@Override
	public void deleteDirection(Long id) {
		Query query = sessionFactory.getCurrentSession()
				.createQuery("DELETE FROM Direction d WHERE d.id=:id").setLong("id", id);
		query.executeUpdate();
	}

}
