package assignment.cim;

import org.w3c.dom.Element;


public class RegulatingControl extends BaseCIMClass{
	private static final String NAME_ = "NAME";
	private static final String TARGET_VALUE_ = "TARGET_VALUE";

	private static final String NAME = "cim:IdentifiedObject.name";
	private static final String TARGET_VALUE = "cim:RegulatingControl.targetValue"; 

	private String name;
	private String targetValue; 
	
	public RegulatingControl(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		targetValue = parseElement(element, TARGET_VALUE); 
	}
	
	public String createTable() {
		return REG_CONTROL_ + " (" + RDF_ID_ + " VARCHAR(50) NOT NULL, " + NAME_ 
				+ " VARCHAR(50), " + TARGET_VALUE_ + " FLOAT, PRIMARY KEY(" + RDF_ID_ + "))"; 
	}
	
	public String insertTable() {
		String columnNames = " (";
		String values = "VALUES ("; 
		
		// Add rdf_id 
		columnNames = columnNames.concat(RDF_ID_); 
		values = values.concat(rdfID);
		
		// Add name
		if (name != null) {
			columnNames = columnNames.concat(", " + NAME_);
			values = values.concat(", " + name);
		}
		
		// Add equipment container ID
		if (targetValue != null) {
			columnNames = columnNames.concat(", " + TARGET_VALUE_);
			values = values.concat(", " + targetValue);
		}
		
		return REG_CONTROL_ + columnNames + ") " + values + ")";
	}
	
	public String getName() { return name; }
	
	public String getTargetValue() { return targetValue; } 

	public String getElement() { return REG_CONTROL_; }
	
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nTarget Value: " + targetValue + "\n");
	}
}
