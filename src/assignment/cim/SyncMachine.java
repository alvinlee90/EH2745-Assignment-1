package assignment.cim;

import org.w3c.dom.Element;


public class SyncMachine extends BaseCimClass{	
	private static final String NAME_ = "NAME";
	private static final String S_ = "S"; 
	private static final String P_ = "P";
	private static final String Q_ = "Q"; 
	private static final String GEN_UNIT_ID_ = "GENERATING_UNIT_ID"; 
	private static final String REG_CONTROL_ID_ = "REGULATING_CONTROL_ID"; 
	private static final String EQUIP_CONTAINER_ID_ = "EQUIPMENT_CONTAINER_ID"; 
	private static final String BASE_VOLTAGE_ID_ = "BASE_VOLTAGE_ID"; 

	private static final String NAME = "cim:IdentifiedObject.name";
	private static final String RATE_S = "cim:RotatingMachine.ratedS"; 
	private static final String MACHINE_P = "cim:RotatingMachine.p";
	private static final String MACHINE_Q = "cim:RotatingMachine.q"; 
	private static final String GEN_UNIT = "cim:RotatingMachine.GeneratingUnit"; 
	private static final String REG_CONTROL = "cim:RegulatingCondEq.RegulatingControl"; 
	private static final String EQUIP_CONTAINER = "cim:Equipment.EquipmentContainer"; 

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
		return SYNC_MACHINE_ + " (" + RDF_ID_ + " VARCHAR(50) NOT NULL, " + NAME_ + " VARCHAR(50), "
				+ S_ + " FLOAT, " + P_ + " FLOAT, " + Q_ + " FLOAT, " + GEN_UNIT_ID_ + " VARCHAR(50), "
				+ REG_CONTROL_ID_ + " VARCHAR(50), " + EQUIP_CONTAINER_ID_ + " VARCHAR(50), " 
				+ BASE_VOLTAGE_ID_ + " VARCHAR(50), PRIMARY KEY(" + RDF_ID_ + "), FOREIGN KEY ("
				+ GEN_UNIT_ID_ + ") REFERENCES " + GENERATING_UNIT_ + "(" + RDF_ID_ + "), FOREIGN KEY ("
				+ REG_CONTROL_ID_ + ") REFERENCES " + REG_CONTROL_ + "(" + RDF_ID_ + "), FOREIGN KEY (" 
				+ BASE_VOLTAGE_ID_ + ") REFERENCES " + BASE_VOLTAGE_ + "(" + RDF_ID_ + "))"; 
	}
	
	public String insertTable() {
		String columnNames = " (";
		String values = "VALUES ("; 
		
		String duplicate = " ON DUPLICATE KEY UPDATE "; 
		Boolean update = false; 
		
		// Add rdf_id 
		columnNames = columnNames.concat(RDF_ID_); 
		values = values.concat("'" + rdfID + "'");
		
		// Add name
		if (name != null) {
			columnNames = columnNames.concat(", " + NAME_);
			values = values.concat(", '" + name + "'");
			
			duplicate = duplicate.concat(NAME_ + " = VALUES(" + NAME_ + "), ");  
			update = true; 
		}
				
		// Add rated S
		if (ratedS != null) {
			columnNames = columnNames.concat(", " + S_);
			values = values.concat(", '" + ratedS + "'");
			
			duplicate = duplicate.concat(S_ + " = VALUES(" + S_ + "), ");  
			update = true; 
		}
		
		// Add P
		if (machineP != null) {
			columnNames = columnNames.concat(", " + P_);
			values = values.concat(", '" + machineP + "'");
			
			duplicate = duplicate.concat(P_ + " = VALUES(" + P_ + "), ");  
			update = true; 
		}
		
		// Add Q
		if (machineQ != null) {
			columnNames = columnNames.concat(", " + Q_);
			values = values.concat(", '" + machineQ + "'");
			
			duplicate = duplicate.concat(Q_ + " = VALUES(" + Q_ + "), ");  
			update = true; 
		}
		
		// Add generating unit ID
		if (genUnit != null) {
			columnNames = columnNames.concat(", " + GEN_UNIT_ID_);
			values = values.concat(", '" + genUnit + "'");
			
			duplicate = duplicate.concat(GEN_UNIT_ID_ + " = VALUES(" 
										 + GEN_UNIT_ID_ + "), ");  
			update = true; 
		}
		
		// Add regulating control ID
		if (regControl != null) {
			columnNames = columnNames.concat(", " + REG_CONTROL_ID_);
			values = values.concat(", '" + regControl + "'");
			
			duplicate = duplicate.concat(REG_CONTROL_ID_ + " = VALUES(" 
										 + REG_CONTROL_ID_ + "), ");  
			update = true; 
		}

		// Add equipment container ID
		if (equipContainer != null) {
			columnNames = columnNames.concat(", " + EQUIP_CONTAINER_ID_);
			values = values.concat(", '" + equipContainer + "'");
			
			duplicate = duplicate.concat(EQUIP_CONTAINER_ID_ + " = VALUES(" 
										 + EQUIP_CONTAINER_ID_ + "), ");  
			update = true; 
		}
		
		// Add base voltage ID
		if (baseVoltage != null) {
			columnNames = columnNames.concat(", " + BASE_VOLTAGE_ID_);
			values = values.concat(", '" + baseVoltage + "'");
			
			duplicate = duplicate.concat(BASE_VOLTAGE_ID_ + " = VALUES(" 
										 + BASE_VOLTAGE_ID_ + "), ");  
			update = true; 
		}

		// Return SQL command (check possibility for duplicates already in table)
		String command = SYNC_MACHINE_ + columnNames + ") " + values + ")"; 
		
		return insertSQL(command, duplicate, update); 
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
