package assignment.cim;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class BaseCIMObject {
	static final String RDF_ID = "rdf:ID"; 
	
	protected String rdfID;
	
	public String getElement(Element element, String tagName) {
		NodeList nodeList = element.getElementsByTagName(tagName);
		
		// Check if any valid elements were found
		if (nodeList.getLength() > 0) {
			Node node = nodeList.item(0);
			
			// Check if it has attributes 
			if (node.hasAttributes()) {
				return node.getAttributes().item(0).getTextContent(); 
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
