package com.excilys.cdb.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
//@PropertySource("classpath:hibernate.properties")
//@EnableTransactionManagement
//@EnableJpaRepositories("com.excilys.cdb.repositories")
public class HibernateConfiguration {
//	Logger logger = Logger.getLogger(HibernateConfiguration.class);
//	@Autowired
//	private Environment env;
//	@Bean
//	   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//		logger.info("Inside LocalContainerEntityManagerFactoryBean initializer ...");
//	      LocalContainerEntityManagerFactoryBean em 
//	        = new LocalContainerEntityManagerFactoryBean();
//	      em.setDataSource(dataSource());
//	      em.setPackagesToScan(new String[] { "com.excilys.cdb.model" });
//	 
//	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//	      em.setJpaVendorAdapter(vendorAdapter);
//	      em.setJpaProperties(additionalProperties());
//	      
//	     return em;
//	   }
//	
//	@Bean
//	public DataSource dataSource(){
//		logger.info("Inside DataSource initializer ...");
//	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
//	    dataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.className"));
//	    dataSource.setUrl(env.getRequiredProperty("spring.datasource.url"));
//	    dataSource.setUsername(env.getRequiredProperty("spring.datasource.username") );
//	    dataSource.setPassword(env.getRequiredProperty("spring.datasource.password"));
//	    return dataSource;
//	}
//	
//	@Bean
//	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
//		logger.info("Inside PlatformTransactionManager initializer ...");
//	    JpaTransactionManager transactionManager = new JpaTransactionManager();
//	    transactionManager.setEntityManagerFactory(emf);
//	 
//	    return transactionManager;
//	}
//	 
//	@Bean
//	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
//		logger.info("Inside PersistenceExceptionTranslationPostProcessor initializer ...");
//	    return new PersistenceExceptionTranslationPostProcessor();
//	}
//	 
//	Properties additionalProperties() {
//	    Properties properties = new Properties();
//	    properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
//	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//	        
//	    return properties;
//	}
//	

}
