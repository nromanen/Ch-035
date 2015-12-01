package com.crsms.service;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.OptionDao;
import com.crsms.domain.Option;

@Service
@Transactional
public class OptionServiceImpl implements OptionService {
	
	@Autowired
	private OptionDao optionDao;
	
	private Properties properties;

	private Properties getPropertiesFromList(List<Option> list) {
		Properties properties = new Properties();
		for (Option option : list) {
			properties.setProperty(option.getKey(), option.getValue());
		}
		return properties;
	}
	
	@Override
	public Properties getOptionsAsProperties() {
		if (properties == null) {
			properties = getPropertiesFromList(optionDao.getAll());
		}
		return properties;
	}

	@Override
	public List<Option> getAll() {
		return optionDao.getAll();
	}

	@Override
	public Option getOptionValue(String key) {
		return optionDao.getOptionValue(key);
	}
	
}
