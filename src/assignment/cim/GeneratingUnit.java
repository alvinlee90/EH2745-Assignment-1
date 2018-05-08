package assignment.cim;

import org.w3c.dom.Element;


public class GeneratingUnit extends BaseCIMClass{
	static final String NAME = "cim:IdentifiedObject.name";
	static final String MAX_P = "cim:GeneratingUnit.maxOperatingP"; 
	static final String MIN_P = "cim:GeneratingUnit.minOperatingP";
	static final String EQUIP_CONTAINER = "cim:Equipment.EquipmentContainer"; 

	private String name;
	private String maxP;
	private String minP;
	private String equipContainer; 
	
	public GeneratingUnit(Element element) {
		parseRDF(element);
		name = parseElement(element, NAME); 
		maxP = parseElement(element, MAX_P); 
		minP = parseElement(element, MIN_P); 
		equipContainer = parseElement(element, EQUIP_CONTAINER); 
	}
	
	public String createTable() {
		return GENERATING_UNIT_ + " (RDFID VARCHAR(50) NOT NULL, NAME VARCHAR(50), MAX_P FLOAT, "
				+ "MIN_P FLOAT, EQUIPMENT_CONTAINER_ID VARCHAR(50), PRIMARY KEY(RDFID))"; 
	}
	
	public String getName() { return name; }
	
	public String getMaxP() { return maxP; } 
	
	public String getMinP() { return minP; } 
	
	public String getEquipContainer() { return equipContainer; } 

	public String getElement() { return GENERATING_UNIT_; }
	
	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nMax P: " + maxP + 
				"\nMin P: " + minP + "\nEquipment Container: " + equipContainer + "\n");
	}
}
