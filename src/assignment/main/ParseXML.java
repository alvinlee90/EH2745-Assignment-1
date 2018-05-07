package assignment.main;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class ParseXML {
	// CIM classes 
	static final String BASE_VOLTAGE = "cim:BaseVoltage"; 
	static final String SUBSTATION = "cim:Substation"; 
	static final String VOLTAGE_LEVEL = "cim:VoltageLevel"; 
	static final String GENERATING_UNIT = "cim:GeneratingUnit"; 
	static final String SYNC_MACHINE = "cim:SynchronousMachine"; 
	static final String REG_CONTROL = "cim:RegulatingControl"; 
	static final String POWER_TRANS = "cim:PowerTransformer"; 
	static final String ENERGY_CONSUMER = "cim:EnergyConsumer"; 
	static final String POWER_TRANS_END = "cim:PowerTransformerEnd"; 
	static final String BREAKER = "cim:Breaker"; 
	static final String RATIO_TAP = "cim:RatioTapChanger"; 
	
	public static Document getDocument(String filePath) throws ParserConfigurationException, SAXException, IOException {
		File file = new File(filePath);		// EQ file
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		
		Document doc = dBuilder.parse(file);
		
		doc.getDocumentElement().normalize();
		
		return doc; 
	}
	
	public static String extractName(Element element) {
		String rdfID = element.getAttribute("rdf:ID");
		String name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
		
		String string = "rdfID: " + rdfID + "\nName: " + name; 
		
		return string;
	}
}
