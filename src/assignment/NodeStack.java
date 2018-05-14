package assignment;

import java.util.ArrayList;

/** Class to represent a stack of NodeTraverse; for traversing through the network. 
 * 
 * @author Alvin Lee
 *
 */

public class NodeStack {
	private NodeTraverse cnNode; 
	private ArrayList<NodeTraverse> ceStack = new ArrayList<NodeTraverse>();
	
	public NodeStack() {}
	
	public NodeStack(NodeTraverse node, ArrayList<NodeTraverse> stack) {
		cnNode = node;
		ceStack.addAll(stack); 
	}
	
	public String getID() { return cnNode.getID(); }
	
	public NodeTraverse getNode() { return cnNode; }
	
	public ArrayList<NodeTraverse> getStack() { return ceStack; }
	
	public ArrayList<String> getList() { return cnNode.getList(); }
	
	public void printStack() {
		for (NodeTraverse ce : ceStack) {
			ce.print();
		}
	}
}
