package com.excilys.cdb.dao.repositories;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.sun.istack.logging.Logger;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@EnableJpaRepositories
@PropertySource("classpath:application.properties")
public class SpringPersistenceConfiguration {

	Logger logger = Logger.getLogger(SpringPersistenceConfiguration.class);
	
	@Bean
	public void initSpringPersistenceConfiguration() {
		logger.info("Inside SpringPersistenceConfiguration initializer ...");
	}
	@Autowired
	private Environment env;

	@Bean
	public JdbcTemplate jdbcTemplate() {
		logger.info("Inside jdbcTemplate initializer ...");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty("dataSource.className"));
		dataSource.setUrl(env.getRequiredProperty("dataSource.url"));
		dataSource.setUsername(env.getRequiredProperty("dataSource.user"));
		dataSource.setPassword(env.getRequiredProperty("dataSource.password"));
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		return jdbcTemplate;
	}
	
	
	@Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		logger.info("Inside LocalContainerEntityManagerFactoryBean initializer ...");
	      LocalContainerEntityManagerFactoryBean em 
	        = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(dataSource());
	      em.setPackagesToScan(new String[] { "com.excilys.cdb.model" });
	 
	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      em.setJpaProperties(additionalProperties());
	      
	     return em;
	   }
	@Bean
	public HikariDataSource dataSource() {
		logger.info("Inside DataSource HikariConfig initializer ..."); 
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(env.getRequiredProperty("spring.datasource.className"));
		config.setJdbcUrl(env.getRequiredProperty("spring.datasource.url"));
		config.setUsername(env.getRequiredProperty("spring.datasource.username"));
		config.setPassword(env.getRequiredProperty("spring.datasource.password"));
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		HikariDataSource dataSource = new HikariDataSource( config );
		return dataSource ; 
	}
		
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		logger.info("Inside PlatformTransactionManager initializer ...");
	    JpaTransactionManager transactionManager = new JpaTransactionManager();
	    transactionManager.setEntityManagerFactory(emf);
	 
	    return transactionManager;
	}
	 
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		logger.info("Inside PersistenceExceptionTranslationPostProcessor initializer ...");
	    return new PersistenceExceptionTranslationPostProcessor();
	}
	 
	Properties additionalProperties() {
	    Properties properties = new Properties();
	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	    return properties;
	}
	
}
