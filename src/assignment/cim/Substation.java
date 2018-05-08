package assignment.cim;

import org.w3c.dom.Element;


public class Substation extends BaseCIMClass{
	static final String NAME = "cim:IdentifiedObject.name";
	static final String REGION = "cim:Substation.Region"; 
	
	private String name;
	private String region; 
	
	public Substation(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		region = parseElement(element, REGION); 
	}
	
	public String createTable() {
		return SUBSTATION_ + " (RDFID VARCHAR(50) NOT NULL, NAME VARCHAR(50), "
				+ "REGION_ID VARCHAR(50), PRIMARY KEY(RDFID))";
	}
	
	public String getName() { return name; }
	
	public String getRegion() { return region; } 

	public String getElement() { return SUBSTATION_; } 
	
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nRegion: " + region + "\n");
	}
}
