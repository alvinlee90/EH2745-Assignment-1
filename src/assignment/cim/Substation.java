package assignment.cim;

import java.util.ArrayList;

import org.w3c.dom.Element;


public class Substation extends BaseCimClass{
	private static final String NAME_ = "NAME";
	private static final String REGION_ID_ = "REGION_ID"; 
	
	private static final String NAME = "cim:IdentifiedObject.name";
	private static final String REGION = "cim:Substation.Region"; 
	
	private String name;
	private String region; 
	
	public Substation() {}
	
	public Substation(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		region = parseElement(element, REGION); 
	}
	
	public String createTable() {
		return SUBSTATION_ + " (" + RDF_ID_ + " " + STRING + " NOT NULL, " + NAME_ 
				+ " " + STRING + ", " + REGION_ID_ + " " + STRING + ", PRIMARY KEY("
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
		
		// Add region id
		if (region != null) {
			columnNames = columnNames.concat(", " + REGION_ID_);
			values = values.concat(", '" + region + "'");
			
			duplicate = duplicate.concat(REGION_ID_ + " = VALUES(" + REGION_ID_ + "), ");  
			update = true; 
		}

		// Return SQL command (check possibility for duplicates already in table)
		String command = SUBSTATION_ + columnNames + ") " + values + ")"; 
		
		return insertSQL(command, duplicate, update); 
	}
	
	public ArrayList<String> getAttributes() {
		ArrayList<String> attributes = new ArrayList<String>(); 
		
		attributes.add(RDF_ID_);
		attributes.add(NAME_);
		attributes.add(REGION_ID_);

		return attributes; 
	}
	
	public String getName() { return name; }
	
	public String getRegion() { return region; } 

	public String getElement() { return SUBSTATION_; } 
	
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nRegion: " + region + "\n");
	}
}
