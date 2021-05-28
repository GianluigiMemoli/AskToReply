package model;

import java.io.BufferedReader;
<<<<<<< HEAD
=======
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
>>>>>>> 9c4faeadf73f0d24e1f6b090e5af6a4f0837f114
import java.io.FileReader;
import java.io.IOException;
import java.lang.String;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;
<<<<<<< HEAD

=======
>>>>>>> 9c4faeadf73f0d24e1f6b090e5af6a4f0837f114
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.CallableStatement;

public class DBManager {
	private String CONNECTION_STRING = "jdbc:mysql://localhost:3306/mysql?serverTimezone=UTC";  
	private String DRIVER_NAME = "com.mysql.cj.jdbc.Driver"; 
	private String USERNAME = "root"; 
	private String PASSWORD = "root"; 
	
	private DataSource connectionPool;
	private static DBManager dbManager; 
	
	private Logger log = Logger.getLogger(DBManager.class.getName());
	
	private DBManager() {
		PoolProperties poolProps = new PoolProperties();
		poolProps.setUrl(CONNECTION_STRING);
		poolProps.setDriverClassName(DRIVER_NAME);
		poolProps.setUsername(USERNAME);
		poolProps.setPassword(PASSWORD);				
		this.connectionPool = new DataSource();
		this.connectionPool.setPoolProperties(poolProps);				
	}
	
	public static DBManager getInstance() {
		if (dbManager == null) {
			dbManager = new DBManager();
		}
		return dbManager;
	}
	
	private Connection getSQLConnection() throws SQLException  {
		Connection conn = this.connectionPool.getConnection();
		conn.setCatalog("asktoreply");
		return conn;
	}
	
	public CallableStatement prepareStoredProcedureCall(String procedureName, int parametersAmount) throws SQLException {
		Connection connection = null;
		CallableStatement preparedProcedure = null;
		try{
			connection = this.getSQLConnection();
		
			String parametersPlaceholder = "?,".repeat(parametersAmount);			
			int lastCommaIndex = 0; 
			if (parametersAmount > 0) {
				lastCommaIndex = parametersPlaceholder.length() - 1;
			}
			String query = String.format("{CALL %s(%s)}", procedureName, parametersPlaceholder.substring(0, lastCommaIndex)); 			
			preparedProcedure = connection.prepareCall(query); 
		}
		catch(SQLException exc) {
			exc.printStackTrace();
		}
		finally {
			if(connection != null) {
				connection.close();
			} 
		}
		return preparedProcedure;		
	}
	
	public PreparedStatement createPreparedStatement(String query) throws SQLException {
		Connection conn = this.getSQLConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		conn.close();
		return stmt;
	}  	
	
	public void executeFromScript(String filePath) throws IOException, SQLException {
<<<<<<< HEAD
        ScriptRunner sr = new ScriptRunner(this.getSQLConnection());
        java.io.Reader reader = new BufferedReader(new FileReader(filePath));
        sr.runScript(reader);
    }
=======
		
		Connection conn = getSQLConnection();
		ScriptRunner sr = new ScriptRunner(conn);
		sr.runScript(new BufferedReader(new FileReader(filePath)));
		conn.close();
	}
>>>>>>> 9c4faeadf73f0d24e1f6b090e5af6a4f0837f114
	
}
