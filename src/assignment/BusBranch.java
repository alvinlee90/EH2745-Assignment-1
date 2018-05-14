package assignment;

public class BusBranch extends CimConsts {
	private String fromID;
	private String fromName;
	private String fromType; 
	
	private String toID; 
	private String toName; 
	private String toType; 
	
	private String deviceID; 
	private String deviceName; 
	private String deviceType; 

	
	public BusBranch() {}
	
	public BusBranch(String fID, String tID) {
		fromID = fID; 
		toID = tID; 
	}
	
	public BusBranch(String fID, String fType, String tID, String tType) {
		fromID = fID; 
		toID = tID; 
	
		fromType = fType; 
		toType = tType;
	}

	public BusBranch(String fID, String fType, String fName, String tID, String tType, String tName) {
		fromID = fID; 
		toID = tID; 
	
		fromType = fType; 
		toType = tType;
		
		fromName = fName; 
		toName = tName; 
		
		if (fType == SHUNT_) {
			deviceID = fID;
			deviceType = fType;
			deviceName = fName;
		}
	}
	
	public void updateDevice(String id, String name, String type) {
		deviceID = id;
		deviceName = name;
		deviceType = type; 
	}
	
	static public void printTitle() {
		System.out.println("Device\t\tType\t\tFrom\t\tTo");
		System.out.println("---------------------------------------------------------");
	}
	
	public void print() {
		System.out.println(deviceName + "\t\t" + deviceType + "\t\t" + fromName + "\t\t" + toName);
	}
}
