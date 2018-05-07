package assignment.cim;

import org.w3c.dom.Element;


public class GeneratingUnit extends BaseCIMObject{
	static final String NAME = "cim:IdentifiedObject.name";
	static final String MAX_P = "cim:GeneratingUnit.maxOperatingP"; 
	static final String MIN_P = "cim:GeneratingUnit.minOperatingP";
	static final String EQUIP_CONTAINER = "cim:Equipment.EquipmentContainer"; 

	private String name;
	private String maxP;
	private String minP;
	private String equipContainer; 
	
	public GeneratingUnit(Element element) {
		rdfID = element.getAttribute("rdf:ID");
		name = getElement(element, NAME); 
		maxP = getElement(element, MAX_P); 
		minP = getElement(element, MIN_P); 
		equipContainer = getElement(element, EQUIP_CONTAINER); 
	}
	
	public String getName() { return name; }
	
	public String getMaxP() { return maxP; } 
	
	public String getMinP() { return minP; } 
	
	public String getEquipContainer() { return equipContainer; } 

	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nMax P: " + maxP + 
				"\nMin P: " + minP + "\nEquipment Container: " + equipContainer + "\n");
	}
}
