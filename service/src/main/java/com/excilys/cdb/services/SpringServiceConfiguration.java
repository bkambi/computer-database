package com.excilys.cdb.services;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.excilys.cdb.dao.repositories.SpringPersistenceConfiguration;

@Configuration
@ComponentScan(basePackageClasses = SpringPersistenceConfiguration.class)
public class SpringServiceConfiguration  {
	Logger logger = Logger.getLogger(SpringServiceConfiguration.class);
	
	@Bean
	public void initSpringServiceConfiguration() {
		logger.info("Inside SpringServiceConfiguration initializer ...");
	}
	
}
