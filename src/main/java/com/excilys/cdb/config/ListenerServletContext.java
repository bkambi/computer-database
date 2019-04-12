package com.excilys.cdb.config;

import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


public class ListenerServletContext extends AbstractContextLoaderInitializer {

	  @Override
	  protected WebApplicationContext createRootApplicationContext() {
	      AnnotationConfigWebApplicationContext rootContext
	        = new AnnotationConfigWebApplicationContext();
	      rootContext.register(SpringConfiguration.class);
	      return rootContext;
	}

}
