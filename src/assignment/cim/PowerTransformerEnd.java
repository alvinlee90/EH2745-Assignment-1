package assignment.cim;

import org.w3c.dom.Element;


public class PowerTransformerEnd extends BaseCIMClass{
	static final String NAME = "cim:IdentifiedObject.name";
	static final String TRANSFORMER_R = "cim:PowerTransformerEnd.r";
	static final String TRANSFORMER_X = "cim:PowerTransformerEnd.x"; 
	static final String TRANSOFRMER = "cim:PowerTransformerEnd.PowerTransformer"; 
	static final String BASE_VOLTAGE = "cim:TransformerEnd.BaseVoltage"; 

	private String name;
	private String transformerR;
	private String transformerX;
	private String transformer;
	private String baseVoltage; 
	
	public PowerTransformerEnd(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		transformerR = parseElement(element, TRANSFORMER_R); 
		transformerX = parseElement(element, TRANSFORMER_X); 
		transformer = parseElement(element, TRANSOFRMER);
		baseVoltage = parseElement(element, BASE_VOLTAGE); 
	}
	
	public String createTable() {
		return POWER_TRANS_END_ + " (RDFID VARCHAR(50) NOT NULL, NAME VARCHAR(50), R FLOAT, "
				+ "X FLOAT, TRANSFORMER_ID VARCHAR(50), BASE_VOLTAGE_ID VARCHAR(50), "
				+ "PRIMARY KEY (RDFID), FOREIGN KEY (TRANSFORMER_ID) REFERENCES "
				+ POWER_TRANS_ + "(RDFID), FOREIGN KEY (BASE_VOLTAGE_ID) REFERENCES " 
				+ BASE_VOLTAGE_ + "(RDFID))"; 
	}

	public String getName() { return name; }
		
	public String getTransformerR() { return transformerR; } 
	
	public String getTransformerX() { return transformerX; } 
	
	public String getTransformer() { return transformer; } 
	
	public String getBaseVoltage() { return baseVoltage; } 

	public String getElement() { return POWER_TRANS_END_; }

	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nR: " + transformerR + "\nX: " + transformerX 
				+ "\nTransformer: " + TRANSOFRMER + "\nBase Voltage: " + baseVoltage + "\n");
	}
}
