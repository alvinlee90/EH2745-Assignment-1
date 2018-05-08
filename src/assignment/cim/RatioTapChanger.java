package assignment.cim;

import org.w3c.dom.Element;


public class RatioTapChanger extends BaseCIMClass{
	private static final String NAME_ = "NAME";
	private static final String STEP_ = "STEP";

	private static final String NAME = "cim:IdentifiedObject.name";
	private static final String STEP = "cim:TapChanger.step";

	private String name;
	private String step; 
	
	public RatioTapChanger(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		step = parseElement(element, STEP); 
	}
	
	public String createTable() {
		return RATIO_TAP_ + " (" + RDF_ID_ + " VARCHAR(50) NOT NULL, " 
				+ NAME_ + " VARCHAR(50), " + STEP_ + " INTEGER, "
				+ "PRIMARY KEY("  + RDF_ID_ + "))"; 
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
				
		// Add R
		if (step != null) {
			columnNames = columnNames.concat(", " + STEP_);
			values = values.concat(", '" + step + "'");
			
			duplicate = duplicate.concat(STEP_ + " = VALUES(" + STEP_ + "), ");  
			update = true; 
		}
		
		// Return SQL command (check possibility for duplicates already in table)
		String command = RATIO_TAP_ + columnNames + ") " + values + ")"; 
		
		return insertSQL(command, duplicate, update); 
	}
	
	public String getName() { return name; }
		
	public String getStep() { return step; } 
	
	public String getElement() { return RATIO_TAP_; }
		
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nStep: " + step + "\n");
	}
}
