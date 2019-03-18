package com.excilys.cdb.DAOImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.excilys.cdb.View;
import com.excilys.cdb.DAO.CompanyDAO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.LogConfigurator;

public class CompanyDAOImpl implements CompanyDAO {

	private  Logger logger ;
	JDBCConnection jdbcConnection = new JDBCConnection() ;
	
	@Override
	public int creat(Company c) {
		logger = LogConfigurator.configureLoggerIfNull(logger,CompanyDAOImpl.class.getName());
		String query =" Insert into company(name) value(?)";
		Connection conn = jdbcConnection.open();
		int results =0;
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, c.getName());
			results = ps.executeUpdate();
		}catch (SQLException e) {
			//System.out.println("creat : catch SQL Exception");
			logger.error("creat : catch SQL Exception");
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				//System.out.println("creat : finally catch SQL Exception");
				logger.error("creat : catch SQL Exception");
				e.printStackTrace();
			}
		}
		
		return results ;
	}

	@Override
	public Company getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("finally")
	@Override
	public List<Company> getList() {
		logger = LogConfigurator.configureLoggerIfNull(logger,CompanyDAOImpl.class.getName());
		List<Company> listReturn = new ArrayList<Company>();
		String query =" Select * FROM company";
		Connection conn = jdbcConnection.open();
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery(query);
			while(rs.next()) {
				Company c = new Company(rs.getLong("id"),rs.getString("name"));
				listReturn.add(c);
			}
		} catch (SQLException e) {
			//System.out.println("getList : SQL Exception");
			logger.error("getList : SQL Exception");
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return listReturn ;
		}
	}

	@Override
	public int update(Company c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		logger = LogConfigurator.configureLoggerIfNull(logger,CompanyDAOImpl.class.getName());
		//TODO CASCADE DELETE ATFER (IT DOESN'T WORK)
		String query ="DELETE FROM company WHERE id = ?";
		Connection conn = jdbcConnection.open();
		int results =0;
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setLong(1, id);
			results = ps.executeUpdate();
		}catch (SQLException e) {
			//System.out.println("delete : catch SQL Exception");
			logger.error("delete : catch SQL Exception");
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				//System.out.println("delete : finally catch SQL Exception");
				logger.error("delete : finally catch SQL Exception");
				e.printStackTrace();
			}
		}
		
		return results ;
	}
	
}
