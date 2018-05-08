package assignment.cim;

import org.w3c.dom.Element;


public class SyncMachine extends BaseCIMClass{
	static final String NAME = "cim:IdentifiedObject.name";
	static final String RATE_S = "cim:RotatingMachine.ratedS"; 
	static final String MACHINE_P = "cim:RotatingMachine.p";
	static final String MACHINE_Q = "cim:RotatingMachine.q"; 
	static final String GEN_UNIT = "cim:RotatingMachine.GeneratingUnit"; 
	static final String REG_CONTROL = "cim:RegulatingCondEq.RegulatingControl"; 
	static final String EQUIP_CONTAINER = "cim:Equipment.EquipmentContainer"; 

	private String name;
	private String ratedS;
	private String machineP;
	private String machineQ;
	private String genUnit; 
	private String regControl;
	private String equipContainer;
	private String baseVoltage; 
	
	public SyncMachine(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		ratedS = parseElement(element, RATE_S); 
		machineP = parseElement(element, MACHINE_P); 
		machineQ = parseElement(element, MACHINE_Q); 
		genUnit = parseElement(element, GEN_UNIT); 
		regControl = parseElement(element, REG_CONTROL); 
		equipContainer = parseElement(element, EQUIP_CONTAINER); 
	}
	
	public String createTable() {
		return SYNC_MACHINE_ + " (RDFID VARCHAR(50) NOT NULL, NAME VARCHAR(50), S FLOAT, "
				+ "P FLOAT, Q FLOAT, GENERATING_UNIT_ID VARCHAR(50), REGULATING_CONTROL_ID "
				+ "VARCHAR(50), EQUIPMENT_CONTAINER_ID VARCHAR(50), BASE_VOLTAGE_ID VARCHAR(50), "
				+ "PRIMARY KEY(RDFID), FOREIGN KEY (GENERATING_UNIT_ID) REFERENCES " 
				+ GENERATING_UNIT_ + "(RDFID), FOREIGN KEY (REGULATING_CONTROL_ID) REFERENCES " 
				+ REG_CONTROL_ + "(RDFID), FOREIGN KEY (BASE_VOLTAGE_ID) REFERENCES " 
				+ BASE_VOLTAGE_ + "(RDFID))"; 
	}
	
	public String getName() { return name; }
	
	public String getRatedS() { return ratedS; } 
	
	public String getMachineP() { return machineP; } 
	
	public String getMachineQ() { return machineQ; } 
	
	public String getGenUnit() { return genUnit; } 
	
	public String getRegControl() { return regControl; } 

	public String getEquipContainer() { return equipContainer; } 
	
	public String getBaseVoltage() { return baseVoltage; } 

	public String getElement() { return SYNC_MACHINE_; }
	
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nRated S: " + ratedS +
				"\nP: " + machineP + "\nQ: " + machineQ + "\nGenerating Unit: " 
				+ genUnit + "\nRegulating Control: " + regControl + "\nEquipment Container: " 
				+ equipContainer + "\n");
	}
}
