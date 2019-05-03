package com.excilys.cdb.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.excilys.cdb.config.SpringBindingConfiguration;
import com.excilys.cdb.services.SpringServiceConfiguration;

@Configuration
@ComponentScan(basePackageClasses = {SpringServiceConfiguration.class,
		SpringBindingConfiguration.class},basePackages ="com.excilys.cdb.dao.dao")
public class ControllerConfig {

}
