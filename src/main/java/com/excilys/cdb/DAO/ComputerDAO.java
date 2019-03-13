package com.excilys.cdb.DAO;

import java.util.List;

import com.excilys.cdb.model.Computer;

public interface ComputerDAO {
	/**
	 * This method add a computer on database
	 * 
	 * @param c The computer which we want to add in Database
	 * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 */
	public int creat(Computer c); 
	/**
	 * This method takes on data base Computer if the id in the parameter 
	 * match with the primary key id on the database
	 * 
	 * @param id The Id of the Computer wanted
	 * @return Computer Comes from Database
	 */
	public Computer getById(Long id);
	/**
	 * This method takes all computer on the database
	 * 
	 * @return List<Computer> The list of all Computer
	 */
	public List<Computer> getList();
	/**
	 * This method update a computer on the database
	 * 
	 * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 */
	public int update(Computer c);
	/**
	 * This method delete a computer 
	 * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 */
	public int delete(Long id);

}
