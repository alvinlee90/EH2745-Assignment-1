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
			
			// Drop the database and close connection
			database.DropDatabase(); 
		}
		catch (Exception e) {
			e.printStackTrace();
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
		for (String command : eqData.createTables()) {
			System.out.println("[SQL] " + command); 
			database.CreateTable(command);
		}
		
		// Insert elements to the table
		InsertTables(); 
	}
	
	public static void InsertTables() {
		// -------------- Base Voltage -------------- 
		for (String command : eqData.insertBaseVoltage()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}
		
		for (String command : sshData.insertBaseVoltage()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}

		// -------------- Sub-station -------------- 
		for (String command : eqData.insertSubstation()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}
		
		for (String command : sshData.insertSubstation()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}
		
		// -------------- Voltage Level -------------- 
		for (String command : eqData.insertVoltageLevel()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}
		
		for (String command : sshData.insertVoltageLevel()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}

		// -------------- Generating Unit -------------- 
		for (String command : eqData.insertGeneratingUnit()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}
		
		for (String command : sshData.insertGeneratingUnit()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}

		// -------------- Regulating Control -------------- 
		for (String command : eqData.insertRegulatingControl()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}
		
		for (String command : sshData.insertRegulatingControl()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}

		// -------------- Power Transformer -------------- 
		for (String command : eqData.insertPowerTransformer()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}
		
		for (String command : sshData.insertPowerTransformer()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}
		
		// -------------- Energy Consumer -------------- 
		for (String command : eqData.insertEnergyConsumer()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}
		
		for (String command : sshData.insertEnergyConsumer()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}

		// -------------- Power Transformer End -------------- 
		for (String command : eqData.insertPowerTransformerEnd()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}
		
		for (String command : sshData.insertPowerTransformerEnd()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}

		// -------------- Breaker -------------- 
		for (String command : eqData.insertBreaker()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}
		
		for (String command : sshData.insertBreaker()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}
		
		// -------------- Ratio Tap Changer -------------- 
		for (String command : eqData.insertRatioTapChanger()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}
		
		for (String command : sshData.insertRatioTapChanger()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}

		// -------------- Synchronous Machine -------------- 
		for (String command : eqData.insertSyncMachine()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}
		
		for (String command : sshData.insertSyncMachine()) {
			System.out.println("[SQL] " + command); 
			database.InsertTable(command);
		}
	}
}