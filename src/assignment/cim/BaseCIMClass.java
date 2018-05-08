package assignment.cim;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class BaseCIMClass {
	static final String RDF_ID = "rdf:ID";
	static final String RDF_ABOUT = "rdf:about"; 
	
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
	
	public String getID() { return rdfID; }
}
