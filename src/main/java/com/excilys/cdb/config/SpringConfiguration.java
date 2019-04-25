package com.excilys.cdb.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
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
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@EnableWebMvc
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.excilys.cdb.repositories")
@ComponentScan(basePackages = "com.excilys.cdb")
@PropertySources({
	@PropertySource("classpath:dataSource.properties"),
	@PropertySource("classpath:hibernate.properties")
})
public class SpringConfiguration implements WebMvcConfigurer {

	Logger logger = Logger.getLogger(SpringConfiguration.class);
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
	
	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		return localeResolver;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry interceptorRegistry) {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		interceptorRegistry.addInterceptor(localeChangeInterceptor);
	}
	
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

}
