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
	
	public String getName() { return name; }
	
	public String getSubstation() { return substation; } 
	
	public String getBaseVoltage() { return baseVoltage; } 

	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nSubstation: " + substation + 
				"\nBase Voltage: " + baseVoltage + "\n");
	}
}
