package assignment;

public class MainProgram {
	private static final String DATABASE = "cim"; 
	
	private static CimData eqData;
	private static CimData sshData;
	
	private static Database database;
	
	public static void main (String[] args) {
		if (args.length != 2) {
			System.err.println("[Main] Error: invalid number of EQ and SSH filepaths");
			return;
		}
		
		try {
			// Parse data
			parseXMLFiles(args[0], args[1]);
			
			// Initialize database (create database, create tables, insert elements)
			initializeDatabase(); 			
			
			// Display (print) all the tables
			database.viewTables(); 
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
		System.out.println("Parsing EQ file...");
		eqData = new CimData(eqFile);
		
		System.out.println("Parsing SSH file...");
		sshData = new CimData(sshFile);
	}
	
	public static void initializeDatabase() {
		// Create database
		database = new Database(DATABASE); 
		
		// Create tables 
		for (String query : eqData.CreateTables()) {
			System.out.println("[SQL] " + query); 
			database.createTable(query);
		}
		
		// Insert elements to the table
		insertTables(); 
		
		// Fill in values where base voltage ID is null
		database.updateBaseVoltageID();
	}
	
	public static void insertTables() {
		// -------------- Base Voltage -------------- 
		for (String query : eqData.insertBaseVoltage()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}
		
		for (String query : sshData.insertBaseVoltage()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}

		// -------------- Sub-station -------------- 
		for (String query : eqData.insertSubstation()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}
		
		for (String query : sshData.insertSubstation()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}
		
		// -------------- Voltage Level -------------- 
		for (String query : eqData.insertVoltageLevel()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}
		
		for (String query : sshData.insertVoltageLevel()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}

		// -------------- Generating Unit -------------- 
		for (String query : eqData.insertGeneratingUnit()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}
		
		for (String query : sshData.insertGeneratingUnit()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}

		// -------------- Regulating Control -------------- 
		for (String query : eqData.insertRegulatingControl()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}
		
		for (String query : sshData.insertRegulatingControl()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}

		// -------------- Power Transformer -------------- 
		for (String query : eqData.insertPowerTransformer()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}
		
		for (String query : sshData.insertPowerTransformer()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}
		
		// -------------- Energy Consumer -------------- 
		for (String query : eqData.insertEnergyConsumer()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}
		
		for (String query : sshData.insertEnergyConsumer()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}

		// -------------- Power Transformer End -------------- 
		for (String query : eqData.insertPowerTransformerEnd()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}
		
		for (String query : sshData.insertPowerTransformerEnd()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}

		// -------------- Breaker -------------- 
		for (String query : eqData.insertBreaker()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}
		
		for (String query : sshData.insertBreaker()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}
		
		// -------------- Ratio Tap Changer -------------- 
		for (String query : eqData.insertRatioTapChanger()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}
		
		for (String query : sshData.insertRatioTapChanger()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}

		// -------------- Busbar Section -------------- 
		for (String query : eqData.insertBusbar()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}
		
		for (String query : sshData.insertBusbar()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}
		
		// -------------- Connectivity Node -------------- 
		for (String query : eqData.insertConnectNode()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}
		
		for (String query : sshData.insertConnectNode()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}
		
		// -------------- Terminal -------------- 
		for (String query : eqData.insertTerminal()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}
		
		for (String query : sshData.insertTerminal()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}

		// -------------- Synchronous Machine -------------- 
		for (String query : eqData.insertSyncMachine()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}
		
		for (String query : sshData.insertSyncMachine()) {
			System.out.println("[SQL] " + query); 
			database.insertTable(query);
		}
	}
}