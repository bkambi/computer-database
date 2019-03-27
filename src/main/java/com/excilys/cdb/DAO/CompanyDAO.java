package com.excilys.cdb.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.excilys.cdb.View;
import com.excilys.cdb.model.Company;

public class CompanyDAO {

	private Logger logger = Logger.getLogger(CompanyDAO.class.getName());

	private final static String INSERT_COMPANY = "INSERT INTO company(name) VALUE(?)";
	private final static String SELECT_A_COMPANY = "SELECT * FROM company WHERE id = ?";
	private final static String SELECT_COMPANIES = "SELECT * FROM company";
	private final static String DELETE_COMPANY = "DELETE FROM company WHERE id = ?";

	/**
	 * This method add a Company on database
	 * 
	 * @param c The Company which we want to add in Database
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 */
	public int creat(Company c) {

		JDBCConnection jdbcConnection = new JDBCConnection();
		Connection conn = jdbcConnection.open();
		int results = 0;

		try {
			PreparedStatement ps = conn.prepareStatement(INSERT_COMPANY);
			ps.setString(1, c.getName());
			results = ps.executeUpdate();
		} catch (SQLException e) {

			logger.error("creat : catch SQL Exception");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {

				logger.error("creat : catch SQL Exception");
				e.printStackTrace();
			}
		}

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

		Company companyReturn = null;
		JDBCConnection jdbcConnection = new JDBCConnection();

		try (Connection conn = jdbcConnection.open()) {

			PreparedStatement ps = conn.prepareStatement(SELECT_A_COMPANY);
			if (id != null) {
				ps.setLong(1, id);
				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					companyReturn = new Company(rs.getLong("id"), rs.getString("name"));
				}
			}
		} catch (SQLException e) {

			logger.error("getId : SQL Exception");
			e.printStackTrace();
		}
		return Optional.ofNullable(companyReturn);
	}

	/**
	 * This method takes all Company on the database
	 * 
	 * @return List<Company> The list of all Company
	 */
	@SuppressWarnings("finally")
	public List<Company> getList() {

		List<Company> listReturn = new ArrayList<Company>();
		JDBCConnection jdbcConnection = new JDBCConnection();
		Connection conn = jdbcConnection.open();

		try {
			PreparedStatement ps = conn.prepareStatement(SELECT_COMPANIES);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Company c = new Company(rs.getLong("id"), rs.getString("name"));
				listReturn.add(c);
			}
		} catch (SQLException e) {

			logger.error("getList : SQL Exception");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return listReturn;
		}
	}

	/**
	 * This method delete a Company
	 * 
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 */
	public int delete(Long id) {

		JDBCConnection jdbcConnection = new JDBCConnection();
		Connection conn = jdbcConnection.open();
		int results = 0;

		try {
			PreparedStatement ps = conn.prepareStatement(DELETE_COMPANY);
			ps.setLong(1, id);
			results = ps.executeUpdate();
		} catch (SQLException e) {

			logger.error("delete : catch SQL Exception");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("delete : finally catch SQL Exception");
				e.printStackTrace();
			}
		}

		return results;
	}

}
