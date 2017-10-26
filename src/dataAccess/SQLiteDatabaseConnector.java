package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabaseConnector implements IDatabaseConnector {
	
	private String url;
	private String user;
	private String password;
	
	private Connection connection = null;
	
		
	public Connection getConnection() {
		return connection;
	}
	
	public SQLiteDatabaseConnector(String url, String user, String password)
	{
		this.url 	  = url;
		this.user 	  = user;
		this.password = password;
	}
	
	/**
	 * Create a connection to the given database and creates one if it
	 * 
	 */
	public void connect() throws SQLException {
        try {
            String connectionString = "jdbc:sqlite:" + url;
            
            // create a connection to the database
            connection = DriverManager.getConnection(connectionString);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            throw new SQLException();
        } 
    }	
	
		
	@Override
	public void close() {
		if(connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
