package com.crsms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crsms.domain.Role;
import com.crsms.domain.User;
/**
 * 
 * @author Roman Romaniuk
 *
 */
@Repository ("roleDao")
public class RoleDaoImpl implements RoleDao {
	@Autowired
	private SessionFactory sessionFactory;
	private static Logger log = LogManager.getLogger(RoleDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAllRoles() {
		List<Role> roles = new ArrayList<>();
		try {
			log.debug("get all roles");
			Query query = sessionFactory.getCurrentSession().getNamedQuery(
					Role.ALL_SORTED);
			roles = query.list();
		} catch (Exception e) {
			log.error("Error get all roles " + e);
		}
		return roles;
	}

	@Override
	public Role getRoleById(Long id) {
		Role role = new Role();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			role = (Role) session.get(Role.class, id);
		} catch (Exception e) {
			log.error("Error get role by Id: " + id + e);
		} finally {
			session.clear();
		}
		return role;
	}
	
	
	
	
}
