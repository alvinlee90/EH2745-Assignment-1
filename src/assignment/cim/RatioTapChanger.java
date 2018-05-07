package assignment.cim;

import org.w3c.dom.Element;


public class RatioTapChanger extends BaseCIMObject{
	static final String NAME = "cim:IdentifiedObject.name";
	static final String STEP = "cim:TapChanger.step";

	private String name;
	private String step; 
	
	public RatioTapChanger(Element element) {
		rdfID = element.getAttribute("rdf:ID");
		name = getElement(element, NAME); 
		step = getElement(element, STEP); 
	}
	
	public String getName() { return name; }
		
	public String getStep() { return step; } 
		
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nStep: " + step + "\n");
	}
}
