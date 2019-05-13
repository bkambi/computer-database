package com.excilys.cdb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.excilys.cdb.dao.repositories.CompanyRepository;
import com.excilys.cdb.model.Company;

@Service
public class CompanyRestServices {

	@Autowired
	CompanyRepository companyRepository;

	public ResponseEntity<List<Company>> findAllCompanies() {
		List<Company> listComputers = companyRepository.findAll();
		return new ResponseEntity<List<Company>>(listComputers, HttpStatus.OK);
	}

}
