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

import com.excilys.cdb.model.Computer;

@Repository
public class ComputerDAO {

	private Logger logger = Logger.getLogger(ComputerDAO.class.getName());

	private final static String INSERT_COMPUTER = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUE(?,?,?,?)";
	private final static String SELECT_A_COMPUTER = "SELECT id,name,introduced,discontinued,company_id FROM computer WHERE id = ?";
	private final static String SELECT_COMPUTERS = "SELECT id,name,introduced,discontinued,company_id FROM computer";
	private final static String UPDATE_COMPUTER = "UPDATE computer SET name = ? , introduced = ? , discontinued = ? , company_id = ?  WHERE id = ?";
	private final static String DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";
	private final static String DELETE_COMPUTERS_BY_COMPANY_ID = "DELETE FROM computer WHERE company_id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * This method add a computer on database
	 * 
	 * @param c The computer which we want to add in Database
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 */
	@Transactional
	public void creat(Computer computer) {
		try {
			jdbcTemplate.update(INSERT_COMPUTER, computer.getName(), computer.getIntroduced(),
					computer.getDiscontinued(), computer.getCompany_id());
			logger.info("Inside creat computer method ...");
		} catch (DataAccessException e) {
			logger.error("Data Access Exception : fail to creat computer");
		}

	}

	/**
	 * This method takes on data base Computer if the id in the parameter match with
	 * the primary key id on the database
	 * 
	 * @param id The Id of the Computer wanted
	 * @return Computer Comes from Database
	 */
	@Transactional
	public Optional<Computer> getById(Long id) {

		Computer computer = new Computer();
		try {
			computer = (Computer) jdbcTemplate.queryForObject(SELECT_A_COMPUTER, new Object[] { id },
					new BeanPropertyRowMapper<Computer>(Computer.class));
			logger.info("Inside get computer by id method ...");
		} catch (DataAccessException e) {
			logger.error("Data Access Exception : fail to get a computer");
		}

		return Optional.ofNullable(computer);

	}

	/**
	 * This method takes all computer on the database
	 * 
	 * @return List<Computer> The list of all Computer
	 */
	@Transactional
	public List<Computer> getList() {
		List<Computer> computers = new ArrayList<Computer>();
		try {
			computers = jdbcTemplate.query(SELECT_COMPUTERS, new BeanPropertyRowMapper<Computer>(Computer.class));
			logger.info("Inside get all Computer method ...");

		} catch (DataAccessException e) {
			logger.error("Data Access Exception : fail to get all Computer");
		}
		return computers;
	}

	/**
	 * This method update a computer on the database
	 * 
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 */
	@Transactional
	public void update(Computer computer) {
		try {
			jdbcTemplate.update(UPDATE_COMPUTER, computer.getName(), computer.getIntroduced(),
					computer.getDiscontinued(), computer.getCompany_id(),computer.getId());
			logger.info("Inside update computer method ...");
		} catch (DataAccessException e) {
			logger.error("Data Access Exception : fail to update computer");
		}
	}

	/**
	 * This method delete a computer
	 * 
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 */
	@Transactional
	public void delete(Long id) throws SQLException {

		try {
			jdbcTemplate.update(DELETE_COMPUTER, id);
			logger.info("Inside delete computer by computer id method ...");

		} catch (DataAccessException e) {
			logger.error("SQL Exception : fail to delete computer by computer id ");
		}

	}

	/**
	 * This method delete a computer 1 or multiple computer
	 * 
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 */
	@Transactional
	public void delete(List<Long> listeId) throws SQLException {

		try {
			for (Long id : listeId) {
				jdbcTemplate.update(DELETE_COMPUTER, id);
			}
			logger.info("Inside delete List of computers method...");

		} catch (DataAccessException e) {
			logger.error("SQL Exception : fail to delete List of computers");
		}

	}

	/**
	 * This method delete a computer
	 * 
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 */
	@Transactional
	public void deleteComputerByCompanyId(Long companyId) throws SQLException {

		try {
			jdbcTemplate.update(DELETE_COMPUTERS_BY_COMPANY_ID, companyId);
			logger.info("Inside delete computers by company id method ...");

		} catch (DataAccessException e) {
			logger.error("SQL Exception : fail to delete computers by id company id");
		}
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

}
