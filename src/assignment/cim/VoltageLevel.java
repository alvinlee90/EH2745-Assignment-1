package assignment.cim;

import org.w3c.dom.Element;


public class VoltageLevel extends BaseCIMClass{
	static final String NAME = "cim:IdentifiedObject.name";
	static final String SUBSTATION = "cim:VoltageLevel.Substation"; 
	static final String BASE_VOLTAGE = "cim:VoltageLevel.BaseVoltage";
	
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
		return VOLTAGE_LEVEL_ + " (RDFID VARCHAR(50) NOT NULL, NAME VARCHAR(50), SUBSTATION_ID VARCHAR(50), "
				+ "BASE_VOLTAGE_ID VARCHAR(50), PRIMARY KEY(RDFID), FOREIGN KEY (SUBSTATION_ID) REFERENCES " 
				+ SUBSTATION_ + "(RDFID), FOREIGN KEY (BASE_VOLTAGE_ID) REFERENCES " + BASE_VOLTAGE_ 
				+ "(RDFID))";
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
