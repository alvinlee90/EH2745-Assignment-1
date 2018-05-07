package assignment.cim;

import org.w3c.dom.Element;


public class PowerTransformerEnd extends BaseCIMObject{
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
		rdfID = element.getAttribute("rdf:ID");
		name = getElement(element, NAME); 
		transformerR = getElement(element, TRANSFORMER_R); 
		transformerX = getElement(element, TRANSFORMER_X); 
		transformer = getElement(element, TRANSOFRMER);
		baseVoltage = getElement(element, BASE_VOLTAGE); 
	}
	
	public String getName() { return name; }
		
	public String getTransformerR() { return transformerR; } 
	
	public String getTransformerX() { return transformerX; } 
	
	public String getTransformer() { return transformer; } 
	
	public String getBaseVoltage() { return baseVoltage; } 

	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nR: " + transformerR + "\nX: " + transformerX 
				+ "\nTransformer: " + TRANSOFRMER + "\nBase Voltage: " + baseVoltage + "\n");
	}
}
