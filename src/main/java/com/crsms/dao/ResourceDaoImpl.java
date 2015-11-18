package com.crsms.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.crsms.domain.Resource;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Repository
public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements ResourceDao {
	
	private static Logger logger = LogManager.getLogger(ResourceDaoImpl.class);
	
	@Override
	public void deleteById(Long id) {
		
		String hql = " DELETE Resource"   
	               + " WHERE id = :resource_id";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("resource_id", id);
		try {
			query.executeUpdate();
		} catch (Exception e) {
			logger.error("Error deleteById resource with id=" + id + ": " + e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> getAllByModuleId(Long moduleId) {
		
		List<Resource> results = null;

		String hql = "select mr from Module m join m.resources mr where m.id = :moduleId";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("moduleId", moduleId);
		try {
			results = (List<Resource>) query.list();
		} catch (Exception e) {
			logger.error("Error getAll resources:" + e);
		}
		return results;
		
	}

}
