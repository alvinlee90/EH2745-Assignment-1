package assignment.cim;

import org.w3c.dom.Element;

public class BaseVoltage extends BaseCIMClass {
	private static final String NOMINAL_VOLTAGE_ = "NOMINAL_VOLTAGE";

	private static final String NOMINAL_VOLTAGE = "cim:BaseVoltage.nominalVoltage";
	
	private String nominalVolt; 
	
	public BaseVoltage(Element element) {
		parseRDF(element);
		nominalVolt = parseElement(element, NOMINAL_VOLTAGE); 
	}
	
	public String createTable() {
		return BASE_VOLTAGE_ + " (" + RDF_ID_  + " VARCHAR(50) NOT NULL, " + NOMINAL_VOLTAGE_ 
				+ " FLOAT, PRIMARY KEY(" + RDF_ID_ + "))";
	}
	
	public String insertTable() {
		String columnNames = " (";
		String values = "VALUES ("; 
		
		// Add rdf_id 
		columnNames = columnNames.concat(RDF_ID_); 
		values = values.concat(rdfID);
		
		// Add nominal voltage (check if empty)
		if (nominalVolt != null) {
			columnNames = columnNames.concat(", " + NOMINAL_VOLTAGE_);
			values = values.concat(", " + nominalVolt);
		}
		
		return BASE_VOLTAGE_ + columnNames + ") " + values + ")";
	}
	
	public String getNominalVolt() { return nominalVolt; }
	
	public String getElement() {return BASE_VOLTAGE_; }
	
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nNominal Voltage: " + nominalVolt + "\n");
	}
}