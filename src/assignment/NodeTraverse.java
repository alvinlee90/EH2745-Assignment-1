package assignment;

import java.util.ArrayList;

public class NodeTraverse {
	private String rdfID;
	private String nodeType; 
	private String ceType; 
	private ArrayList<String> terminalList = new ArrayList<String>(); 
	
	public NodeTraverse() {}
	
	// Constructor for 2 inputs (CN)
	public NodeTraverse(String id, String type) {
		rdfID = id; 
		nodeType = type; 
	}
	
	// Constructor for CE type
	public NodeTraverse(String id, String type, String ce) {
		rdfID = id; 
		nodeType = type; 
		ceType = ce; 
	}
	
	// Constructor for CN type with terminals
	public NodeTraverse(String id, String type, ArrayList<String> terminal) {
		rdfID = id; 
		nodeType = type; 
		terminalList = (ArrayList<String>) terminal.clone(); 
	}
	
	public void setCeType(String type) { ceType = type; }
	
	public void setTerminal(ArrayList<String> list) { terminalList = (ArrayList<String>) list.clone(); }
	
	public String getID() { return rdfID; }
	
	public String getNodeType() { return nodeType; }
	
	public String getCeType() { return ceType; }
		
	public ArrayList<String> getList() { return terminalList; }
	
	public int getTerminalLength() { return terminalList.size(); }
	
	public void print() {
		// Print information about the node
		System.out.println("ID: " + rdfID);
		System.out.println("Node type: " + nodeType);
		
		if (ceType != null) {
			System.out.println("CE type: " + ceType);
		}
		
//		if (terminalList != null) {
//			System.out.println("Terminal list: ");
//			
//			for (String print : terminalList) {
//				System.out.println(print);
//			}
//		}
		
		System.out.println("");
	}
}
