package com.excilys.cdb.services;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.cdb.dao.dao.CompanyDAO;
import com.excilys.cdb.model.Company;

@Service
public class CompanyServices {
	
	@Autowired
	private CompanyDAO companyDAO;
	
	
	public Map<Long, String> handlerRequestForCompanies() {
	return companyDAO.getList().stream()
				.collect(Collectors.toMap(Company::getId, Company::getName));
	}

}
