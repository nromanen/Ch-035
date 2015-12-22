package com.crsms.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Repository;

import com.crsms.domain.Module;
import com.crsms.domain.Resource;
import com.crsms.service.hibernate.query.ResourceQueryCustomizer;

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
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> getAllByModuleId(Long moduleId) {
		try {
			return (List<Resource>) getSessionFactory().getCurrentSession()
					.getNamedQuery(Resource.GET_ALL_BY_MODULE_ID)
					.setParameter("moduleId", moduleId)
					.list();
		} catch (Exception e) {
			logger.error("Error getAll resources:" + e);
			throw e;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> getAllNotAssociatedWithModule(Long moduleId, ResourceQueryCustomizer queryCustomizer) {
		try {
			return (List<Resource>) queryCustomizer.customize(
					getSessionFactory().getCurrentSession()
					.createCriteria(Resource.class)
						.add(Subqueries.propertyNotIn("id", 
							DetachedCriteria.forClass(Module.class, "module")
								.createAlias("module.resources", "moduleResources")
								.add(Restrictions.idEq(moduleId))
								.setProjection(Property.forName("moduleResources.id"))
						))
					).list();
		} catch (Exception e) {
			logger.error("Error getAllNotAssociatedWithModule resources:" + e);
			throw e;
		}
	}

}
