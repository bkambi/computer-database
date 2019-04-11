package com.excilys.cdb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.excilys.cdb.View;
import com.excilys.cdb.exception.DeleteDataException;
import com.excilys.cdb.model.Computer;
@Component
public class ComputerDAO {

	private Logger logger = Logger.getLogger(ComputerDAO.class.getName());

	private final static String INSERT_COMPUTER = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUE(?,?,?,?)";
	private final static String SELECT_A_COMPUTER = "SELECT id,name,introduced,discontinued,company_id FROM computer WHERE id = ?";
	private final static String SELECT_COMPUTERS = "SELECT id,name,introduced,discontinued,company_id FROM computer";
	private final static String UPDATE_COMPUTER = "UPDATE computer SET name = ? , introduced =?,discontinued=?,company_id=?  WHERE id = ?";
	private final static String DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";
	private final static String DELETE_COMPUTERS_BY_COMPANY_ID = "DELETE FROM computer WHERE company_id=?";

//	@Autowired
//	private JdbcTemplate jdbcTemplate;
		

	/**
	 * This method add a computer on database
	 * 
	 * @param c The computer which we want to add in Database
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 */
	public int creat(Computer c) {
		int results = 0;
		try (Connection conn = HikaricpConnection.getInstance().open()) {
			PreparedStatement ps = conn.prepareStatement(INSERT_COMPUTER);
			ps.setString(1, c.getName());
			ps.setTimestamp(2, c.getIntroduced());
			ps.setTimestamp(3, c.getDiscontinued());
			ps.setLong(4, c.getCompany_id());
			results = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("creat : catch SQL Exception");
			e.printStackTrace();
		}
		return results;

	}

	/**
	 * This method takes on data base Computer if the id in the parameter match with
	 * the primary key id on the database
	 * 
	 * @param id The Id of the Computer wanted
	 * @return Computer Comes from Database
	 */
	public Computer getById(Long id) {

		try (Connection conn = HikaricpConnection.getInstance().open()) {
			PreparedStatement ps = conn.prepareStatement(SELECT_A_COMPUTER);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			Computer c = new Computer();
			if (rs.next()) {
				c.setId(rs.getLong("id"));
				c.setName(rs.getString("name"));
				c.setIntroduced(rs.getTimestamp("introduced"));
				c.setDiscontinued(rs.getTimestamp("discontinued"));
				c.setCompany_id(rs.getLong("company_id"));
			}
			return c;
		} catch (SQLException e) {
			// System.out.println("getById : SQL Exception");
			logger.error("getById : SQL Exception");
			e.printStackTrace();
			return new Computer();
		}

	}

	/**
	 * This method takes all computer on the database
	 * 
	 * @return List<Computer> The list of all Computer
	 */
	public List<Computer> getList() {

		List<Computer> listReturn = new ArrayList<Computer>();
		try (Connection conn = HikaricpConnection.getInstance().open()) {
			PreparedStatement ps = conn.prepareStatement(SELECT_COMPUTERS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Computer c = new Computer(rs.getLong("id"), rs.getString("name"), rs.getTimestamp("introduced"),
						rs.getTimestamp("discontinued"), rs.getLong("company_id"));
				listReturn.add(c);
			}
		} catch (SQLException e) {

			logger.error("getList : SQL Exception");
			e.printStackTrace();
		}
		return listReturn;
	}

	/**
	 * This method update a computer on the database
	 * 
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 */
	public int update(Computer c) {
		int results = 0;

		try (Connection conn = HikaricpConnection.getInstance().open()) {
			PreparedStatement ps = conn.prepareStatement(UPDATE_COMPUTER);
			ps.setString(1, c.getName());
			ps.setTimestamp(2, c.getIntroduced());
			ps.setTimestamp(3, c.getDiscontinued());
			ps.setLong(4, c.getCompany_id());
			ps.setLong(5, c.getId());
			results = ps.executeUpdate();
		} catch (SQLException e) {

			logger.error("update : catch SQL Exception");
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * This method delete a computer
	 * 
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 */
	public int delete(Long id) throws SQLException {

		int results = 0;
		Connection conn = HikaricpConnection.getInstance().open();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(DELETE_COMPUTER);
			ps.setLong(1, id);
			results = ps.executeUpdate();
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				logger.error("delete : catch SQL Exception");
			}
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return results;
	}
	

	/**
	 * This method delete a computer 1 or multiple computer
	 * 
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 */
	public int delete(List<Long> listeId) throws SQLException {

		int results = 0;
		Connection conn = HikaricpConnection.getInstance().open();
		try {
			conn.setAutoCommit(false);
			for (Long id : listeId) {
				PreparedStatement ps = conn.prepareStatement(DELETE_COMPUTER);
				ps.setLong(1, id);
				results += ps.executeUpdate();
			}

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				logger.error("delete : catch SQL Exception");
			}
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				logger.error("delete : catch SQL Exception");
				e.printStackTrace();
			}
		}
		return results;
	}
	/**
	 * This method delete a computer
	 * 
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 */
	public int deleteComputerByCompanyId(Long companyId) throws SQLException {

		int results = 0;
		Connection conn = HikaricpConnection.getInstance().open();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(DELETE_COMPUTERS_BY_COMPANY_ID);
			ps.setLong(1, companyId);
			results = ps.executeUpdate();
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				logger.error("delete : catch SQL Exception");
			}
			e.printStackTrace();
			
		} finally {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return results;
	}
	
}
