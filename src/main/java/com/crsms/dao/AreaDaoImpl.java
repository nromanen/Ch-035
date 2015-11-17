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

import com.crsms.domain.Area;

/**
 * @author Yuri Kucheriavy
 *
 */

@Repository
public class AreaDaoImpl implements AreaDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger logger = LogManager.getLogger(AreaDaoImpl.class);

	@Override
	public void saveArea(Area area) {
		try {
			sessionFactory.getCurrentSession().save(area);
		} catch (Exception e) {
			logger.error("Error saveArea: " + e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> getAllAreas() {
		Query query = sessionFactory.getCurrentSession()
		        .createQuery("FROM Area d ORDER BY d.id");
		return query.list();
	}

	@Override
	public Area getAreaById(Long id) {
		Area area = null;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			area = (Area) session.get(Area.class, id);
		} catch (Exception e) {
			logger.error("Error getTest: " + e);
		}
		session.clear();
		return area;
	}

	@Override
	public void updateArea(Area area) {
		try {
			sessionFactory.getCurrentSession().update(area);
		} catch (Exception e) {
			logger.error("Error updateArea: " + e);
		}
	}
	
	@Override
	public Area getAreaByName(String name) {
		Area area = null;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			area = (Area) session.get(Area.class, name);
		} catch (Exception e) {
			logger.error("Error getTest: " + e);
		}
		session.clear();
		return area;
		
	}

	@Override
	public void deleteArea(Long id) {
		Query query = sessionFactory.getCurrentSession()
				.createQuery("DELETE FROM Area d WHERE d.id=:id")
				.setLong("id", id);
		query.executeUpdate();
	}

}
