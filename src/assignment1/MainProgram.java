package assignment1;

public class MainProgram {
	public static void main (String[] args) {
		try {
			Database database = new Database("assignment"); 
			database.DropDatabase(); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
