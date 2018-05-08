package assignment;

public class MainProgram {
	private static final String DATABASE = "assignment"; 
	
	public static void main (String[] args) {
		if (args.length != 2) {
			System.err.println("[Main] Error: invalid number of EQ and SSH filepaths");
			return;
		}
		
		try {
			// Parse EQ and SSH file
			System.out.println("Parsing EQ file...");
			CIMData eqData = new CIMData(args[0]);
			
			System.out.println("Parsing SSH file...");
			CIMData sshData = new CIMData(args[1]);
			
			// Create database
			Database database = new Database(DATABASE); 
			
			// Create tables 
			for (String command : eqData.createTables()) {
				System.out.println("[SQL] " + command); 
				database.CreateTable(command);
			}
			
			// Insert tables
			for (String command : eqData.insertAllTables()) {
				System.out.println("[SQL] " + command); 
			}
			
			// Insert tables
			for (String command : sshData.insertAllTables()) {
				System.out.println("[SQL] " + command); 
			}

			
			database.DropDatabase(); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
