package com.excilys.cdb.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.DAO.ComputerDAO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class ComputerDAOImpl implements ComputerDAO{

	JDBCConnection jdbcConnection = new JDBCConnection() ;
	
	@Override
	public int creat(Computer c) {
		String query =" Insert into computer(name,introduced,discontinued,company_id) value(?,?,?,?);";
		Connection conn = jdbcConnection.open();
		int results =0;
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, c.getName());
			ps.setTimestamp(2, c.getIntroduced());
			ps.setTimestamp(3, c.getDiscontinued());
			ps.setLong(4, c.getCompany_id());
			results = ps.executeUpdate();
		}catch (SQLException e) {
			System.out.println("creat : catch SQL Exception");
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("creat : finally catch SQL Exception");
				e.printStackTrace();
			}
			
		}
		return results ;
		
	}

	@Override
	public Computer getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("finally")
	@Override
	public List<Computer> getList() {
		
		List<Computer> listReturn = new ArrayList<Computer>();
		String query =" Select * FROM computer";
		Connection conn = jdbcConnection.open();
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery(query);
			while(rs.next()) {
				Computer c = new Computer(rs.getLong("id"),rs.getString("name"),rs.getTimestamp("introduced"),rs.getTimestamp("discontinued"),rs.getLong("company_id"));
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
	public int update(Computer c) {
		String query ="UPDATE computer SET name = ? , introduced =?,discontinued=?,company_id=?  WHERE id = ?";
		Connection conn = jdbcConnection.open();
		int results =0;
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, c.getName());
			ps.setTimestamp(2, c.getIntroduced());
			ps.setTimestamp(3, c.getDiscontinued());
			ps.setLong(4, c.getCompany_id());
			ps.setLong(5, c.getId());
			results = ps.executeUpdate();
		}catch (SQLException e) {
			System.out.println("update : catch SQL Exception");
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("update : finally catch SQL Exception");
				e.printStackTrace();
			}
		}
		
		return results ;
	}

	@Override
	public int delete(Long id) {
		String query ="DELETE FROM computer WHERE id = ?";
		Connection conn = jdbcConnection.open();
		int results =0;
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setLong(1, id);
			results = ps.executeUpdate();
		}catch (SQLException e) {
			System.out.println("delete : catch SQL Exception");
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("delete : finally catch SQL Exception");
				e.printStackTrace();
			}
		}
		
		return results ;
	}

}
