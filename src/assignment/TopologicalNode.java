package assignment;

public class TopologicalNode {
	private String name; 
	private String rdfID; 
	
	public TopologicalNode() {}
	
	public TopologicalNode(String nodeName, String nodeID) {
		name = nodeName; 
		rdfID = nodeID; 
	}
	
	public String getName() { return name; }
	
	public String getID () { return rdfID; }
}
