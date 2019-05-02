package com.excilys.cdb.service.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.excilys.cdb.service.config.SpringServiceConfiguration;

@Configuration
@ComponentScan(basePackages = {"com.excilys.cdb.services","com.excilys.cdb.service.config"})
public class SpringServiceConfiguration  {
	Logger logger = Logger.getLogger(SpringServiceConfiguration.class);
	
	@Bean
	public void initSpringServiceConfiguration() {
		logger.info("Inside SpringServiceConfiguration initializer ...");
	}
	
}
