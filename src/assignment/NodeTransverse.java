package assignment;

import java.util.ArrayList;

public class NodeTransverse {
	private String rdfID;
	private String nodeType; 
	private String conductType;
	private ArrayList<String> terminalList; 
	
	public NodeTransverse() {}
	
	public String getID() { return rdfID; }
	
	public String getNodeType() { return nodeType; }
	
	public String getConductType() { return conductType; }
	
	public ArrayList<String> getList() { return terminalList; }
	
	public int getTerminalLength() { return terminalList.size(); }
}
