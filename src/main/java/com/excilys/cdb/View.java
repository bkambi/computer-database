package com.excilys.cdb;

import com.excilys.cdb.DAOImpl.CompanyDAOImpl;
import com.excilys.cdb.DAOImpl.ComputerDAOImpl;

public class View {

	public static void main(String[] args) {
		CompanyDAOImpl daoCompany = new CompanyDAOImpl();
		System.out.println(daoCompany.getList());
		
		
		ComputerDAOImpl daoComputer = new ComputerDAOImpl();
		System.out.println(daoComputer.getList());

	}

}
