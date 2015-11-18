/**
 * 
 */
package com.crsms.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.crsms.domain.Area;

/**
 * @author Yuri Kucheriavy
 *
 */

@Repository
public class AreaDaoImpl extends BaseDaoImpl<Area> implements AreaDao {

  public AreaDaoImpl() {
    super(Area.class);
  }
  
	@Override
	public Area getByName(String name) {
		Area area = null;
		Session session = null;
		try {
			session = this.getSessionFactory().getCurrentSession();
			area = (Area) session.get(Area.class, name);
		} catch (Exception e) {
			this.getLogger().error("Error getTest: " + e);
		}
		session.clear();
		return area;		
	}

	@Override
	public void deleteById(Long id) {
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery("DELETE FROM Area d WHERE d.id=:id")
				.setLong("id", id);
		query.executeUpdate();
	}
}