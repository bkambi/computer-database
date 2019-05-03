package com.excilys.cdb.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.Company;

public interface CompanyRepository extends Repository<Company, Long> {

	@Transactional
	public void save(Company company);

	@Transactional(readOnly = true)
	public Optional<Company> findById(Long id);
	
	@Transactional(readOnly = true)
	public List<Company> findAll();

	@Transactional(readOnly = true)
	public Long count();
	
	@Transactional
	public void deleteById(Long id);
	
}
