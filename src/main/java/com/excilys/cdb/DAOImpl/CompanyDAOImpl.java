package com.excilys.cdb.DAOImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.DAO.CompanyDAO;
import com.excilys.cdb.model.Company;

public class CompanyDAOImpl implements CompanyDAO {

	JDBCConnection jdbcConnection = new JDBCConnection() ;
	
	@Override
	public int creat(Company c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Company getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("finally")
	@Override
	public List<Company> getList() {
		
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
			System.out.println("getList : SQL Exception");
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
		// TODO Auto-generated method stub
		return 0;
	}

}
