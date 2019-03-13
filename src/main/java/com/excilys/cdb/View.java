package com.excilys.cdb;

import java.sql.Timestamp;

import com.excilys.cdb.DAOImpl.CompanyDAOImpl;
import com.excilys.cdb.DAOImpl.ComputerDAOImpl;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class View {

	public static void main(String[] args) {
		CompanyDAOImpl daoCompany = new CompanyDAOImpl();
		//System.out.println(daoCompany.getList());
		//daoCompany.creat(new Company("Excilys"));
		//daoCompany.delete(44L);
		
		ComputerDAOImpl daoComputer = new ComputerDAOImpl();
		//System.out.println(daoComputer.getList());
		//daoComputer.creat(new Computer("Excilys-Computer-G7",new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),44L));
		//daoComputer.delete(575L);
	}

}
