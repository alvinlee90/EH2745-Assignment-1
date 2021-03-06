package assignment.cim;

import java.util.ArrayList;

import org.w3c.dom.Element;

/**
 * Class to store attributes for power transformer end.
 * Also generates SQL queries to create table and insert data into the table
 * 
 * @author Alvin Lee
 *
 */

public class PowerTransformerEnd extends BaseCimClass{
	private static final String NAME = "cim:IdentifiedObject.name";
	private static final String TRANSFORMER_R = "cim:PowerTransformerEnd.r";
	private static final String TRANSFORMER_X = "cim:PowerTransformerEnd.x"; 
	private static final String TRANSFORMER = "cim:PowerTransformerEnd.PowerTransformer"; 
	private static final String BASE_VOLTAGE = "cim:TransformerEnd.BaseVoltage"; 
	private static final String TERMINAL = "cim:TransformerEnd.Terminal"; 
	
	private String name;
	private String transformerR;
	private String transformerX;
	private String transformer;
	private String baseVoltage; 
	private String terminal; 
	
	public PowerTransformerEnd() {}
	
	public PowerTransformerEnd(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		transformerR = parseElement(element, TRANSFORMER_R); 
		transformerX = parseElement(element, TRANSFORMER_X); 
		transformer = parseElement(element, TRANSFORMER);
		baseVoltage = parseElement(element, BASE_VOLTAGE); 
		terminal = parseElement(element, TERMINAL); 
	}
	
	public String createTable() {
		return POWER_TRANS_END_ + " (" + RDF_ID_ + " " + STRING + " NOT NULL, " + NAME_ 
				+ " " + STRING + ", " + R_ + " " + FLOAT + ", " + X_ + " " + FLOAT + ", " 
				+ TRANSFORMER_ID_ + " " + STRING + ", " + BASE_VOLTAGE_ID_ + " " + STRING 
				+ ", " + TERMINAL_ID_ + " "  + STRING
				+ ", PRIMARY KEY (" + RDF_ID_ + "), FOREIGN KEY (" + TRANSFORMER_ID_ 
				+ ") REFERENCES " + POWER_TRANS_ + "(" + RDF_ID_ + "), FOREIGN KEY (" 
				+ BASE_VOLTAGE_ID_ + ") REFERENCES " + BASE_VOLTAGE_ + "(" + RDF_ID_ + "))"; 
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
		if (transformerR != null) {
			columnNames = columnNames.concat(", " + R_);
			values = values.concat(", '" + transformerR + "'");
			
			duplicate = duplicate.concat(R_ + " = VALUES(" + R_ + "), ");  
			update = true; 
		}
				
		// Add X
		if (transformerX != null) {
			columnNames = columnNames.concat(", " + X_);
			values = values.concat(", '" + transformerX + "'");
			
			duplicate = duplicate.concat(X_ + " = VALUES(" + X_ + "), ");  
			update = true; 
		}
		
		// Add transformer ID
		if (transformer != null) {
			columnNames = columnNames.concat(", " + TRANSFORMER_ID_);
			values = values.concat(", '" + transformer + "'");
			
			duplicate = duplicate.concat(TRANSFORMER_ID_ + " = VALUES(" 
										 + TRANSFORMER_ID_ + "), ");  
			update = true; 
		}
		
		// Add base voltage ID
		if (baseVoltage != null) {
			columnNames = columnNames.concat(", " + BASE_VOLTAGE_ID_);
			values = values.concat(", '" + baseVoltage + "'");
			
			duplicate = duplicate.concat(BASE_VOLTAGE_ID_ + " = VALUES(" 
										 + BASE_VOLTAGE_ID_ + "), ");  
			update = true; 
		}
		
		// Add terminal ID
		if (terminal != null) {
			columnNames = columnNames.concat(", " + TERMINAL_ID_);
			values = values.concat(", '" + terminal + "'");
			
			duplicate = duplicate.concat(TERMINAL_ID_ + " = VALUES(" 
										 + TERMINAL_ID_ + "), ");  
			update = true; 
		}

		
		// Return SQL command (check possibility for duplicates already in table)
		String command = POWER_TRANS_END_ + columnNames + ") " + values + ")"; 
		
		return insertSQL(command, duplicate, update);
	}

	public ArrayList<String> getAttributes() {
		ArrayList<String> attributes = new ArrayList<String>(); 
		
		attributes.add(RDF_ID_);
		attributes.add(NAME_);
		attributes.add(R_);
		attributes.add(X_);
		attributes.add(TRANSFORMER_ID_);
		attributes.add(BASE_VOLTAGE_ID_);
		attributes.add(TERMINAL_ID_); 

		return attributes; 
	}
	
	public String getName() { return name; }
		
	public String getTransformerR() { return transformerR; } 
	
	public String getTransformerX() { return transformerX; } 
	
	public String getTransformer() { return transformer; } 
	
	public String getBaseVoltage() { return baseVoltage; } 

	public String getElement() { return POWER_TRANS_END_; }

	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nR: " + transformerR + "\nX: " + transformerX 
				+ "\nTransformer: " + TRANSFORMER + "\nBase Voltage: " + baseVoltage + "\n");
	}
}
