package assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import assignment.cim.BaseCimClass;
import assignment.cim.BaseVoltage;
import assignment.cim.Breaker;
import assignment.cim.EnergyConsumer;
import assignment.cim.GeneratingUnit;
import assignment.cim.PowerTransformer;
import assignment.cim.PowerTransformerEnd;
import assignment.cim.RatioTapChanger;
import assignment.cim.RegulatingControl;
import assignment.cim.Substation;
import assignment.cim.SyncMachine;
import assignment.cim.VoltageLevel; 

public class Database extends BaseCimClass {
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
	
	public void InsertTable(String sql) {
		try {
			stmt.executeUpdate(sql);
			
			System.out.println("Insert given table successfully...\n");
		}
		catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void PopulateBaseVoltageID() {
		
	}
	
	public void viewTable(String table) {	    
	    try {
			ArrayList<String> attributes = new ArrayList<String>(); 

			switch (table) {
				case BASE_VOLTAGE_: 
					attributes = new BaseVoltage().getAttributes();
					break;
				case SUBSTATION_: 
					attributes = new Substation().getAttributes();
					break;
				case VOLTAGE_LEVEL_: 
					attributes = new VoltageLevel().getAttributes();
				  	break;
				case GENERATING_UNIT_: 
					attributes = new GeneratingUnit().getAttributes();
					break;
				case SYNC_MACHINE_: 
					attributes = new SyncMachine().getAttributes();
					break;
				case REG_CONTROL_: 
					attributes = new RegulatingControl().getAttributes();
					break;
				case POWER_TRANS_: 
					attributes = new PowerTransformer().getAttributes();
					break;
				case ENERGY_CONSUMER_: 
					attributes = new EnergyConsumer().getAttributes();
					break;
				case POWER_TRANS_END_: 
					attributes = new PowerTransformerEnd().getAttributes();
					break;
				case BREAKER_: 
					attributes = new Breaker().getAttributes();
					break;
				case RATIO_TAP_: 
					attributes = new RatioTapChanger().getAttributes();
					break;
				default:
					System.err.println("Error: Incorrect CIM object");	    	
			}
	    	
			String command = "SELECT "; 
			String title = "\n";
			
			for (String entry : attributes) {
				command = command.concat(entry + ", "); 
				title = title.concat(entry + "\t"); 
            }
			
			if (command.endsWith(", ")) {
				command = command.substring(0, command.length() - 2);
			}

		    command = command.concat(" FROM " + table);            
		    System.out.println("[SQL] " + command);
		    System.out.println(title);

	        ResultSet result = stmt.executeQuery(command);
	        	        	        
	        while (result.next()) {
	        	String data = ""; 
	        	
				for (String entry : attributes) {
					data = data.concat(result.getString(entry) + "\t"); 
				}
				
				System.out.println(data);
	        }
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
			if (conn != null) {
				conn.close();
			}
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
