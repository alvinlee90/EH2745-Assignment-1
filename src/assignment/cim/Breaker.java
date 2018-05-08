package assignment.cim;

import org.w3c.dom.Element;


public class Breaker extends BaseCimClass{
	private static final String NAME_ = "NAME";
	private static final String STATE_ = "STATE";
	private static final String EQUIP_CONTAINER_ID_ = "EQUIPMENT_CONTAINER_ID"; 
	private static final String BASE_VOLTAGE_ID_ = "BASE_VOLTAGE_ID"; 

	private static final String NAME = "cim:IdentifiedObject.name";
	private static final String STATE = "cim:Switch.open";
	private static final String EQUIP_CONTAINER = "cim:Equipment.EquipmentContainer"; 

	private String name;
	private String state;
	private String equipContainer;
	private String baseVoltage; 
	
	public Breaker(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		state = parseElement(element, STATE); 
		equipContainer = parseElement(element, EQUIP_CONTAINER); 
	}
	
	public String createTable() {
		return BREAKER_ + " (" + RDF_ID_ + " VARCHAR(50) NOT NULL, " + NAME_ + " VARCHAR(50), "
				+ STATE_ + " BOOL, " + EQUIP_CONTAINER_ID_ + " VARCHAR(50), " + BASE_VOLTAGE_ID_ 
				+ " VARCHAR(50), PRIMARY KEY(" + RDF_ID_ + "), FOREIGN KEY (" + BASE_VOLTAGE_ID_ 
				+ ") REFERENCES " + BASE_VOLTAGE_ + "(" + RDF_ID_ + "))"; 
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
				
		// Add state
		if (state != null) {
			String boolState; 
			
			if (state == "true") {
				boolState = "1"; 
			}
			else {
				boolState = "0";
			}
			
			columnNames = columnNames.concat(", " + STATE_);
			values = values.concat(", '" + boolState + "'");
			
			duplicate = duplicate.concat(STATE_ + " = VALUES(" + STATE_ + "), ");  
			update = true; 
		}
		
		// Add equipment container ID
		if (equipContainer != null) {
			columnNames = columnNames.concat(", " + EQUIP_CONTAINER_ID_);
			values = values.concat(", '" + equipContainer + "'");
			
			duplicate = duplicate.concat(EQUIP_CONTAINER_ID_ + " = VALUES(" + EQUIP_CONTAINER_ID_ + "), ");  
			update = true; 
		}
		
		// Add base voltage ID
		if (baseVoltage != null) {
			columnNames = columnNames.concat(", " + BASE_VOLTAGE_ID_);
			values = values.concat(", '" + baseVoltage + "'");
			
			duplicate = duplicate.concat(BASE_VOLTAGE_ID_ + " = VALUES(" + BASE_VOLTAGE_ID_ + "), ");  
			update = true; 
		}
		
		String command = BREAKER_ + columnNames + ") " + values + ")"; 
		
		return insertSQL(command, duplicate, update); 
	}
	
	public String getName() { return name; }
		
	public String getState() { return state; } 
		
	public String getEquipContainer() { return equipContainer; } 
	
	public String getBaseVoltage() { return baseVoltage; } 
	
	public String getElement() { return BREAKER_; }

	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nState: " + state
				+ "\nEquipment Container: " + equipContainer + "\n");
	}
}
