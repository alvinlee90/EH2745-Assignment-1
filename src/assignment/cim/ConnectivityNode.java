package assignment.cim;

import java.util.ArrayList;

import org.w3c.dom.Element;

/**
 * Class to store attributes for connectivity node.
 * Also generates SQL queries to create table and insert data into the table
 * 
 * @author Alvin Lee
 *
 */

public class ConnectivityNode extends BaseCimClass{
	private static final String NAME = "cim:IdentifiedObject.name";
	private static final String EQUIP_CONTAINER = "cim:ConnectivityNode.ConnectivityNodeContainer"; 
	
	private String name;
	private String equipContainer;
	
	public ConnectivityNode() {}
	
	public ConnectivityNode(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		equipContainer = parseElement(element, EQUIP_CONTAINER); 
	}
	
	public String createTable() {
		return CONNECT_NODE_ + " (" + RDF_ID_ + " " + STRING + " NOT NULL, " + NAME_ + " " + STRING + ", "
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
		
		// Add equipment container ID
		if (equipContainer != null) {
			columnNames = columnNames.concat(", " + EQUIP_CONTAINER_ID_);
			values = values.concat(", '" + equipContainer + "'");
			
			duplicate = duplicate.concat(EQUIP_CONTAINER_ID_ + " = VALUES(" 
										 + EQUIP_CONTAINER_ID_ + "), ");  
			update = true; 
		}
				
		// Return SQL command (check possibility for duplicates already in table)
		String command = CONNECT_NODE_ + columnNames + ") " + values + ")"; 
		
		return insertSQL(command, duplicate, update); 
	}
	
	public ArrayList<String> getAttributes() {
		ArrayList<String> attributes = new ArrayList<String>(); 
		
		attributes.add(RDF_ID_);
		attributes.add(NAME_);
		attributes.add(EQUIP_CONTAINER_ID_);

		return attributes; 
	}
	
	public String getName() { return name; }
		
	public String getEquipContainer() { return equipContainer; } 
	
	public String getElement() { return CONNECT_NODE_; }
	
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name 
				+ "\nEquipment Container: " + equipContainer + "\n");
	}
}
