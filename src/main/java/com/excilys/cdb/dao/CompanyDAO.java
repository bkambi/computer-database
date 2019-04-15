package com.excilys.cdb.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Company;

@Repository
public class CompanyDAO {

	@Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ComputerDAO computerDAO ;
	 
	private Logger logger = Logger.getLogger(CompanyDAO.class.getName());
	
	private final static String INSERT_COMPANY = "INSERT INTO company(name) VALUE(?)";
	private final static String SELECT_A_COMPANY = "SELECT id,name FROM company WHERE id = ?";
	private final static String SELECT_COMPANIES = "SELECT id,name FROM company";
	private final static String DELETE_COMPANY = "DELETE FROM company WHERE id = ?";
	

	/**
	 * This method add a Company on database
	 * 
	 * @param c The Company which we want to add in Database
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 */
	public int creat(Company c) {
		int results = 0;
		 jdbcTemplate.update(INSERT_COMPANY,c.getName());
		return results;
	}

	/**
	 * This method takes on data base Company if the id in the parameter match with
	 * the primary key id on the database
	 * 
	 * @param id The Id of the Company wanted
	 * @return Company Comes from Database
	 */
	public Optional<Company> getById(Long id) {
		Company companyReturn= (Company) jdbcTemplate.queryForObject(SELECT_A_COMPANY,
	            new Object[] { id }, new BeanPropertyRowMapper(Company.class));
		return Optional.ofNullable(companyReturn);
	}

	/**
	 * This method takes all Company on the database
	 * 
	 * @return List<Company> The list of all Company
	 * @throws SQLException 
	 */
	public List<Company> getList() {
        List < Company > persons = jdbcTemplate.query(SELECT_COMPANIES,
        		new BeanPropertyRowMapper<Company>(Company.class));
        logger.info("getList();");
        return persons;
	}
	/**
	 * This method delete a Company
	 * 
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException 
	 */ 
	public int delete(Long id) throws SQLException {

		int results = 0;
		computerDAO.deleteComputerByCompanyId(id);
		jdbcTemplate.update(DELETE_COMPANY, id);
		return results;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

}
