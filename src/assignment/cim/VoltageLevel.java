package assignment.cim;

import org.w3c.dom.Element;


public class VoltageLevel extends BaseCIMClass{
	private static final String NAME_ = "NAME";
	private static final String SUBSTATION_ID_ = "SUBSTATION_ID";
	private static final String BASE_VOLTAGE_ID_ = "BASE_VOLTAGE_ID";

	private static final String NAME = "cim:IdentifiedObject.name";
	private static final String SUBSTATION = "cim:VoltageLevel.Substation"; 
	private static final String BASE_VOLTAGE = "cim:VoltageLevel.BaseVoltage";
	
	private String name;
	private String substation;
	private String baseVoltage; 
	
	public VoltageLevel(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		substation = parseElement(element, SUBSTATION); 
		baseVoltage = parseElement(element, BASE_VOLTAGE); 
	}
	
	public String createTable() {
		return VOLTAGE_LEVEL_ + " (" + RDF_ID_ + " VARCHAR(50) NOT NULL, " + NAME_ + " VARCHAR(50), " 
				+ SUBSTATION_ID_ + " VARCHAR(50), " + BASE_VOLTAGE_ID_ + " VARCHAR(50), "
				+ "PRIMARY KEY( " + RDF_ID_ + "), FOREIGN KEY (" + SUBSTATION_ID_ + ") REFERENCES " 
				+ SUBSTATION_ + "(" + RDF_ID_ + "), FOREIGN KEY (" + BASE_VOLTAGE_ID_+ ") REFERENCES " 
				+ BASE_VOLTAGE_ + "(" + RDF_ID_ + "))";
	}
	
	public String insertTable() {
		String columnNames = " (";
		String values = "VALUES ("; 
		
		// Add rdf_id 
		columnNames = columnNames.concat(RDF_ID_); 
		values = values.concat(rdfID);
		
		// Add name
		if (name != null) {
			columnNames = columnNames.concat(", " + NAME_);
			values = values.concat(", " + name);
		}
		
		// Add substation id
		if (substation != null) {
			columnNames = columnNames.concat(", " + SUBSTATION_ID_);
			values = values.concat(", " + substation);
		}
		
		// Add base voltage id
		if (substation != null) {
			columnNames = columnNames.concat(", " + BASE_VOLTAGE_ID_);
			values = values.concat(", " + baseVoltage);
		}
		
		return VOLTAGE_LEVEL_ + columnNames + ") " + values + ")";
	}

	public String getName() { return name; }
	
	public String getSubstation() { return substation; } 
	
	public String getBaseVoltage() { return baseVoltage; } 

	public String getElement() { return VOLTAGE_LEVEL_; }
	
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nSubstation: " + substation + 
				"\nBase Voltage: " + baseVoltage + "\n");
	}
}
