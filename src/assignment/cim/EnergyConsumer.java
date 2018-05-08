package assignment.cim;

import org.w3c.dom.Element;


public class EnergyConsumer extends BaseCIMClass{
	static final String NAME = "cim:IdentifiedObject.name";
	static final String CONSUMER_P = "cim:EnergyConsumer.p";
	static final String CONSUMER_Q = "cim:EnergyConsumer.q"; 
	static final String EQUIP_CONTAINER = "cim:Equipment.EquipmentContainer"; 
//	static final String BASE_VOLTAGE = 

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
	
	public String getName() { return name; }
		
	public String getConsumerP() { return consumerP; } 
	
	public String getConsumerQ() { return consumerQ; } 
	
	public String getEquipContainer() { return equipContainer; } 
	
	public String getBaseVoltage() { return baseVoltage; } 

	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nP: " + consumerP + "\nQ: " + consumerQ 
				+ "\nEquipment Container: " + equipContainer + "\n");
	}
}
