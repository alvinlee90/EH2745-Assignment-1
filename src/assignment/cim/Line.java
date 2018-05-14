package assignment.cim;

import java.util.ArrayList;

import org.w3c.dom.Element;

/**
 * Class to store attributes for ac line.
 * Also generates SQL queries to create table and insert data into the table
 * 
 * @author Alvin Lee
 *
 */

public class Line extends BaseCimClass{
	private static final String NAME = "cim:IdentifiedObject.name";
	private static final String R = "cim:ACLineSegment.r";
	private static final String X = "cim:ACLineSegment.x";
	private static final String BCH = "cim:ACLineSegment.bch"; 
	private static final String GCH = "cim:ACLineSegment.gch"; 
	private static final String BASE_VOLTAGE_ID = "cim:ConductingEquipment.BaseVoltage"; 

	private String name;
	private String r;
	private String x;
	private String bch;
	private String gch; 
	private String baseVoltage; 
	
	public Line() {}
	
	public Line(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		r = parseElement(element, R); 
		x = parseElement(element, X); 
		bch = parseElement(element, BCH); 
		gch = parseElement(element, GCH); 
		baseVoltage = parseElement(element, BASE_VOLTAGE_ID); 
	}
	
	public String createTable() {
		return LINE_ + " (" + RDF_ID_ + " " + STRING + " NOT NULL, " + NAME_ 
				+ " " + STRING + ", " + R_ + " " + FLOAT + ", " + X_ + " " + FLOAT + ", " 
				+ BCH_ + " " + FLOAT + ", " + GCH_ + " " + FLOAT + ", " + BASE_VOLTAGE_ID_ 
				+ " " + STRING + ", PRIMARY KEY(" + RDF_ID_ + "))"; 
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
		
		// Add r
		if (r != null) {
			columnNames = columnNames.concat(", " + R_);
			values = values.concat(", '" + r + "'");
			
			duplicate = duplicate.concat(R_ + " = VALUES(" + R_ + "), ");  
			update = true; 
		}
		
		// Add x
		if (x != null) {
			columnNames = columnNames.concat(", " + X_);
			values = values.concat(", '" + x + "'");
			
			duplicate = duplicate.concat(X_ + " = VALUES(" + X_ + "), ");  
			update = true; 
		}
		
		// Add bch
		if (bch != null) {
			columnNames = columnNames.concat(", " + BCH_);
			values = values.concat(", '" + bch + "'");
			
			duplicate = duplicate.concat(BCH_ + " = VALUES(" + BCH_ + "), ");  
			update = true; 
		}
		
		// Add gch
		if (gch != null) {
			columnNames = columnNames.concat(", " + GCH_);
			values = values.concat(", '" + gch + "'");
			
			duplicate = duplicate.concat(GCH_ + " = VALUES(" + GCH_ + "), ");  
			update = true; 
		}
		
		// Add base voltage ID
		if (baseVoltage != null) {
			columnNames = columnNames.concat(", " + BASE_VOLTAGE_ID_);
			values = values.concat(", '" + baseVoltage + "'");
			
			duplicate = duplicate.concat(BASE_VOLTAGE_ID_ + " = VALUES(" + 
					BASE_VOLTAGE_ID_ + "), ");  
			update = true; 
		}
		
		// Return SQL command (check possibility for duplicates already in table)
		String command = LINE_ + columnNames + ") " + values + ")"; 
		
		return insertSQL(command, duplicate, update); 
	}
	
	public ArrayList<String> getAttributes() {
		ArrayList<String> attributes = new ArrayList<String>(); 
		
		attributes.add(RDF_ID_);
		attributes.add(NAME_);
		attributes.add(R_);
		attributes.add(X_);
		attributes.add(BCH_);
		attributes.add(GCH_);
		attributes.add(BASE_VOLTAGE_ID_);

		return attributes; 
	}
	
	public String getName() { return name; }
	
	public String getR() { return r; } 
	
	public String getX() { return x; } 
	
	public String getBch() { return bch; } 
	
	public String getGch() { return gch; } 
	
	public String getBaseVoltage() { return baseVoltage; } 

	public String getElement() { return LINE_; }
	
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nR: " + r + 
				"\nX: " + x + "\nBch: " + bch + "\nGch: " + gch + "\nBase Voltage ID: " 
				+ baseVoltage + "\n");
	}
}
