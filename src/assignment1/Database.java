package assignment1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class Database {
	// JDBC driver name and database URL
	final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final static String DB_URL = "jdbc:mysql://localhost/";
	
	// Database credentials 
	private Properties properties = new Properties(); 
	final static String USER = "root";
	final static String PASS = "root"; 
	
	// Connection and statement 
	private Connection conn = null;
	private Statement stmt = null;

	// Database name
	private String databaseName; 
	
	public Database(String name) {
		try {			
			databaseName = name;

			// Register JDBC driver
			Class.forName(JDBC_DRIVER);
			
			// Properties for connection (credentials) 
			properties.setProperty("user", USER);
			properties.setProperty("password", PASS);
			properties.setProperty("useSSL", "false");		// Stops time-zone error from appearing
			
			// Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, properties);

			// Execute a query to create database
			System.out.println("Creating database...");
			
			// Create database students
			String sql = "CREATE DATABASE IF NOT EXISTS " + databaseName; 
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Database created successfully...");

			// Connect to the created database
			conn = DriverManager.getConnection(DB_URL + databaseName, properties);
			stmt = conn.createStatement();
		}
		catch (SQLException se) {
			// Handle errors for JDBC
			System.out.println("JDBC error occured while creating database");
			se.printStackTrace();
		}
		catch (Exception e) {
			// Handle errors for Class.forName
			System.out.println("Class.forName error occured while creating database");
			e.printStackTrace();
		}

	}
	
	public void DropDatabase() {
		try {
			// Drop database
			String sql = "DROP DATABASE " + databaseName; 
			stmt.executeUpdate(sql);
			System.out.println("Database dropped successfully...");

			// Close connection
			conn.close();
		}
		catch (SQLException se) {
			// Handle errors for JDBC
			System.out.println("JDBC error occured while dropping database");
			se.printStackTrace();
		}
	}
}
