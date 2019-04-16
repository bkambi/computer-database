package com.excilys.cdb.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.Company;

@Repository
public class CompanyDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ComputerDAO computerDAO;

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
	@Transactional
	public void creat(Company company) {
		try {
			jdbcTemplate.update(INSERT_COMPANY, company.getName());
			logger.info("Inside creat company method ...");
		} catch (DataAccessException e) {
			logger.error("Data Access Exception : fail to creat company");
		}

	}

	/**
	 * This method takes on data base Company if the id in the parameter match with
	 * the primary key id on the database
	 * 
	 * @param id The Id of the Company wanted
	 * @return Company Comes from Database
	 */
	@Transactional(readOnly = true)
	public Optional<Company> getById(Long id) {
		Company companyReturn = new Company();
		try {
			companyReturn = (Company) jdbcTemplate.queryForObject(SELECT_A_COMPANY, new Object[] { id },
					new BeanPropertyRowMapper<Company>(Company.class));
			logger.info("Inside get company by id method ...");
		} catch (DataAccessException e) {
			logger.error("Data Access Exception : fail to get a company");
		}
		
		return Optional.ofNullable(companyReturn);
	}

	/**
	 * This method takes all Company on the database
	 * 
	 * @return List<Company> The list of all Company
	 * @throws SQLException
	 */
	@Transactional(readOnly = true)
	public List<Company> getList() {
		List<Company> companies = new ArrayList<Company>();
		try {
			companies = jdbcTemplate.query(SELECT_COMPANIES, new BeanPropertyRowMapper<Company>(Company.class));
			logger.info("Inside get all company method ...");

		} catch (DataAccessException e) {
			logger.error("Data Access Exception : fail to get all company");
		}
		return companies;
	}

	/**
	 * This method delete a Company
	 * 
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 */
	@Transactional
	public void delete(Long id) throws SQLException {

		try {
			computerDAO.deleteComputerByCompanyId(id);
			jdbcTemplate.update(DELETE_COMPANY, id);
			logger.info("Inside delete company method ...");

		} catch (DataAccessException e) {
			logger.error("Data Access Exception : fail to delete company");
		} catch (SQLException e) {
			logger.error("SQL Exception : fail to delete computers");
		}
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

}
