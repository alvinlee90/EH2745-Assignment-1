package assignment.cim;

import org.w3c.dom.Element;


public class RegulatingControl extends BaseCimClass{
	private static final String NAME_ = "NAME";
	private static final String TARGET_VALUE_ = "TARGET_VALUE";

	private static final String NAME = "cim:IdentifiedObject.name";
	private static final String TARGET_VALUE = "cim:RegulatingControl.targetValue"; 

	private String name;
	private String targetValue; 
	
	public RegulatingControl(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		targetValue = parseElement(element, TARGET_VALUE); 
	}
	
	public String createTable() {
		return REG_CONTROL_ + " (" + RDF_ID_ + " VARCHAR(50) NOT NULL, " + NAME_ 
				+ " VARCHAR(50), " + TARGET_VALUE_ + " FLOAT, PRIMARY KEY(" + RDF_ID_ + "))"; 
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
		if (targetValue != null) {
			columnNames = columnNames.concat(", " + TARGET_VALUE_);
			values = values.concat(", '" + targetValue + "'");
			
			duplicate = duplicate.concat(TARGET_VALUE_ + " = VALUES(" + TARGET_VALUE_ + "), ");  
			update = true; 
		}

		// Return SQL command (check possibility for duplicates already in table)
		String command = REG_CONTROL_ + columnNames + ") " + values + ")"; 
		
		return insertSQL(command, duplicate, update); 
	}
	
	public String getName() { return name; }
	
	public String getTargetValue() { return targetValue; } 

	public String getElement() { return REG_CONTROL_; }
	
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nTarget Value: " + targetValue + "\n");
	}
}
