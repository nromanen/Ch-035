package com.crsms.config;

import java.io.File;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.crsms.config.RootConfig;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	// Temporary location where files will be stored.
	private static final String LOCATION = "temp";
	// 5MB : Max file size.
	private static final long MAX_FILE_SIZE = 5242880;
	// 20MB : Total request size containing Multi part.
	private static final long MAX_REQUEST_SIZE = 20971520;
	// Size threshold after which files will be written to disk.
	private static final int FILE_SIZE_THRESHOLD = 0;
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
    }
 
    private MultipartConfigElement getMultipartConfigElement() {
    	String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "storage/resources/" + LOCATION);
        if (!dir.exists()) {
        	dir.mkdirs();
        }
    	MultipartConfigElement multipartConfigElement = new MultipartConfigElement( dir.getAbsolutePath(), MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
        return multipartConfigElement;
    }
    
    @Override
    protected void registerDispatcherServlet(ServletContext servletContext) {
		String servletName = getServletName();
		Assert.hasLength(servletName, "getServletName() must not return empty or null");

		WebApplicationContext servletAppContext = createServletApplicationContext();
		Assert.notNull(servletAppContext,
				"createServletApplicationContext() did not return an application " +
				"context for servlet [" + servletName + "]");

		DispatcherServlet dispatcherServlet = createDispatcherServlet(servletAppContext);
		dispatcherServlet.setContextInitializers(getServletApplicationContextInitializers());
		
		// the only change in overriden method
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		//

		ServletRegistration.Dynamic registration = servletContext.addServlet(servletName, dispatcherServlet);
		Assert.notNull(registration,
				"Failed to register servlet with name '" + servletName + "'." +
				"Check if there is another servlet registered under the same name.");

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
}