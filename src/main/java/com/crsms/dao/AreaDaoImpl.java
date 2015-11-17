/**
 * 
 */
package com.crsms.dao;

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

@Repository("areaDao")
public class AreaDaoImpl extends BaseDaoImpl<Area> implements AreaDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger logger = LogManager.getLogger(AreaDaoImpl.class);

	@Override
	public Area getByName(String name) {
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
	public void deleteById(Long id) {
		Query query = sessionFactory.getCurrentSession()
				.createQuery("DELETE FROM Area d WHERE d.id=:id")
				.setLong("id", id);
		query.executeUpdate();
	}
}