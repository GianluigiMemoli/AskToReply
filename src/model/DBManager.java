package model;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

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
			String query = String.format("{CALL %s(%s)}", procedureName, parametersPlaceholder.substring(0, parametersPlaceholder.length() - 1)); 			
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
	

	public void executePreparedQuery(CallableStatement statement) {
		
	}
	

	
}