package assignment.cim;

import java.util.ArrayList;

import org.w3c.dom.Element;


public class Terminal extends BaseCimClass{
	private static final String NAME = "cim:IdentifiedObject.name";
	private static final String CONDUCT_ID = "cim:Terminal.ConductingEquipment";
	private static final String CONNECT_ID = "cim:Terminal.ConnectivityNode"; 
	private static final String CONNECTED = "cim:ACDCTerminal.connected"; 
	
	private String name;
	private String conduct;
	private String connectID; 
	private String connected; 
	
	public Terminal() {}
	
	public Terminal(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		conduct = parseElement(element, CONDUCT_ID);
		connectID = parseElement(element, CONNECT_ID); 
		connected = parseElement(element, CONNECTED); 
	}
	
	public String createTable() {
		return TERMINAL_ + " (" + RDF_ID_ + " " + STRING + " NOT NULL, " + NAME_ + " " + STRING + ", "
				+ CONDUCT_ID_ + " " + STRING + ", " + CONNECT_ID_ + " " + STRING 
				+ ", " + CONNECTED_ + " " + STRING + ", PRIMARY KEY(" + RDF_ID_ + "))"; 
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
		
		// Add conducting equipment ID
		if (conduct != null) {
			columnNames = columnNames.concat(", " + CONDUCT_ID_);
			values = values.concat(", '" + conduct + "'");
			
			duplicate = duplicate.concat(CONDUCT_ID_ + " = VALUES(" 
										 + CONDUCT_ID_ + "), ");  
			update = true; 
		}
		
		// Add connecting container ID
		if (connectID != null) {
			columnNames = columnNames.concat(", " + CONNECT_ID_);
			values = values.concat(", '" + connectID + "'");
			
			duplicate = duplicate.concat(CONNECT_ID_ + " = VALUES(" 
										 + CONNECT_ID_ + "), ");  
			update = true; 
		}
		
		// Add connecting container ID
		if (connected != null) {
			columnNames = columnNames.concat(", " + CONNECTED_);
			values = values.concat(", '" + connected + "'");
			
			duplicate = duplicate.concat(CONNECTED_ + " = VALUES(" 
										 + CONNECTED_ + "), ");  
			update = true; 
		}

				
		// Return SQL command (check possibility for duplicates already in table)
		String command = TERMINAL_ + columnNames + ") " + values + ")"; 
		
		return insertSQL(command, duplicate, update); 
	}
	
	public ArrayList<String> getAttributes() {
		ArrayList<String> attributes = new ArrayList<String>(); 
		
		attributes.add(RDF_ID_);
		attributes.add(NAME_);
		attributes.add(CONDUCT_ID_);
		attributes.add(CONNECT_ID_);
		attributes.add(CONNECTED_); 

		return attributes; 
	}
	
	public String getName() { return name; }
		
	public String getConduct() { return conduct; } 

	public String getConnectID() { return connectID; }
	
	public String getConnected() { return connected; }
	
	public String getElement() { return TERMINAL_; }
	
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name 
				+ "\nConducting Equipment: " + conduct 
				+ "\nConnecting Equipment: " + connectID 
				+ "\nConnected: " + connected + "\n");
	}
}
