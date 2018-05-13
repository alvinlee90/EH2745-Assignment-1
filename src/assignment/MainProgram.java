package assignment;

import java.util.ArrayList;

public class MainProgram {
	private static final String DATABASE = "cim"; 
	
	// Array to store the data from the XML files (both EQ and SSH)
	private static ArrayList<CimData> cimData = new ArrayList<CimData>(); 
	
	// Object to manage the SQL database
	private static Database database;
	
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
			
			networkTraversal.printBranches();
			// Display (print) all the tables
//			database.printTables(); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			// Drop the database and close connection
			database.dropDatabase(); 
		}
	}
	
	public static void parseXMLFiles(String eqFile, String sshFile) {
		// Parse EQ and SSH file
//		System.out.println("Parsing EQ file...");
		cimData.add(new CimData(eqFile));
		
//		System.out.println("Parsing SSH file...");
		cimData.add(new CimData(sshFile));
	}
	
	public static void initializeDatabase() {
		// Create database
		database = new Database(DATABASE); 
		
		// Create tables (1 for each class)
		for (String query : cimData.get(0).CreateTables()) {
//			System.out.println("[SQL] " + query); 
			database.createTable(query);
		}
		
		// Insert elements into the respective table
		insertTables(); 
		
		// Fill in values where base voltage ID is null
		database.updateBaseVoltageID();
	}
	
	public static void insertTables() {
		// -------------- Base Voltage -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertBaseVoltage()) {
//				System.out.println("[SQL] " + query); 
				database.insertTable(query);
			}
		}
		
		// -------------- Sub-station -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertSubstation()) {
//				System.out.println("[SQL] " + query); 
				database.insertTable(query);
			}
		}
		
		// -------------- Voltage Level -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertVoltageLevel()) {
//				System.out.println("[SQL] " + query); 
				database.insertTable(query);
			}
		}

		// -------------- Generating Unit --------------
		for (CimData object : cimData) {
			for (String query : object.insertGeneratingUnit()) {
//				System.out.println("[SQL] " + query); 
				database.insertTable(query);
			}
		}

		// -------------- Regulating Control -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertRegulatingControl()) {
//				System.out.println("[SQL] " + query); 
				database.insertTable(query);
			}
		}

		// -------------- Power Transformer -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertPowerTransformer()) {
//				System.out.println("[SQL] " + query); 
				database.insertTable(query);
			}
		}
		
		// -------------- Energy Consumer -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertEnergyConsumer()) {
//				System.out.println("[SQL] " + query); 
				database.insertTable(query);
			}
		}

		// -------------- Power Transformer End --------------
		for (CimData object : cimData) {
			for (String query : object.insertPowerTransformerEnd()) {
//				System.out.println("[SQL] " + query); 
				database.insertTable(query);
			}
		}

		// -------------- Breaker -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertBreaker()) {
//				System.out.println("[SQL] " + query); 
				database.insertTable(query);
			}
		}
		
		// -------------- Ratio Tap Changer -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertRatioTapChanger()) {
//				System.out.println("[SQL] " + query); 
				database.insertTable(query);
			}
		}

		// -------------- Bus-bar Section -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertBusbar()) {
//				System.out.println("[SQL] " + query); 
				database.insertTable(query);
			}
		}
		
		// -------------- Connectivity Node --------------
		for (CimData object : cimData) {
			for (String query : object.insertConnectNode()) {
//				System.out.println("[SQL] " + query); 
				database.insertTable(query);
			}
		}
		
		// -------------- Terminal -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertTerminal()) {
//				System.out.println("[SQL] " + query); 
				database.insertTable(query);
			}
		}
		
		// -------------- Line -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertLine()) {
//				System.out.println("[SQL] " + query); 
				database.insertTable(query);
			}
		}

		// -------------- Shunt -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertShunt()) {
//				System.out.println("[SQL] " + query); 
				database.insertTable(query);
			}
		}
		
		// -------------- Synchronous Machine -------------- 
		for (CimData object : cimData) {
			for (String query : object.insertSyncMachine()) {
//				System.out.println("[SQL] " + query); 
				database.insertTable(query);
			}
		}
	}
}