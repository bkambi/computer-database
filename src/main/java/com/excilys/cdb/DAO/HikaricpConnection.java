package com.excilys.cdb.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.excilys.cdb.HikariCPTest;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikaricpConnection {
	
	private Logger logger = Logger.getLogger(HikaricpConnection.class.getName());
	
    private static HikariConfig config = new HikariConfig();
    
    private static DataSource dataSource ;
    
    private final static HikaricpConnection hikaricpConnection = new HikaricpConnection();
    
    /** Constructeur privé */
    private HikaricpConnection() {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.error("Class Not Found Exception");
			e.printStackTrace();
		}
    	config.setJdbcUrl("jdbc:mysql://localhost:3306/computer-database-db");
		config.setUsername("admincdb");
		config.setPassword("qwerty1234");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		dataSource = new HikariDataSource( config );
    }
    
    public  static HikaricpConnection getInstance() {
    	return hikaricpConnection;
    }
 
    public Connection open() throws SQLException {
        return dataSource.getConnection();
    }
}