package assignment.cim;

import java.util.ArrayList;

import org.w3c.dom.Element;

/**
 * Class to store attributes for generating unit.
 * Also generates SQL queries to create table and insert data into the table
 * 
 * @author Alvin Lee
 *
 */

public class GeneratingUnit extends BaseCimClass{
	private static final String NAME = "cim:IdentifiedObject.name";
	private static final String MAX_P = "cim:GeneratingUnit.maxOperatingP"; 
	private static final String MIN_P = "cim:GeneratingUnit.minOperatingP";
	private static final String EQUIP_CONTAINER = "cim:Equipment.EquipmentContainer"; 

	private String name;
	private String maxP;
	private String minP;
	private String equipContainer; 
	
	public GeneratingUnit() {}
	
	public GeneratingUnit(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		maxP = parseElement(element, MAX_P); 
		minP = parseElement(element, MIN_P); 
		equipContainer = parseElement(element, EQUIP_CONTAINER); 
	}
	
	public String createTable() {
		return GENERATING_UNIT_ + " (" + RDF_ID_ + " " + STRING + " NOT NULL, " + NAME_ 
				+ " " + STRING + ", " + MAX_P_ + " " + FLOAT + ", " + MIN_P_ + " " + FLOAT + ", " 
				+ EQUIP_CONTAINER_ID_ + " " + STRING + ", PRIMARY KEY(" + RDF_ID_ + "))"; 
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
		
		// Add max P
		if (maxP != null) {
			columnNames = columnNames.concat(", " + MAX_P_);
			values = values.concat(", '" + maxP + "'");
			
			duplicate = duplicate.concat(MAX_P_ + " = VALUES(" + MAX_P_ + "), ");  
			update = true; 
		}
		
		// Add min P
		if (minP != null) {
			columnNames = columnNames.concat(", " + MIN_P_);
			values = values.concat(", '" + minP + "'");
			
			duplicate = duplicate.concat(MIN_P_ + " = VALUES(" + MIN_P_ + "), ");  
			update = true; 
		}
		
		// Add equipment container ID
		if (equipContainer != null) {
			columnNames = columnNames.concat(", " + EQUIP_CONTAINER_ID_);
			values = values.concat(", '" + equipContainer + "'");
			
			duplicate = duplicate.concat(EQUIP_CONTAINER_ID_ + " = VALUES(" + 
										 EQUIP_CONTAINER_ID_ + "), ");  
			update = true; 
		}
		
		// Return SQL command (check possibility for duplicates already in table)
		String command = GENERATING_UNIT_ + columnNames + ") " + values + ")"; 
		
		return insertSQL(command, duplicate, update); 
	}
	
	public ArrayList<String> getAttributes() {
		ArrayList<String> attributes = new ArrayList<String>(); 
		
		attributes.add(RDF_ID_);
		attributes.add(NAME_);
		attributes.add(MAX_P_);
		attributes.add(MIN_P_);
		attributes.add(EQUIP_CONTAINER_ID_);

		return attributes; 
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
