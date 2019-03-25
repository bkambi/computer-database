package com.excilys.cdb.DAOImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.excilys.cdb.View;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.LogConfigurator;

public class CompanyDAOImpl {

	private  Logger logger = Logger.getLogger(CompanyDAOImpl.class.getName()) ;
	
	public int creat(Company c) {
		
		//logger = LogConfigurator.configureLoggerIfNull(logger,CompanyDAOImpl.class.getName());
		String query =" Insert into company(name) value(?)";
		JDBCConnection jdbcConnection = new JDBCConnection();
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

	
	public Optional<Company> getById(Long id) {
		//logger = LogConfigurator.configureLoggerIfNull(logger,CompanyDAOImpl.class.getName());
		Company companyReturn = null;
		String query =" Select * FROM company where id =?;";
		JDBCConnection jdbcConnection = new JDBCConnection();
		
		try(Connection conn = jdbcConnection.open()) {	
			
			PreparedStatement ps = conn.prepareStatement(query);
			if (id != null) {
				ps.setLong(1, id);
				ResultSet rs = ps.executeQuery();
				//System.out.println(rs);
				if(rs.next()) {
					companyReturn = new Company(rs.getLong("id"),rs.getString("name"));
				}
			}
		} catch (SQLException e) {
			//System.out.println("getList : SQL Exception");
			logger.error("getId : SQL Exception");
			e.printStackTrace();
		}
		return Optional.ofNullable(companyReturn) ;
	}

	@SuppressWarnings("finally")
	public List<Company> getList() {
		//logger = LogConfigurator.configureLoggerIfNull(logger,CompanyDAOImpl.class.getName());
		List<Company> listReturn = new ArrayList<Company>();
		String query =" Select * FROM company";
		JDBCConnection jdbcConnection = new JDBCConnection();
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

	
	public int update(Company c) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int delete(Long id) {
		//logger = LogConfigurator.configureLoggerIfNull(logger,CompanyDAOImpl.class.getName());
		//TODO CASCADE DELETE ATFER (IT DOESN'T WORK)
		String query ="DELETE FROM company WHERE id = ?";
		JDBCConnection jdbcConnection = new JDBCConnection();
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
