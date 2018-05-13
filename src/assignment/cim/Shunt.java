package assignment.cim;

import java.util.ArrayList;

import org.w3c.dom.Element;


public class Shunt extends BaseCimClass{
	private static final String NAME = "cim:IdentifiedObject.name";
	private static final String B = "cim:LinearShuntCompensator.bPerSection";
	private static final String G = "cim:LinearShuntCompensator.gPerSection";
	private static final String EQUIP_CONTAINER = "cim:Equipment.EquipmentContainer"; 

	private String name;
	private String b;
	private String g; 
	private String equipContainer;
	
	public Shunt() {}
	
	public Shunt(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		b = parseElement(element, B); 
		g = parseElement(element, G); 
		equipContainer = parseElement(element, EQUIP_CONTAINER); 
	}
	
	public String createTable() {
		return SHUNT_ + " (" + RDF_ID_ + " " + STRING + " NOT NULL, " + NAME_ 
				+ " " + STRING + ", " + B_ + " " + FLOAT + ", " + G_ + " " + FLOAT + ", " 
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
		
		// Add b
		if (b != null) {
			columnNames = columnNames.concat(", " + B_);
			values = values.concat(", '" + b + "'");
			
			duplicate = duplicate.concat(B_ + " = VALUES(" + B_ + "), ");  
			update = true; 
		}
		
		// Add g
		if (g != null) {
			columnNames = columnNames.concat(", " + G_);
			values = values.concat(", '" + g + "'");
			
			duplicate = duplicate.concat(G_ + " = VALUES(" + G_ + "), ");  
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
		String command = SHUNT_ + columnNames + ") " + values + ")"; 
		
		return insertSQL(command, duplicate, update); 
	}
	
	public ArrayList<String> getAttributes() {
		ArrayList<String> attributes = new ArrayList<String>(); 
		
		attributes.add(RDF_ID_);
		attributes.add(NAME_);
		attributes.add(B_);
		attributes.add(G_);
		attributes.add(EQUIP_CONTAINER_ID_);

		return attributes; 
	}
	
	public String getName() { return name; }
	
	public String getB() { return b; } 
	
	public String getG() { return g; } 
	
	public String getEquipContainer() { return equipContainer; } 

	public String getElement() { return SHUNT_; }
	
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nB: " + b + 
				"\nG: " + g + "\nEquipment Container: " + equipContainer + "\n");
	}
}
