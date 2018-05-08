package assignment.cim;

import org.w3c.dom.Element;


public class PowerTransformerEnd extends BaseCIMClass{
	private static final String NAME_ = "NAME";
	private static final String R_ = "R";
	private static final String X_ = "X";
	private static final String TRANSFORMER_ID_ = "TRANSFORMER_ID"; 
	private static final String BASE_VOLTAGE_ID_ = "BASE_VOLTAGE_ID"; 

	private static final String NAME = "cim:IdentifiedObject.name";
	private static final String TRANSFORMER_R = "cim:PowerTransformerEnd.r";
	private static final String TRANSFORMER_X = "cim:PowerTransformerEnd.x"; 
	private static final String TRANSFORMER = "cim:PowerTransformerEnd.PowerTransformer"; 
	private static final String BASE_VOLTAGE = "cim:TransformerEnd.BaseVoltage"; 

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
		transformer = parseElement(element, TRANSFORMER);
		baseVoltage = parseElement(element, BASE_VOLTAGE); 
	}
	
	public String createTable() {
		return POWER_TRANS_END_ + " (" + RDF_ID_ + " VARCHAR(50) NOT NULL, " + NAME_ 
				+ " VARCHAR(50), " + R_ + " FLOAT, " + X_ + " FLOAT, " + TRANSFORMER_ID_ 
				+ " VARCHAR(50), " + BASE_VOLTAGE_ID_ + " VARCHAR(50), PRIMARY KEY (" 
				+ RDF_ID_ + "), FOREIGN KEY (" + TRANSFORMER_ID_ + ") REFERENCES "
				+ POWER_TRANS_ + "(" + RDF_ID_ + "), FOREIGN KEY (" + BASE_VOLTAGE_ID_ 
				+ ") REFERENCES " + BASE_VOLTAGE_ + "(" + RDF_ID_ + "))"; 
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
				
		// Add R
		if (transformerR != null) {
			columnNames = columnNames.concat(", " + R_);
			values = values.concat(", '" + transformerR + "'");
		}
				
		// Add X
		if (transformerX != null) {
			columnNames = columnNames.concat(", " + X_);
			values = values.concat(", '" + transformerX + "'");
		}
		
		// Add transformer ID
		if (transformer != null) {
			columnNames = columnNames.concat(", " + TRANSFORMER_ID_);
			values = values.concat(", '" + transformer + "'");
		}
		
		// Add base voltage ID
		if (baseVoltage != null) {
			columnNames = columnNames.concat(", " + BASE_VOLTAGE_ID_);
			values = values.concat(", '" + baseVoltage + "'");
		}
		
		return POWER_TRANS_END_ + columnNames + ") " + values + ")";
	}

	public String getName() { return name; }
		
	public String getTransformerR() { return transformerR; } 
	
	public String getTransformerX() { return transformerX; } 
	
	public String getTransformer() { return transformer; } 
	
	public String getBaseVoltage() { return baseVoltage; } 

	public String getElement() { return POWER_TRANS_END_; }

	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nR: " + transformerR + "\nX: " + transformerX 
				+ "\nTransformer: " + TRANSFORMER + "\nBase Voltage: " + baseVoltage + "\n");
	}
}
