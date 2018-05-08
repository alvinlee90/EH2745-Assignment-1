package assignment.cim;

import org.w3c.dom.Element;


public class RatioTapChanger extends BaseCIMClass{
	static final String NAME = "cim:IdentifiedObject.name";
	static final String STEP = "cim:TapChanger.step";

	private String name;
	private String step; 
	
	public RatioTapChanger(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		step = parseElement(element, STEP); 
	}
	
	public String getName() { return name; }
		
	public String getStep() { return step; } 
		
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nStep: " + step + "\n");
	}
}
