package assignment.cim;

import org.w3c.dom.Element;


public class PowerTransformer extends BaseCimClass{
	private static final String NAME_ = "NAME";
	private static final String EQUIP_CONTAINER_ID_ = "EQUIPMENT_CONTAINER_ID"; 

	private static final String NAME = "cim:IdentifiedObject.name";
	private static final String EQUIP_CONTAINER = "cim:Equipment.EquipmentContainer"; 

	private String name;
	private String equipContainer; 
	
	public PowerTransformer(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		equipContainer = parseElement(element, EQUIP_CONTAINER); 
	}

	public String createTable() {
		return POWER_TRANS_ + " (" + RDF_ID_ + " VARCHAR(50) NOT NULL, " + NAME_ 
				+ " VARCHAR(50), " + EQUIP_CONTAINER_ID_ + " VARCHAR(50), PRIMARY KEY (" 
				+ RDF_ID_ + "))"; 
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
				
		// Add equipment container ID
		if (equipContainer != null) {
			columnNames = columnNames.concat(", " + EQUIP_CONTAINER_ID_);
			values = values.concat(", '" + equipContainer + "'");
			
			duplicate = duplicate.concat(EQUIP_CONTAINER_ID_ + " = VALUES(" 
										 + EQUIP_CONTAINER_ID_ + "), ");  
			update = true; 
		}
			
		// Return SQL command (check possibility for duplicates already in table)
		String command = POWER_TRANS_ + columnNames + ") " + values + ")"; 
		
		return insertSQL(command, duplicate, update); 
	}
	
	public String getName() { return name; }
	
	public String getEquipContainer() { return equipContainer; } 

	public String getElement() { return POWER_TRANS_; }
	
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nEquipment Container: " + equipContainer + "\n");
	}
}
