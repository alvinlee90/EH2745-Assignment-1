package assignment.cim;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class BaseCIMClass {
	protected static final String BASE_VOLTAGE_ = "BASE_VOLTAGE"; 
	protected static final String SUBSTATION_ = "SUBSTATION"; 
	protected static final String VOLTAGE_LEVEL_ = "VOLTAGE_LEVEL"; 
	protected static final String GENERATING_UNIT_ = "GENERATING_UNIT"; 
	protected static final String SYNC_MACHINE_ = "SYNC_MACHINE"; 
	protected static final String REG_CONTROL_ = "REG_CONTROL"; 
	protected static final String POWER_TRANS_ = "POWER_TRANS"; 
	protected static final String ENERGY_CONSUMER_ = "ENERGY_CONSUMER"; 
	protected static final String POWER_TRANS_END_ = "POWER_TRANS_END"; 
	protected static final String BREAKER_ = "BREAKER"; 
	protected static final String RATIO_TAP_ = "RATIO_TAP"; 
	
	protected static final String RDF_ID_ = "RDFID"; 
	
	protected static final String RDF_ID = "rdf:ID";
	protected static final String RDF_ABOUT = "rdf:about"; 
	
	protected String rdfID;
	
	public void parseRDF(Element element) {
		if (element.hasAttribute(RDF_ID)) {
			rdfID = element.getAttribute(RDF_ID);
		} 
		else if (element.hasAttribute(RDF_ABOUT)) {
			rdfID = element.getAttribute(RDF_ABOUT).substring(1);
		}
		else {
			System.out.println("Unable to find " + RDF_ID); 
		}
	}
	
	public String parseElement(Element element, String tagName) {
		NodeList nodeList = element.getElementsByTagName(tagName);
		
		// Check if any valid elements were found
		if (nodeList.getLength() > 0) {
			Node node = nodeList.item(0);
			
			// Check if it has attributes 
			if (node.hasAttributes()) {
				return node.getAttributes().item(0).getTextContent().substring(1); 
			}
			
			return node.getTextContent(); 
		}
		else {
			// No elements found
			return null;
		}
	}
	
	protected String insertSQL(String command, String duplicate, Boolean update) {
		if (update) {
			if (duplicate.endsWith(", ")) {
				duplicate = duplicate.substring(0, duplicate.length() - 2);
			}

			return "INSERT INTO " + command + duplicate;
		}
		else {
			return "INSERT IGNORE INTO " + command;
		}
	}
	
	public String getID() { return rdfID; }
}
