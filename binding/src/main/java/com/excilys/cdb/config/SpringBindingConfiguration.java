package com.excilys.cdb.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.excilys.cdb.dao.repositories.SpringPersistenceConfiguration;
import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.mapper.ComputerMapper;

@Configuration
@ComponentScan(basePackageClasses = {SpringPersistenceConfiguration.class,ComputerMapper.class,ComputerDTO.class})
public class SpringBindingConfiguration {

}