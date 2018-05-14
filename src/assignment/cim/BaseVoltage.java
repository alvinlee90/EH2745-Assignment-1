package assignment.cim;

import java.util.ArrayList;

import org.w3c.dom.Element;

/** Class to store attributes for the base voltage.
 * Also generates SQL queries to create table and insert data into the table
 * 
 * @author Alvin Lee
 *
 */

public class BaseVoltage extends BaseCimClass {
	private static final String NOMINAL_VOLTAGE = "cim:BaseVoltage.nominalVoltage";
	
	private String nominalVolt; 
	
	public BaseVoltage() {}
	
	public BaseVoltage(Element element) {
		parseRDF(element);
		nominalVolt = parseElement(element, NOMINAL_VOLTAGE); 
	}
	
	public String createTable() {
		return BASE_VOLTAGE_ + " (" + RDF_ID_  + " " + STRING + " NOT NULL, " 
				+ NOMINAL_VOLTAGE_ + " " + FLOAT + ", PRIMARY KEY(" + RDF_ID_ + "))";
	}
	
	public String insertTable() {
		String columnNames = " (";
		String values = "VALUES (";
		
		String duplicate = " ON DUPLICATE KEY UPDATE "; 
		Boolean update = false; 
		
		// Add rdf_id 
		columnNames = columnNames.concat(RDF_ID_); 
		values = values.concat("'" + rdfID + "'");
		
		// Add nominal voltage (check if empty)
		if (nominalVolt != null) {
			columnNames = columnNames.concat(", " + NOMINAL_VOLTAGE_);
			values = values.concat(", '" + nominalVolt + "'");
			
			duplicate = duplicate.concat(NOMINAL_VOLTAGE_ + " = VALUES(" + NOMINAL_VOLTAGE_ + "), ");  
			update = true; 
		}
		
		// Return SQL command (check possibility for duplicates already in table)
		String command = BASE_VOLTAGE_ + columnNames + ") " + values + ")"; 
		
		return insertSQL(command, duplicate, update); 
	}
	
	public ArrayList<String> getAttributes() {
		ArrayList<String> attributes = new ArrayList<String>(); 
		
		attributes.add(RDF_ID_); 
		attributes.add(NOMINAL_VOLTAGE_);

		return attributes; 
	}
	
	public String getNominalVolt() { return nominalVolt; }
	
	public String getElement() {return BASE_VOLTAGE_; }
	
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nNominal Voltage: " + nominalVolt + "\n");
	}
}
