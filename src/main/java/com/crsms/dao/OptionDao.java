package com.crsms.dao;

import java.util.List;

import com.crsms.domain.Option;

public interface OptionDao {
	
	List<Option> getAll();
	
	Option getOptionValue(String key);
	
	void update(Option option);
	
}
