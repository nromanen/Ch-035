package com.crsms.config;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.crsms.service.FileService;

public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	private static final String DEFAULT_TEMP_LOCATION = "temp";
	private static final Integer DEFAULT_MAX_FILE_SIZE = 5242880;
	private static final Integer DEFAULT_MAX_REQUEST_SIZE = 20971520;
	private static final Integer DEFAULT_FILE_SIZE_TRESHOLD = 0;
	
	private final Logger logger = LogManager.getLogger(ServletInitializer.class);
	
	// Temporary location where files will be stored.
	private String tempLocation;
	// 5MB : Max file size.
	private long maxFileSize;
	// 20MB : Total request size containing Multi part.
	private long maxRequestSize;
	// Size threshold after which files will be written to disk.
	private int fileSizeTreshold;
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {ServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	@Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
    }
 
    
	private MultipartConfigElement getMultipartConfigElement() {
        Properties props = new Properties();
        try {
			props.load(ServletInitializer.class.getResourceAsStream("/application.properties"));
			tempLocation = props.getProperty("multipart.temp.location");
			maxFileSize = Long.parseLong(props.getProperty("multipart.max.file.size"));
			maxRequestSize = Long.parseLong(props.getProperty("multipart.max.request.size"));
			fileSizeTreshold = Integer.parseInt(props.getProperty("multipart.file.size.treshold"));
		} catch (IOException e) {
			setDefaultMultipartConfigProperties();
			logger.error("Could not load application.properies for MultipartConfigElement."
					+ " Default values is being used");
		}
    	File tempDir = new File(FileService.RESOURCE_PATH + File.separator + tempLocation);
        if (!tempDir.exists()) {
        	tempDir.mkdirs();
        }
    	MultipartConfigElement multipartConfigElement = 
    			new MultipartConfigElement(tempDir.getAbsolutePath(), maxFileSize, 
    										maxRequestSize, fileSizeTreshold);
        return multipartConfigElement;
    }

	private void setDefaultMultipartConfigProperties() {
		tempLocation = DEFAULT_TEMP_LOCATION;
		maxFileSize = DEFAULT_MAX_FILE_SIZE;
		maxRequestSize = DEFAULT_MAX_REQUEST_SIZE;
		fileSizeTreshold = DEFAULT_FILE_SIZE_TRESHOLD;
	}
    
    @Override
    protected void registerDispatcherServlet(ServletContext servletContext) {
		String servletName = getServletName();
		Assert.hasLength(servletName, "getServletName() must not return empty or null");
		WebApplicationContext servletAppContext = createServletApplicationContext();
		Assert.notNull(servletAppContext, "createServletApplicationContext() did not return" 
			  + "an application context for servlet [" + servletName + "]");

		DispatcherServlet dispatcherServlet = createDispatcherServlet(servletAppContext);
		dispatcherServlet.setContextInitializers(getServletApplicationContextInitializers());
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		ServletRegistration.Dynamic registration = 
				servletContext.addServlet(servletName, dispatcherServlet);
		Assert.notNull(registration, "Failed to register servlet with name '" + servletName + "'." 
			  + "Check if there is another servlet registered under the same name.");

		registration.setLoadOnStartup(1);
		registration.addMapping(getServletMappings());
		registration.setAsyncSupported(isAsyncSupported());

		Filter[] filters = getServletFilters();
		if (!ObjectUtils.isEmpty(filters)) {
			for (Filter filter : filters) {
				registerServletFilter(servletContext, filter);
			}
		}
		customizeRegistration(registration);
	}
    
    @Override
	protected Filter[] getServletFilters() {
		Filter[] singleton = {new CORSFilter()};
		return singleton;
	}

}