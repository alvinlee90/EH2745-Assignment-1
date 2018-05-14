package assignment;

import java.util.ArrayList;

public class MainProgram {
	private static final String DATABASE = "cim"; 
	
	// Array to store the data from the XML files (both EQ and SSH)
	private static ArrayList<CimData> cimData = new ArrayList<CimData>(); 
	
	// Object to manage the SQL database
	private static Database database;
	
	// Function to parse EQ and SHH files; stores all the data into a CimData object
	// in preparation for the database
	public static void parseXMLFiles(String eqFile, String sshFile) {
		// Parse EQ and SSH file
		System.out.println("Parsing EQ file...");
		cimData.add(new CimData(eqFile));
		
		System.out.println("Parsing SSH file...");
		cimData.add(new CimData(sshFile));
	}
	
	// Function to initialize the database; creates all the tables required
	// and populates the tables with the data from the EQ and SHH file. Finally,
	// it populates the missing relations for the [base voltage id]
	public static void initializeDatabase() {
		// Create database
		database = new Database(DATABASE); 
		
		// Create tables (1 for each class)
		for (String query : cimData.get(0).CreateTables()) {
			database.createTable(query);
		}
		
		// Insert elements into the respective table
		insertTables(); 
		
		// Fill in values where base voltage ID is null
		database.updateBaseVoltageID();
	}
	
	// Function to call all the SQL queries to populate the database with 
	// information from the EQ and SSH files
	public static void insertTables() {
		// -------------- Base Voltage -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertBaseVoltage()) {
				database.insertTable(query);
			}
		}
		
		// -------------- Sub-station -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertSubstation()) {
				database.insertTable(query);
			}
		}
		
		// -------------- Voltage Level -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertVoltageLevel()) {
				database.insertTable(query);
			}
		}

		// -------------- Generating Unit --------------
		for (CimData object : cimData) {
			for (String query : object.insertGeneratingUnit()) {
				database.insertTable(query);
			}
		}

		// -------------- Regulating Control -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertRegulatingControl()) {
				database.insertTable(query);
			}
		}

		// -------------- Power Transformer -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertPowerTransformer()) {
				database.insertTable(query);
			}
		}
		
		// -------------- Energy Consumer -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertEnergyConsumer()) {
				database.insertTable(query);
			}
		}

		// -------------- Power Transformer End --------------
		for (CimData object : cimData) {
			for (String query : object.insertPowerTransformerEnd()) {
				database.insertTable(query);
			}
		}

		// -------------- Breaker -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertBreaker()) {
				database.insertTable(query);
			}
		}
		
		// -------------- Ratio Tap Changer -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertRatioTapChanger()) {
				database.insertTable(query);
			}
		}

		// -------------- Bus-bar Section -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertBusbar()) {
				database.insertTable(query);
			}
		}
		
		// -------------- Connectivity Node --------------
		for (CimData object : cimData) {
			for (String query : object.insertConnectNode()) {
				database.insertTable(query);
			}
		}
		
		// -------------- Terminal -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertTerminal()) {
				database.insertTable(query);
			}
		}
		
		// -------------- Line -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertLine()) {
				database.insertTable(query);
			}
		}

		// -------------- Shunt -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertShunt()) {
				database.insertTable(query);
			}
		}
		
		// -------------- Synchronous Machine -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertSyncMachine()) {
				database.insertTable(query);
			}
		}
	}
	
	public static void main (String[] args) {
		// Check the number of arguments 
		// args[0] = EQ file; args[1] = SSH file
		if (args.length != 2) {
			System.err.println("[Main] Error: invalid number of EQ and SSH filepaths");
			return;
		}
		
		try {
			// Parse data
			parseXMLFiles(args[0], args[1]);
			
			// Initialize database (create database, create tables, insert elements)
			initializeDatabase(); 			
			
			NetworkTraversal networkTraversal = new NetworkTraversal(database); 
			
//			networkTraversal.printBranches();
//			database.printTables(); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			// Drop the database and close connection
//			database.dropDatabase(); 
		}
	}
}