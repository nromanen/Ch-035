package com.crsms.service;

import java.util.List;
import java.util.Properties;

import com.crsms.domain.Option;

public interface OptionService {
	
	Properties getOptionsAsProperties();
	
	List<Option> getAll();
	
	Option getOptionValue(String key);
	
}
