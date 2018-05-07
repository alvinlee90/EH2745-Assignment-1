package assignment.cim;

import org.w3c.dom.Element;


public class Substation extends BaseCIMObject{
	static final String NAME = "cim:IdentifiedObject.name";
	static final String REGION = "cim:Substation.Region"; 
	
	private String name;
	private String region; 
	
	public Substation(Element element) {
		rdfID = element.getAttribute("rdf:ID");
		name = getElement(element, NAME); 
		region = getElement(element, REGION); 
	}
	
	public String getName() { return name; }
	
	public String getRegion() { return region; } 

	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nRegion: " + region + "\n");
	}
}
