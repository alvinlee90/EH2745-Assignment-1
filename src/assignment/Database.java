package assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import assignment.cim.BaseVoltage;
import assignment.cim.Breaker;
import assignment.cim.BusbarSection;
import assignment.cim.ConnectivityNode;
import assignment.cim.EnergyConsumer;
import assignment.cim.GeneratingUnit;
import assignment.cim.PowerTransformer;
import assignment.cim.PowerTransformerEnd;
import assignment.cim.RatioTapChanger;
import assignment.cim.RegulatingControl;
import assignment.cim.Substation;
import assignment.cim.SyncMachine;
import assignment.cim.Terminal;
import assignment.cim.VoltageLevel; 

public class Database extends CimConsts {
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
	
	public void createTable(String query) {
		try {
			String sql = "CREATE TABLE IF NOT EXISTS " + query; 
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
	
	public void insertTable(String sql) {
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
	
	public void updateBaseVoltageID() {
		String rdfID = "RDFID"; 
		String equipContainerID = "EQUIPMENT_CONTAINER_ID"; 
		String baseVoltageID = "BASE_VOLTAGE_ID"; 
		
		String[] elements = {SYNC_MACHINE_, ENERGY_CONSUMER_, BREAKER_};
		
		try {
			for (String table : elements) {
				ArrayList<String> updateArray = new ArrayList<String>(); 
				
				// Get all the rows where base voltage id is null
				String query = "SELECT " + rdfID + ", " + equipContainerID 
								+ " FROM " + table + " WHERE " + baseVoltageID
								+ " IS NULL"; 
				
			    System.out.println("\n[SQL] " + query);
		        ResultSet result = stmt.executeQuery(query);
		        
		        // Loop through all found rows
		        while (result.next()) {
		        	// Get the equipment container ID 
		        	String rdfResult = result.getString(rdfID); 
		        	String equipResult = result.getString(equipContainerID); 
		        	
		        	// Find corresponding voltage level with the same equipment container ID
		        	String query2 = "SELECT " + baseVoltageID + " FROM " + VOLTAGE_LEVEL_
		        					+ " WHERE " + rdfID + " = '" + equipResult + "'"; 
				    
		        	System.out.println("\n[SQL] " + query2);
		        	Statement stmt2 = conn.createStatement();
			        ResultSet voltageResult = stmt2.executeQuery(query2);
					
			        // Check if result is found
			        if (voltageResult.next()) {
			        	// Get base voltage id from corresponding voltage level
			        	String baseResult = voltageResult.getString(baseVoltageID);
			        	
			        	// Update (null) base voltage id with found one
			        	String update = "UPDATE " + table + " SET " + baseVoltageID 
			        					+ " = '" + baseResult + "' WHERE " 
			        					+ rdfID + " = '" + rdfResult + "'";
			        	
			        	updateArray.add(update); 
			        }
		        }
	        
	        	for (String update : updateArray) {
			        System.out.println("\n[SQL] " + update);
			        stmt.executeUpdate(update);
	        	}	        
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
	
	public void viewTables() {	    
	    try {
	    	for (String table : CIM_CLASSES_) {
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
					case BUSBAR_: 
						attributes = new BusbarSection().getAttributes();
						break;	
					case CONNECT_NODE_: 
						attributes = new ConnectivityNode().getAttributes();
						break;	
					case TERMINAL_: 
						attributes = new Terminal().getAttributes();
						break;	
					default:
						System.err.println("Error: Incorrect CIM object");	    	
				}
		    	
				String query = "SELECT "; 
				String title = "\n" + table + "\n";
				
				for (String entry : attributes) {
					query = query.concat(entry + ", "); 
					title = title.concat(entry + "\t"); 
	            }
				
				if (query.endsWith(", ")) {
					query = query.substring(0, query.length() - 2);
				}
	
			    query = query.concat(" FROM " + table);            
//			    System.out.println("[SQL] " + query);
			    System.out.println(title);
	
		        ResultSet result = stmt.executeQuery(query);
		        	        	        
		        while (result.next()) {
		        	String data = ""; 
		        	
					for (String entry : attributes) {
						data = data.concat(result.getString(entry) + "\t"); 
					}
					
					System.out.println(data);
		        }
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
	
	public void dropDatabase() {
		try {
			// Drop database
			String sql = "DROP DATABASE " + database; 
			stmt.executeUpdate(sql);
			System.out.println("\nDatabase dropped successfully...");

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
