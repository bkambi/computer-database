package com.excilys.cdb;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.excilys.cdb.config.SpringConfiguration;
import com.excilys.cdb.dao.CompanyDAO;

public class DeleteCli {
	
	static CompanyDAO daoCompany ;
	public static void main(String[] args) {
		 
		
		try {
			daoCompany =new CompanyDAO();
			System.out.println("Nombre délément supprimer : "+ daoCompany.delete(1L));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
