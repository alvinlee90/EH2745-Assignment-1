package assignment.cim;

import org.w3c.dom.Element;


public class Breaker extends BaseCIMClass{
	static final String NAME = "cim:IdentifiedObject.name";
	static final String STATE = "cim:Switch.open";
	static final String EQUIP_CONTAINER = "cim:Equipment.EquipmentContainer"; 
//	static final String BASE_VOLTAGE = 

	private String name;
	private String state;
	private String equipContainer;
	private String baseVoltage; 
	
	public Breaker(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		state = parseElement(element, STATE); 
		equipContainer = parseElement(element, EQUIP_CONTAINER); 
	}
	
	public String getName() { return name; }
		
	public String getState() { return state; } 
		
	public String getEquipContainer() { return equipContainer; } 
	
	public String getBaseVoltage() { return baseVoltage; } 

	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nState: " + state
				+ "\nEquipment Container: " + equipContainer + "\n");
	}
}
