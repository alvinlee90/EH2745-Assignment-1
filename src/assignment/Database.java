package assignment;

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
	private Properties credentials = new Properties(); 
	final static String USER = "root";
	final static String PASS = "root"; 
	
	// Connection and statement 
	private Connection conn = null;
	private Statement stmt = null;

	// Database name
	private String database; 
	
	public Database(String name) {
		try {			
			database = name;

			// Register JDBC driver
			Class.forName(JDBC_DRIVER);
			
			// Properties for connection (credentials) 
			credentials.setProperty("user", USER);
			credentials.setProperty("password", PASS);
			credentials.setProperty("useSSL", "false");		// Stops time-zone error from appearing
			
			// Open a connection
			System.out.println("Connecting to database...\n");
			conn = DriverManager.getConnection(DB_URL, credentials);

			// Execute a query to create database
			System.out.println("Creating database...");
			
			// Create database 
			String sql = "CREATE DATABASE IF NOT EXISTS " + database; 
			System.out.println("[SQL] " + sql);
			
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
			System.out.println("Database created successfully...\n");

			// Connect to the created database
			conn = DriverManager.getConnection(DB_URL + database, credentials);
			stmt = conn.createStatement();
		}
		catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		}
		catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}
	
	public void CreateTable(String command) {
		try {
			String sql = "CREATE TABLE IF NOT EXISTS " + command; 
			stmt.executeUpdate(sql);
			
			System.out.println("Created table in given database successfully...\n");
		}
		catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void DropDatabase() {
		try {
			// Drop database
			String sql = "DROP DATABASE " + database; 
			stmt.executeUpdate(sql);
			System.out.println("Database dropped successfully...");

			// Close connection
			conn.close();
		}
		catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
