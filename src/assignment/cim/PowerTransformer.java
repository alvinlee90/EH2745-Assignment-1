package assignment.cim;

import org.w3c.dom.Element;


public class PowerTransformer extends BaseCIMClass{
	static final String NAME = "cim:IdentifiedObject.name";
	static final String EQUIP_CONTAINER = "cim:Equipment.EquipmentContainer"; 

	private String name;
	private String equipContainer; 
	
	public PowerTransformer(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		equipContainer = parseElement(element, EQUIP_CONTAINER); 
	}
	
	public String getName() { return name; }
	
	public String getEquipContainer() { return equipContainer; } 

	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nEquipment Container: " + equipContainer + "\n");
	}
}
