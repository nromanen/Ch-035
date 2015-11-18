package com.crsms.config;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.crsms.interceptor.BreadcrumbsInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.crsms")
@Import({ SecurityConfig.class, HibernateConfig.class })
public class RootConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		
		BreadcrumbsInterceptor breadcrumbsInterceptor = new BreadcrumbsInterceptor();
		
		registry.addInterceptor(localeChangeInterceptor);
		registry.addInterceptor(breadcrumbsInterceptor)
				.excludePathPatterns("/courses/*/modules/*/tests/*/questions/add/question-form")
				.excludePathPatterns("/resources/downloadfile/**")
				.excludePathPatterns("/api/**");
	}
	
	/**
	 * Jackson serialization mappers
	 * 
	 * Jackson2ObjectMapperFactoryBean allows not to register JodaModule explicitly 
	 * Jackson2ObjectMapperFactoryBean uses Jackson2ObjectMapperBuilder which registers 
	 * the module automatically if it's available on the classpath.
     */
    private ObjectMapper objectMapper() {
        Jackson2ObjectMapperFactoryBean bean = new Jackson2ObjectMapperFactoryBean();
        bean.setIndentOutput(true);
        bean.setSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        bean.afterPropertiesSet();
        ObjectMapper objectMapper = bean.getObject();
        objectMapper.registerModule(new JodaModule());
        return objectMapper;
    }

    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper());
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(this.mappingJackson2HttpMessageConverter());
    }

}