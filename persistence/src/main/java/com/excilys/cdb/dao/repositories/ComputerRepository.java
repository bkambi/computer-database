package com.excilys.cdb.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.Computer;

public interface ComputerRepository extends Repository<Computer, Long>{
	

	@Transactional
	public void save(Computer computer);
	
	@Transactional(readOnly = true)
	public Optional<Computer> findById(Long id);

	@Transactional(readOnly = true)
	public List<Computer> findAll();

	@Transactional(readOnly = true)
	public Long count();

	@Transactional
	public void deleteById(Long id);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Computer c WHERE c.id = :id")
	public void deleteComputers(List<Long> id);
	


}
