package assignment;

public class MainProgram {
	private static final String DATABASE = "assignment"; 
	
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
			ParseXMLFiles(args[0], args[1]);
			
			// Initialize database (create database, create tables, insert elements)
			InitializeDatabase(); 			
			
			// Display (print) all the tables
			database.ViewTables(); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			// Drop the database and close connection
			database.DropDatabase(); 
		}
	}
	
	public static void ParseXMLFiles(String eqFile, String sshFile) {
		// Parse EQ and SSH file
		System.out.println("Parsing EQ file...");
		eqData = new CimData(eqFile);
		
		System.out.println("Parsing SSH file...");
		sshData = new CimData(sshFile);
	}
	
	public static void InitializeDatabase() {
		// Create database
		database = new Database(DATABASE); 
		
		// Create tables 
		for (String query : eqData.createTables()) {
			System.out.println("[SQL] " + query); 
			database.CreateTable(query);
		}
		
		// Insert elements to the table
		InsertTables(); 
		
		// Fill in values where base voltage ID is null
		database.UpdateBaseVoltageID();
	}
	
	public static void InsertTables() {
		// -------------- Base Voltage -------------- 
		for (String query : eqData.insertBaseVoltage()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}
		
		for (String query : sshData.insertBaseVoltage()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}

		// -------------- Sub-station -------------- 
		for (String query : eqData.insertSubstation()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}
		
		for (String query : sshData.insertSubstation()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}
		
		// -------------- Voltage Level -------------- 
		for (String query : eqData.insertVoltageLevel()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}
		
		for (String query : sshData.insertVoltageLevel()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}

		// -------------- Generating Unit -------------- 
		for (String query : eqData.insertGeneratingUnit()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}
		
		for (String query : sshData.insertGeneratingUnit()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}

		// -------------- Regulating Control -------------- 
		for (String query : eqData.insertRegulatingControl()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}
		
		for (String query : sshData.insertRegulatingControl()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}

		// -------------- Power Transformer -------------- 
		for (String query : eqData.insertPowerTransformer()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}
		
		for (String query : sshData.insertPowerTransformer()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}
		
		// -------------- Energy Consumer -------------- 
		for (String query : eqData.insertEnergyConsumer()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}
		
		for (String query : sshData.insertEnergyConsumer()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}

		// -------------- Power Transformer End -------------- 
		for (String query : eqData.insertPowerTransformerEnd()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}
		
		for (String query : sshData.insertPowerTransformerEnd()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}

		// -------------- Breaker -------------- 
		for (String query : eqData.insertBreaker()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}
		
		for (String query : sshData.insertBreaker()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}
		
		// -------------- Ratio Tap Changer -------------- 
		for (String query : eqData.insertRatioTapChanger()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}
		
		for (String query : sshData.insertRatioTapChanger()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}

		// -------------- Synchronous Machine -------------- 
		for (String query : eqData.insertSyncMachine()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}
		
		for (String query : sshData.insertSyncMachine()) {
			System.out.println("[SQL] " + query); 
			database.InsertTable(query);
		}
	}
}