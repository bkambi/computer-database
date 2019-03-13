package com.excilys.cdb.DAO;

import java.util.List;

import com.excilys.cdb.model.Company;

public interface CompanyDAO {
	/**
	 * This method add a Company on database
	 * 
	 * @param c The Company which we want to add in Database
	 * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 */
	public int creat(Company c); 
	/**
	 * This method takes on data base Company if the id in the parameter 
	 * match with the primary key id on the database
	 * 
	 * @param id The Id of the Company wanted
	 * @return 
	 */
	public Company getById(Long id);
	/**
	 * This method takes all Company on the database
	 * 
	 * @return List<Company> The list of all Company
	 */
	public List<Company> getList();
	/**
	 * This method update a Company on the database
	 * 
	 * @return
	 */
	public int update(Company c);
	/**
	 * This method delete a Company 
	 * @return 
	 */
	public int delete(Long id);

}
