package assignment.cim;

import org.w3c.dom.Element;


public class RegulatingControl extends BaseCIMObject{
	static final String NAME = "cim:IdentifiedObject.name";
	static final String TARGET_VALUE = "cim:RegulatingControl.targetValue"; 

	private String name;
	private String targetValue; 
	
	public RegulatingControl(Element element) {
		rdfID = element.getAttribute("rdf:ID");
		name = getElement(element, NAME); 
		targetValue = getElement(element, TARGET_VALUE); 
	}
	
	public String getName() { return name; }
	
	public String getTargetValue() { return targetValue; } 

	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nTarget Value: " + targetValue + "\n");
	}
}
