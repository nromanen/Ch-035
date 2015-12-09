package com.crsms.config;

import java.util.Locale;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@ComponentScan
@EnableWebMvc
@PropertySource(value = { "classpath:mail.properties" })
class WebConfig extends WebMvcConfigurationSupport {
	
	@Autowired
	private Environment environment;
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
    }
    
	@Bean
    TilesViewResolver viewResolver() {
		TilesViewResolver viewResolver = new TilesViewResolver();
		return viewResolver;
	}
	
	@Bean
	TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions("WEB-INF/tiles/tiles.xml");
		tilesConfigurer.setPreparerFactoryClass(
				org.springframework.web.servlet.view.tiles3.SpringBeanPreparerFactory.class);
		return tilesConfigurer;    
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = 
				new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/resources/localization/crsms");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(new Locale("en"));
		resolver.setCookieName("localeCookie");
		return resolver;
	}
	
	@Bean(name = "multipartResolver")
	public StandardServletMultipartResolver multipartResolver() {
	    return new StandardServletMultipartResolver();
	}
	
	@Bean(name = "simpleMappingExceptionResolver")
	public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
	    SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
	    resolver.setDefaultErrorView("errorpage");
	    return resolver;
	}
	
	@Bean
	public JavaMailSenderImpl javaMailSenderImpl() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(environment.getProperty("mail.host"));
		mailSender.setPort(Integer.parseInt(environment.getProperty("mail.port")));
		mailSender.setUsername(environment.getProperty("mail.username"));
		mailSender.setPassword(environment.getProperty("mail.password"));
		Properties javaMailProperties = mailSender.getJavaMailProperties();
		javaMailProperties.put("mail.transport.protocol",
				environment.getProperty("mail.transport.protocol"));
		javaMailProperties.put("mail.smtp.auth",
				environment.getProperty("mail.smtp.auth"));
		javaMailProperties.put("mail.smtp.starttls.enable",
				environment.getProperty("mail.smtp.starttls.enable"));
		return mailSender;
	}
}