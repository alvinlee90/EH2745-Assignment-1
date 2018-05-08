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
	
	public String createTable() {
		return RATIO_TAP_ + " (RDFID VARCHAR(50) NOT NULL, NAME VARCHAR(50), STEP INTEGER, "
				+ "PRIMARY KEY(RDFID))"; 
	}
	
	public String getName() { return name; }
		
	public String getStep() { return step; } 
	
	public String getElement() { return RATIO_TAP_; }
		
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nStep: " + step + "\n");
	}
}
