package assignment.cim;

import org.w3c.dom.Element;


public class SyncMachine extends BaseCIMObject{
	static final String NAME = "cim:IdentifiedObject.name";
	static final String RATE_S = "cim:RotatingMachine.ratedS"; 
	static final String MACHINE_P = "cim:RotatingMachine.p";
	static final String MACHINE_Q = "cim:RotatingMachine.q"; 
	static final String GEN_UNIT = "cim:RotatingMachine.GeneratingUnit"; 
	static final String REG_CONTROL = "cim:RegulatingCondEq.RegulatingControl"; 
	static final String EQUIP_CONTAINER = "cim:Equipment.EquipmentContainer"; 
//	static final String BASE_VOLTAGE = 

	private String name;
	private String ratedS;
	private String machineP;
	private String machineQ;
	private String genUnit; 
	private String regControl;
	private String equipContainer;
	private String baseVoltage; 
	
	public SyncMachine(Element element) {
		rdfID = element.getAttribute("rdf:ID");
		name = getElement(element, NAME); 
		ratedS = getElement(element, RATE_S); 
		machineP = getElement(element, MACHINE_P); 
		machineQ = getElement(element, MACHINE_Q); 
		genUnit = getElement(element, GEN_UNIT); 
		regControl = getElement(element, REG_CONTROL); 
		equipContainer = getElement(element, EQUIP_CONTAINER); 
	}
	
	public String getName() { return name; }
	
	public String getRatedS() { return ratedS; } 
	
	public String getMachineP() { return machineP; } 
	
	public String getMachineQ() { return machineQ; } 
	
	public String getGenUnit() { return genUnit; } 
	
	public String getRegControl() { return regControl; } 

	public String getEquipContainer() { return equipContainer; } 
	
	public String getBaseVoltage() { return baseVoltage; } 

	public void printData() { 
		System.out.println("rdfID: " + rdfID + "\nName: " + name + "\nRated S: " + ratedS +
				"\nP: " + machineP + "\nQ: " + machineQ + "\nGenerating Unit: " 
				+ genUnit + "\nRegulating Control: " + regControl + "\nEquipment Container: " 
				+ equipContainer + "\n");
	}
}
