package com.crsms.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = "com.crsms",
			   excludeFilters = {@Filter(type = FilterType.ANNOTATION, value = Controller.class)})
@Import({SecurityConfig.class, HibernateConfig.class})
public class RootConfig {
	
}