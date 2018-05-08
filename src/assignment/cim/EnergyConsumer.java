package assignment.cim;

import org.w3c.dom.Element;


public class EnergyConsumer extends BaseCIMClass{
	private static final String NAME_ = "NAME";
	private static final String P_ = "P";
	private static final String Q_ = "Q";
	private static final String EQUIP_CONTAINER_ID_ = "EQUIPMENT_CONTAINER_ID"; 
	private static final String BASE_VOLTAGE_ID_ = "BASE_VOLTAGE_ID"; 

	private static final String NAME = "cim:IdentifiedObject.name";
	private static final String CONSUMER_P = "cim:EnergyConsumer.p";
	private static final String CONSUMER_Q = "cim:EnergyConsumer.q"; 
	private static final String EQUIP_CONTAINER = "cim:Equipment.EquipmentContainer"; 
	
	private String name;
	private String consumerP;
	private String consumerQ;
	private String equipContainer;
	private String baseVoltage; 
	
	public EnergyConsumer(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		consumerP = parseElement(element, CONSUMER_P); 
		consumerQ = parseElement(element, CONSUMER_Q); 
		equipContainer = parseElement(element, EQUIP_CONTAINER); 
	}
	
	public String createTable() {
		return ENERGY_CONSUMER_ + " (" + RDF_ID_ + " VARCHAR(50) NOT NULL, " + NAME_ + " VARCHAR(50), "
				+ P_ + " FLOAT, "	+ Q_ + " FLOAT, " + EQUIP_CONTAINER_ID_ + " VARCHAR(50), "
				+ BASE_VOLTAGE_ID_ + " VARCHAR(50), PRIMARY KEY(" + RDF_ID_ + "), FOREIGN KEY ("
				+ BASE_VOLTAGE_ID_+ ") REFERENCES " + BASE_VOLTAGE_ + "(" + RDF_ID_ + "))"; 
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
				
		// Add P
		if (consumerP != null) {
			columnNames = columnNames.concat(", " + P_);
			values = values.concat(", " + consumerP);
		}
				
		// Add Q
		if (consumerQ != null) {
			columnNames = columnNames.concat(", " + Q_);
			values = values.concat(", " + consumerQ);
		}
		
		// Add equipment container ID
		if (equipContainer != null) {
			columnNames = columnNames.concat(", " + EQUIP_CONTAINER_ID_);
			values = values.concat(", " + equipContainer);
		}
		
		// Add base voltage ID
		if (baseVoltage != null) {
			columnNames = columnNames.concat(", " + BASE_VOLTAGE_ID_);
			values = values.concat(", " + baseVoltage);
		}
		
		return ENERGY_CONSUMER_ + columnNames + ") " + values + ")";
	}
	
	public String getName() { return name; }
		
	public String getConsumerP() { return consumerP; } 
	
	public String getConsumerQ() { return consumerQ; } 
	
	public String getEquipContainer() { return equipContainer; } 
	
	public String getBaseVoltage() { return baseVoltage; } 

	public String getElement() { return ENERGY_CONSUMER_; }
	
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nP: " + consumerP + "\nQ: " + consumerQ 
				+ "\nEquipment Container: " + equipContainer + "\n");
	}
}
