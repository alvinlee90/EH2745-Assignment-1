package assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import assignment.cim.*; 

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

	// Database name
	private String database; 
	
	public Database(String name) {
		Statement stmt = null;

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
			
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
			// Connect to the created database
			conn = DriverManager.getConnection(DB_URL + database, credentials);
		}
		catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		}
		catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		finally {
			// Close statement
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// Function to create all the tables
	public void createTable(String query) {
		Statement stmt = null;

		try {
			// Prepare and execute statement;			
			String sql = "CREATE TABLE IF NOT EXISTS " + query; 
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
//			System.out.println("Created table in given database successfully...\n");
		}
		catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		}
		finally {
			// Close statement
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// Function to insert tables 
	public void insertTable(String sql) {
		Statement stmt = null; 
		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
//			System.out.println("Insert given table successfully...\n");
		}
		catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		}
		finally {
			// Close statement
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// Function to update all the base voltage relations in the database
	public void updateBaseVoltageID() {		
		String[] elements = {SYNC_MACHINE_, ENERGY_CONSUMER_, BREAKER_};
		
		Statement stmt = null;
		Statement stmt2 = null; 
		
		try {
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();
			
			for (String table : elements) {
				ArrayList<String> updateArray = new ArrayList<String>(); 
				
				// Get all the rows where base voltage id is null
				String query = "SELECT " + RDF_ID_ + ", " + EQUIP_CONTAINER_ID_ 
								+ " FROM " + table + " WHERE " + BASE_VOLTAGE_ID_
								+ " IS NULL"; 
				
		        ResultSet result = stmt.executeQuery(query);
		        
		        // Loop through all found rows
		        while (result.next()) {
		        	// Get the equipment container ID 
		        	String rdfResult = result.getString(RDF_ID_); 
		        	String equipResult = result.getString(EQUIP_CONTAINER_ID_); 
		        	
		        	// Find corresponding voltage level with the same equipment container ID
		        	String query2 = "SELECT " + BASE_VOLTAGE_ID_ + " FROM " + VOLTAGE_LEVEL_
		        					+ " WHERE " + RDF_ID_ + " = '" + equipResult + "'"; 
				    		        	
			        ResultSet voltageResult = stmt2.executeQuery(query2);
					
			        // Check if result is found
			        if (voltageResult.next()) {
			        	// Get base voltage id from corresponding voltage level
			        	String baseResult = voltageResult.getString(BASE_VOLTAGE_ID_);
			        	
			        	// Update (null) base voltage id with found one
			        	String update = "UPDATE " + table + " SET " + BASE_VOLTAGE_ID_ 
			        					+ " = '" + baseResult + "' WHERE " 
			        					+ RDF_ID_ + " = '" + rdfResult + "'";
			        	
			        	updateArray.add(update); 
			        }
		        }
	        
	        	for (String update : updateArray) {
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
		finally {
			// Close statements
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt2 != null) {
				try {
					stmt2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// Function to return all the RDF ids from a specific table
	public ArrayList<String> getID(String table) {
		Statement stmt = null; 
				
		ArrayList<String> result = new ArrayList<String>(); 
				
		try {
			// Prepare SQL query 
			String sql = "SELECT * FROM " + table; 
						
			// Prepare statement
			stmt = conn.createStatement(); 
									
			// Execute statement
			ResultSet rs = stmt.executeQuery(sql); 
			
			
			// Store result into array list
			while (rs.next()) {
				result.add(rs.getString(RDF_ID_)); 
			}
		}
		catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			// Close statements
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	// Function to execute the SQL select query
	public ArrayList<String> selectSQL(String table, String whereAttribute, String condition, String getAttribute) {
		PreparedStatement stmt = null; 
		
		ArrayList<String> results = new ArrayList<String>(); 
				
		try {
			// Prepare SQL query 
			String sql = "SELECT * FROM " + table + " WHERE " + whereAttribute + " = ?"; 
									
			// Prepare statement
			stmt = conn.prepareStatement(sql); 
			stmt.setString(1, condition);
			
			// Execute statement
			ResultSet rs = stmt.executeQuery(); 
			
			// Store result into array list
			while (rs.next()) {				
				results.add(rs.getString(getAttribute)); 
			}
		}
		catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			// Close statements
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return results;
	}
	
	// Function to execute the SQL select query
	public ArrayList<String> selectSQL(String table, String whereAttribute, String condition) {
		return this.selectSQL(table, whereAttribute, condition, RDF_ID_); 
	}
	
	// Function to print all the tables
	public void printTables() {	    
	    Statement stmt = null; 
	    
		try {
			stmt = conn.createStatement();
			
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
					case LINE_: 
						attributes = new Line().getAttributes();
						break;	
					case SHUNT_:
						attributes = new Shunt().getAttributes();
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
		finally {
			// Close statements
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// Function to drop the database and disconnect
	public void dropDatabase() {
		Statement stmt = null;
		
		try {
			// Drop database
			stmt = conn.createStatement();
			stmt.executeUpdate("DROP DATABASE " + database);
			System.out.println("\nDatabase dropped successfully...");
		}
		catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		}
		finally {
			// Close statement
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// Close connection
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
