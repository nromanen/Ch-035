package com.crsms.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;





import com.crsms.domain.Resource;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Repository
public class ResourceDaoImpl implements ResourceDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger logger = LogManager.getLogger(ResourceDaoImpl.class);
	
	@Override
	public void save(Resource resource) {
		
		try {
			sessionFactory.getCurrentSession().persist(resource);
		} catch (Exception e) {
			logger.error("Error save resource with name=" 
						+ resource.getName() + ": " + e);
		}

	}

	@Override
	public void update(Resource resource) {
		
		try {
			sessionFactory.getCurrentSession().update(resource);
		} catch (Exception e) {
			logger.error("Error update resource with id="
						+ resource.getId() + ": " + e);
		}
		
	}

	@Override
	public void delete(Resource resource) {
		
		try {
			sessionFactory.getCurrentSession().delete(resource);
		} catch (Exception e) {
			logger.error("Error delete resource with id=" 
						+ resource.getId() + ": " + e);
		}

	}
	
	@Override
	public void deleteById(Long id) {
		
		String hql = " DELETE Resource"   
	               + " WHERE id = :resource_id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("resource_id", id);
		try {
			query.executeUpdate();
		} catch (Exception e) {
			logger.error("Error deleteById resource with id=" + id + ": " + e);
		}
	}

	@Override
	public Resource getById(Long id) {
		Resource resource = null;
		try {
			resource = Resource.class.cast(sessionFactory.getCurrentSession()
														 .get(Resource.class, id));
		} catch (Exception e) {
			logger.error("Error getById resource with id=" 
						+ id + ": " + e);
		}
		return resource;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Resource> getAll() {

		List<Resource> results = null;
		String hql = "FROM Resource ORDER BY id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		try {
			results = (List<Resource>) query.list();
		} catch (Exception e) {
			logger.error("Error getAll resources:" + e);
		}
		return results;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> getAllByModuleId(Long moduleId) {
		
		List<Resource> results = null;

		String hql = "select mr from Module m join m.resources mr where m.id = :moduleId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("moduleId", moduleId);
		try {
			results = (List<Resource>) query.list();
		} catch (Exception e) {
			logger.error("Error getAll resources:" + e);
		}
		return results;
		
	}

	@Override
	public boolean onlyForModule(Long moduleId) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery(""
					+ " SELECT COUNT(*) as count"
					+ " FROM module_resource"
					+ " WHERE module_id != :module_id "
					+ " LIMIT 1"
			).setParameter("module_id", moduleId);
			long count = ((BigInteger) query.uniqueResult()).longValue();
			if (count > 0) {
				return false;
			} else {
				return true;
			}
	}
	
	

}
