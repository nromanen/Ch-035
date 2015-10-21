package com.crsms.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
//@EnableWebMvc is turned off for console debug only!!!
//@EnableWebMvc
@ComponentScan(basePackages = "com.crsms")
@Import({ SecurityConfig.class, HibernateConfig.class})
public class RootConfig extends WebMvcConfigurerAdapter {

}