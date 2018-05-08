package assignment.cim;

import org.w3c.dom.Element;


public class Breaker extends BaseCIMClass{
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
		
		// Add rdf_id 
		columnNames = columnNames.concat(RDF_ID_); 
		values = values.concat("'" + rdfID + "'");
		
		// Add name
		if (name != null) {
			columnNames = columnNames.concat(", " + NAME_);
			values = values.concat(", '" + name + "'");
		}
				
		// Add state
		if (state != null) {
			columnNames = columnNames.concat(", " + STATE_);
			values = values.concat(", '" + state + "'");
		}
		
		// Add equipment container ID
		if (equipContainer != null) {
			columnNames = columnNames.concat(", " + EQUIP_CONTAINER_ID_);
			values = values.concat(", '" + equipContainer + "'");
		}
		
		// Add base voltage ID
		if (baseVoltage != null) {
			columnNames = columnNames.concat(", " + BASE_VOLTAGE_ID_);
			values = values.concat(", '" + baseVoltage + "'");
		}
		
		return BREAKER_ + columnNames + ") " + values + ")";
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
