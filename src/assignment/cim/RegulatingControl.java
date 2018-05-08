package assignment.cim;

import org.w3c.dom.Element;


public class RegulatingControl extends BaseCIMClass{
	static final String NAME = "cim:IdentifiedObject.name";
	static final String TARGET_VALUE = "cim:RegulatingControl.targetValue"; 

	private String name;
	private String targetValue; 
	
	public RegulatingControl(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		targetValue = parseElement(element, TARGET_VALUE); 
	}
	
	public String createTable() {
		return REG_CONTROL_ + " (RDFID VARCHAR(50) NOT NULL, NAME VARCHAR(50), "
				+ "TARGET_VALUE FLOAT, PRIMARY KEY(RDFID))"; 
	}
	
	public String getName() { return name; }
	
	public String getTargetValue() { return targetValue; } 

	public String getElement() { return REG_CONTROL_; }
	
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nTarget Value: " + targetValue + "\n");
	}
}
