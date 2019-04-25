package com.excilys.cdb.repositories;

import java.util.List;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.Company;


public interface CompanyRepository extends Repository<Company,Long> {

	@Transactional(readOnly = true)
	public List<Company>findAll();
	
}
