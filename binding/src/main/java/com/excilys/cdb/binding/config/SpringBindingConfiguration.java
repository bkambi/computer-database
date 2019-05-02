package com.excilys.cdb.binding.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.excilys.cdb.dto","com.excilys.cdb.validator"})
public class SpringBindingConfiguration {
	Logger logger = Logger.getLogger(SpringBindingConfiguration.class);
	@Bean
	public void initSpringBindingConfiguration() {
		logger.info("Inside SpringBindingConfiguration initializer ...");
	}
}
