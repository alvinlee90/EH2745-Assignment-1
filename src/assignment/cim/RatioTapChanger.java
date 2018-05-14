package assignment.cim;

import java.util.ArrayList;

import org.w3c.dom.Element;

/**
 * Class to store attributes for ratio tap changer.
 * Also generates SQL queries to create table and insert data into the table
 * 
 * @author Alvin Lee
 *
 */

public class RatioTapChanger extends BaseCimClass{
	private static final String NAME_ = "NAME";
	private static final String STEP_ = "STEP";

	private static final String NAME = "cim:IdentifiedObject.name";
	private static final String STEP = "cim:TapChanger.step";

	private String name;
	private String step; 
	
	public RatioTapChanger() {}
	
	public RatioTapChanger(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		step = parseElement(element, STEP); 
	}
	
	public String createTable() {
		return RATIO_TAP_ + " (" + RDF_ID_ + " " + STRING + " NOT NULL, " 
				+ NAME_ + " " + STRING + ", " + STEP_ + " " + INT + ", "
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
	
	public ArrayList<String> getAttributes() {
		ArrayList<String> attributes = new ArrayList<String>(); 
		
		attributes.add(RDF_ID_);
		attributes.add(NAME_);
		attributes.add(STEP_);

		return attributes; 
	}
	
	public String getName() { return name; }
		
	public String getStep() { return step; } 
	
	public String getElement() { return RATIO_TAP_; }
		
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nStep: " + step + "\n");
	}
}
