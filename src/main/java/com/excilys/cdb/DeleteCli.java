package com.excilys.cdb;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.excilys.cdb.DAO.CompanyDAO;

public class DeleteCli {
	
	
	static JdbcTemplate jdbcTemplate ; 
	static CompanyDAO daoCompany ;
	public static void main(String[] args) {
		 
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		jdbcTemplate = (JdbcTemplate) context.getBean(JdbcTemplate.class);
		System.out.println(jdbcTemplate.getDataSource().toString());
//		daoCompany =new CompanyDAO();
//		try {
//			System.out.println("Nombre délément supprimer : "+ daoCompany.delete(1L));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
}
