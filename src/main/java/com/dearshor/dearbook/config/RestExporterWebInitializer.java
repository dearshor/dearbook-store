package com.dearshor.dearbook.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class RestExporterWebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		// TODO Auto-generated method stub
		AnnotationConfigWebApplicationContext rootCtx = new AnnotationConfigWebApplicationContext();
	    rootCtx.register(
	    		RepositoryContext.class
	    );
	    servletContext.addListener(new ContextLoaderListener(rootCtx));

	    AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
	    webCtx.register(RepositoryRestMvcConfiguration.class);

	    DispatcherServlet dispatcherServlet = new DispatcherServlet(webCtx);
	    ServletRegistration.Dynamic reg = servletContext.addServlet("rest-exporter", dispatcherServlet);
	    reg.setLoadOnStartup(1);
	    reg.addMapping("/*");
	}

}
