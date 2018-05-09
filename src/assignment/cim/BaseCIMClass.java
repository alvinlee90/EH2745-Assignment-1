package assignment.cim;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import assignment.CimConsts;

public class BaseCimClass extends CimConsts {	
	protected static final String RDF_ID_ = "RDFID"; 

	protected static final String STRING = "VARCHAR(50)";
	protected static final String FLOAT = "FLOAT"; 
	protected static final String INT = "INT"; 
	
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
