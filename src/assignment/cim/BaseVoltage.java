package assignment.cim;

import org.w3c.dom.Element;

public class BaseVoltage extends BaseCIMClass {
	static final String NOMINAL_VOLTAGE = "cim:BaseVoltage.nominalVoltage";
	
	private String nominalVolt; 
	
	public BaseVoltage(Element element) {
		parseRDF(element);
		nominalVolt = parseElement(element, NOMINAL_VOLTAGE); 
	}
	
	public String createTable() {
		return BASE_VOLTAGE_ + " (RDFID VARCHAR(50) NOT NULL, NOMINAL_VOLTAGE FLOAT, PRIMARY KEY(RDFID))";
	}
	
	public String getNominalVolt() { return nominalVolt; }
	
	public String getElement() {return BASE_VOLTAGE_; }
	
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nNominal Voltage: " + nominalVolt + "\n");
	}
}
