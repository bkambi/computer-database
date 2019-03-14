package com.excilys.cdb.controler;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.DAOImpl.CompanyDAOImpl;
import com.excilys.cdb.model.Company;

public class CompanyController {

	private String stringRetour;
	/**
	 * 
	 * @return
	 */
	public String showListCompany() {
		stringRetour = "";
		CompanyDAOImpl daoCompany = new CompanyDAOImpl();
		List<Company> listeCompany = new ArrayList<Company>();
		listeCompany = daoCompany.getList();
		for (Company c : listeCompany) {
			stringRetour += c.toString();
		}
		return stringRetour += "\n";
	}

}
