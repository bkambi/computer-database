//package com.excilys.cdb.web.config;
//
//import org.apache.log4j.Logger;
//import org.springframework.context.MessageSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.support.ReloadableResourceBundleMessageSource;
//import org.springframework.web.servlet.LocaleResolver;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.i18n.CookieLocaleResolver;
//import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.springframework.web.servlet.view.JstlView;
//
//@EnableWebMvc
//@Configuration
//@ComponentScan(basePackages = {"com.excilys.cdb.controller","com.excilys.cdb.web.config"})
//public class SpringWebConfiguration implements WebMvcConfigurer {
//
//	Logger logger = Logger.getLogger(SpringWebConfiguration.class);
//
//	@Bean
//	public InternalResourceViewResolver resolver() {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setViewClass(JstlView.class);
//		resolver.setPrefix("/WEB-INF/views/");
//		resolver.setSuffix(".jsp");
//		return resolver;
//	}
//
//	@Bean
//	public MessageSource messageSource() {
//		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//		messageSource.setBasename("classpath:messages");
//		messageSource.setDefaultEncoding("UTF-8");
//		messageSource.setUseCodeAsDefaultMessage(true);
//		return messageSource;
//	}
//	
//	@Bean
//	public LocaleResolver localeResolver() {
//		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
//		return localeResolver;
//	}
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry interceptorRegistry) {
//		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//		localeChangeInterceptor.setParamName("lang");
//		interceptorRegistry.addInterceptor(localeChangeInterceptor);
//	}
//	
//	
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
//	}
//
//}
