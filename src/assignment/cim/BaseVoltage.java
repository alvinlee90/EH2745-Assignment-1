package assignment.cim;

import org.w3c.dom.Element;

public class BaseVoltage extends BaseCIMClass {
	static final String NOMINAL_VOLTAGE = "cim:BaseVoltage.nominalVoltage";
	
	private String nominalVolt; 
	
	public BaseVoltage(Element element) {
		parseRDF(element);
		nominalVolt = parseElement(element, NOMINAL_VOLTAGE); 
	}
	
	public String getNominalVolt() { return nominalVolt; }
	
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nNominal Voltage: " + nominalVolt + "\n");
	}
}
