package com.excilys.cdb;

import java.sql.SQLException;

import com.excilys.cdb.DAO.CompanyDAO;

public class DeleteCli {
	
	static CompanyDAO daoCompany ;
	public static void main(String[] args) {
		daoCompany =new CompanyDAO();
		try {
			System.out.println("Nombre délément supprimer : "+ daoCompany.delete(1L));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
