package assignment;

public class MainProgram {
	public static void main (String[] args) {
		if (args.length != 2) {
			System.err.println("[Main] Error: invalid number of EQ and SSH filepaths");
			return;
		}
		
		try {
			System.out.println("Parsing EQ file...");
			CIMData eqData = new CIMData(args[0]);
			
			System.out.println("Parsing SSH file...");
			CIMData sshData = new CIMData(args[1]);
			
			Database database = new Database("assignment"); 
			
			database.DropDatabase(); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
