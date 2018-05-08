package assignment.cim;

import org.w3c.dom.Element;


public class Substation extends BaseCIMClass{
	private static final String NAME_ = "NAME";
	private static final String REGION_ID_ = "REGION_ID"; 
	
	private static final String NAME = "cim:IdentifiedObject.name";
	private static final String REGION = "cim:Substation.Region"; 
	
	private String name;
	private String region; 
	
	public Substation(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		region = parseElement(element, REGION); 
	}
	
	public String createTable() {
		return SUBSTATION_ + " (" + RDF_ID_ + " VARCHAR(50) NOT NULL, " + NAME_ 
				+ " VARCHAR(50), " + REGION_ID_ + " VARCHAR(50), PRIMARY KEY("+ RDF_ID_ + "))";
	}
	
	public String insertTable() {
		String columnNames = " (";
		String values = "VALUES ("; 
		
		// Add rdf_id 
		columnNames = columnNames.concat(RDF_ID_); 
		values = values.concat("'" + rdfID + "'");
		
		// Add name
		if (name != null) {
			columnNames = columnNames.concat(", " + NAME_);
			values = values.concat(", '" + name + "'");
		}
		
		// Add region id
		if (region != null) {
			columnNames = columnNames.concat(", " + REGION_ID_);
			values = values.concat(", '" + region + "'");
		}
		
		return SUBSTATION_ + columnNames + ") " + values + ")";
	}
	
	public String getName() { return name; }
	
	public String getRegion() { return region; } 

	public String getElement() { return SUBSTATION_; } 
	
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nRegion: " + region + "\n");
	}
}
