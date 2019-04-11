package com.excilys.cdb;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.services.ComputerServices;

public class Test {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		ComputerServices computerServices =app.getBean(ComputerServices.class);
		System.out.println(computerServices);
		
	}
}
