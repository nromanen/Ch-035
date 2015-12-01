package com.crsms.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crsms.domain.Option;

@Repository
public class OptionDaoImpl implements OptionDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private final Logger logger = LogManager.getLogger(OptionDaoImpl.class);
	
	@SuppressWarnings(value = "unchecked")
	@Override
	public List<Option> getAll() {
		try {
			return (List<Option>) sessionFactory.getCurrentSession().createCriteria(Option.class).list();
		} catch (Exception e) {
			logger.error("Error in getAll. " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Option getOptionValue(String key) {
		try {
			return (Option)sessionFactory.getCurrentSession().get(Option.class, key);
		} catch (Exception e) {
			logger.error("Error in getAppOptionValue. " + e.getMessage());
			throw e;
		}
	}
	
}
