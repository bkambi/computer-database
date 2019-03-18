package com.excilys.cdb.model;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.excilys.cdb.View;

public class LogConfigurator {

	private final static String CONFIGURATION = "src/main/ressources/log4j/log4j.properties";
	
	public static void addProperties() {
		PropertyConfigurator.configure(CONFIGURATION);
	}
	
	public static Logger configureLoggerIfNull(Logger logger , String className) {
		if( logger != null) {
			LogConfigurator.addProperties();
			logger = Logger.getLogger(className);
			
		}
		return logger;
	}
	
}
