package com.excilys.cdb;

import com.excilys.cdb.DAOImpl.CompanyDAOImpl;

public class View {

	public static void main(String[] args) {
		CompanyDAOImpl dao = new CompanyDAOImpl();
		System.out.println(dao.getList());

	}

}
