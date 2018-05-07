package assignment.main;

import java.util.ArrayList;
import java.util.Arrays;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class DataEQ extends ParseXML {
	// CIM classes 		
	public static void main(String[] args) {
		try {
			Document doc = getDocument("Assignment_EQ_reduced.xml"); 
			
			// Extract the required CIM classes from the EQ file
			ArrayList<String> cimClasses = new ArrayList<String>(
											Arrays.asList(//BASE_VOLTAGE, 
//													      SUBSTATION,
//													      VOLTAGE_LEVEL,
//													      GENERATING_UNIT,
													      SYNC_MACHINE//,
//													      REG_CONTROL,
//													      POWER_TRANS,
//													      ENERGY_CONSUMER,
//													      POWER_TRANS_END,
//													      BREAKER,
//													      RATIO_TAP
													      ));
			
			for (String cimClass : cimClasses) {
				NodeList nodeList = doc.getElementsByTagName(cimClass);
							
				switch (cimClass) {
					case BASE_VOLTAGE: 
						System.out.println(BASE_VOLTAGE);
						extractBaseV(nodeList);
						break;
					case SUBSTATION: 
						System.out.println(SUBSTATION);
						extractSubstation(nodeList); 
						break;
					case VOLTAGE_LEVEL: 
						System.out.println(VOLTAGE_LEVEL);
						extractVoltageLvl(nodeList); 
					  	break;
					case GENERATING_UNIT: 
						System.out.println(GENERATING_UNIT);
						extractGenUnit(nodeList); 
						break;
					case SYNC_MACHINE: 
						System.out.println(SYNC_MACHINE);
						extractSyncMachine(nodeList); 
						break;
					case REG_CONTROL: 
						System.out.println(REG_CONTROL);
						extractRegControl(nodeList);
						break;
					case POWER_TRANS: 
						System.out.println(POWER_TRANS);
						extractPowerTrans(nodeList); 
						break;
					case ENERGY_CONSUMER: 
						System.out.println(ENERGY_CONSUMER);
						extractEnergyCons(nodeList); 
						break;
					case POWER_TRANS_END: 
						System.out.println(POWER_TRANS_END);
						extractPowerTransEnd(nodeList); 
						break;
					case BREAKER: 
						System.out.println(BREAKER);
						extractBreaker(nodeList); 
						break;
					case RATIO_TAP: 
						System.out.println(RATIO_TAP);
						extractRatioTap(nodeList); 
						break;
					default:
						System.err.println("Error: Incorrect CIM object");
				}
			}						
		}

		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void extractBaseV (NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			
			// Extract element data 
			String rdfID = element.getAttribute("rdf:ID");
			String nominalV = element.getElementsByTagName("cim:BaseVoltage.nominalVoltage")
					.item(0).getTextContent();
		}
	}
	
	public static void extractSubstation (NodeList nodeList) {
		String region = null; 

		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			
			// Extract sub-station data 
			String name = extractName(element);

//			String region = element.getElementsByTagName("cim:Substation.Region").item(0)
//					.getAttributes().item(0).getTextContent();
			
//			NodeList something = element.getElementsByTagName("cim:Substation.Region");
			
			System.out.println(name + "\n" + "Region: " + region + "\n");
		}
	}
	
	public static void extractVoltageLvl (NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			
			// Extract voltage level data 
			String name = extractName(element);
			String substation = element.getElementsByTagName("cim:VoltageLevel.Substation").item(0)
					.getAttributes().item(0).toString();
			String baseVolt = element.getElementsByTagName("cim:VoltageLevel.BaseVoltage").item(0)
					.getAttributes().item(0).toString();
			
			System.out.println(name + "\nSubstation: " + substation + "\nBase Voltage: " + baseVolt + "\n");
		}
	}
	
	public static void extractGenUnit (NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			
			// Extract generating unit data 
			String name = extractName(element);
			String maxP = element.getElementsByTagName("cim:GeneratingUnit.maxOperatingP").item(0).toString();
			String minP = element.getElementsByTagName("cim:GeneratingUnit.minOperatingP").item(0).toString();
			
			System.out.println(name + "\nMax P: " + maxP + "\nMin P: " + minP + "\n");
		}
	}
	
	public static void extractSyncMachine (NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			
			// Extract synchronous machine data 
			String name = extractName(element);
			String ratedS = element.getElementsByTagName("cim:RotatingMachine.ratedS").item(0).getTextContent();
			String genUnit = element.getElementsByTagName("cim:RotatingMachine.GeneratingUnit").item(0).getAttributes().item(0).getTextContent();
			String regControl = element.getElementsByTagName("cim:RegulatingCondEq.RegulatingControl").item(0).getAttributes().item(0).getTextContent();
			String eqContainer = element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0).getAttributes().item(0).getTextContent();
			String baseVolt = element.getElementsByTagName("cim:VoltageLevel.BaseVoltage").item(0)
					.getAttributes().item(0).toString();

			System.out.println(baseVolt);
//			System.out.println(name + "\nRated S: " + ratedS + "\nGenerating Unit: " + genUnit + "\nRegulating Control: " 
//					+ regControl + "\nEquipment Container: " + eqContainer + "\n");
		}
	}
	
	public static void extractRegControl (NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			
			// Extract synchronous machine data 
			String name = extractName(element);

			System.out.println(name + "\n");
		}
	}
	
	public static void extractPowerTrans (NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			
			// Extract synchronous machine data 
			String name = extractName(element);
			String eqContainer = element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0).getAttributes().item(0).getTextContent();

			System.out.println(name + "\nEquipment Container: " + eqContainer + "\n");
		}
	}
	
	public static void extractEnergyCons (NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			
			// Extract synchronous machine data 
			String name = extractName(element);
			String eqContainer = element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0).getAttributes().item(0).getTextContent();

			System.out.println(name + "\nEquipment Container: " + eqContainer + "\n");
		}
	}
	
	public static void extractPowerTransEnd (NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);

			// Extract synchronous machine data 
			String name = extractName(element); 
			String r = element.getElementsByTagName("cim:PowerTransformerEnd.r").item(0).getTextContent();
			String x = element.getElementsByTagName("cim:PowerTransformerEnd.x").item(0).getTextContent();
			String transformer = element.getElementsByTagName("cim:PowerTransformerEnd.PowerTransformer").item(0).getAttributes().item(0).getTextContent();
			String baseVoltage = element.getElementsByTagName("cim:TransformerEnd.BaseVoltage").item(0).getAttributes().item(0).getTextContent();

			System.out.println(name + "\nr: " + r + "\n" + "x: " + x + "\nTransformer: " + transformer + 
					"\nBase Voltage: " + baseVoltage + "\n");
		}
	}
	
	public static void extractBreaker (NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);

			// Extract synchronous machine data 
			String name = extractName(element);
			String eqContainer = element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0).getAttributes().item(0).getTextContent();
			
			System.out.println(name + "\nEquipment Container: " + eqContainer + "\n");
		}
	}
	
	public static void extractRatioTap (NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);

			// Extract synchronous machine data 
			String name = extractName(element); 
			
			System.out.println(name + "\n");
		}
	}
}
