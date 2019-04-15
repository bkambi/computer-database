package com.excilys.cdb.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationInitializer implements WebApplicationInitializer {

	Logger logger = Logger.getLogger(ApplicationInitializer.class);
	// Called first when the application starts loading.
	public void onStartup(ServletContext servletContext) throws ServletException {
		logger.info("Inside application initializer...");

		// Registering the class that incorporates the annotated DispatcherServlet
		// configuration of spring
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringConfiguration.class);

		// Adding the listener for the rootContext
		servletContext.addListener(new ContextLoaderListener(rootContext));

		// Registering the dispatcher servlet mappings.
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
				new DispatcherServlet(rootContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

}