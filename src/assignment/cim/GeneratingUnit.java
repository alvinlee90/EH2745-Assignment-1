package assignment.cim;

import org.w3c.dom.Element;


public class GeneratingUnit extends BaseCIMClass{
	private static final String NAME_ = "NAME";
	private static final String MAX_P_ = "MAX_P";
	private static final String MIN_P_ = "MIN_P";
	private static final String EQUIP_CONTAINER_ID_ = "EQUIPMENT_CONTAINER_ID"; 

	private static final String NAME = "cim:IdentifiedObject.name";
	private static final String MAX_P = "cim:GeneratingUnit.maxOperatingP"; 
	private static final String MIN_P = "cim:GeneratingUnit.minOperatingP";
	private static final String EQUIP_CONTAINER = "cim:Equipment.EquipmentContainer"; 

	private String name;
	private String maxP;
	private String minP;
	private String equipContainer; 
	
	public GeneratingUnit(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		maxP = parseElement(element, MAX_P); 
		minP = parseElement(element, MIN_P); 
		equipContainer = parseElement(element, EQUIP_CONTAINER); 
	}
	
	public String createTable() {
		return GENERATING_UNIT_ + " (" + RDF_ID_ + " VARCHAR(50) NOT NULL, " + NAME_ 
				+ " VARCHAR(50), " + MAX_P_ + " FLOAT, " + MIN_P_ + " FLOAT, " 
				+ EQUIP_CONTAINER_ID_ + " VARCHAR(50), PRIMARY KEY(" + RDF_ID_ + "))"; 
	}
	
	public String insertTable() {
		String columnNames = " (";
		String values = "VALUES ("; 
		
		// Add rdf_id 
		columnNames = columnNames.concat(RDF_ID_); 
		values = values.concat(rdfID);
		
		// Add name
		if (name != null) {
			columnNames = columnNames.concat(", " + NAME_);
			values = values.concat(", " + name);
		}
		
		// Add max P
		if (maxP != null) {
			columnNames = columnNames.concat(", " + MAX_P_);
			values = values.concat(", " + maxP);
		}
		
		// Add min P
		if (minP != null) {
			columnNames = columnNames.concat(", " + MIN_P_);
			values = values.concat(", " + minP);
		}
		
		// Add equipment container ID
		if (equipContainer != null) {
			columnNames = columnNames.concat(", " + EQUIP_CONTAINER_ID_);
			values = values.concat(", " + equipContainer);
		}
		
		return GENERATING_UNIT_ + columnNames + ") " + values + ")";
	}
	
	public String getName() { return name; }
	
	public String getMaxP() { return maxP; } 
	
	public String getMinP() { return minP; } 
	
	public String getEquipContainer() { return equipContainer; } 

	public String getElement() { return GENERATING_UNIT_; }
	
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nMax P: " + maxP + 
				"\nMin P: " + minP + "\nEquipment Container: " + equipContainer + "\n");
	}
}
