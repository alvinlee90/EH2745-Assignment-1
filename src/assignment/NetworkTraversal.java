package assignment;

import java.util.ArrayList;
import java.util.ListIterator;

public class NetworkTraversal extends CimConsts {	
	private static final String CE = "CE_TYPE"; 
	private static final String CN = "CN_TYPE"; 
	private static final String TE = "TE_TYPE"; 

	private Database database; 
	
	private ArrayList<ArrayList<NodeTraverse>> networkBranch; 
	
	private ArrayList<String> untraversedTe; 
	
	public NetworkTraversal() {}
	
	public NetworkTraversal(Database data) {
		// Initialize database variable
		database = data; 
		
		networkBranch =  new ArrayList<ArrayList<NodeTraverse>>(); 
		
		// Initialize list of all connected terminals 
		untraversedTe = database.selectSQL(TERMINAL_, CONNECTED_, "true"); 
		
		// Traverse network
		traverseNetwork();
		
	}
	
	public void traverseNetwork() {
		// Get all the RDF id of the bus-bar sections
		ArrayList<String> busbarIDs = database.getID(BUSBAR_); 
		
		// Loop through all bus-bars (end-device) to find branches 
		for (String busbarID : busbarIDs) {
			// Initialize empty connectivity node stack
			ArrayList<NodeStack> cnStack = new ArrayList<NodeStack>();
			ArrayList<NodeTraverse> ceStack = new ArrayList<NodeTraverse>(); 

//			System.out.println("Checking Busbar: " + busbarID);

			// Initialize the previous NodeTraverse for the [bus-bar]
			NodeTraverse prevNode = initialiseNode(busbarID, CE, BUSBAR_); 
						
			// Initialize the current NodeTraverse for connected [terminal]
			NodeTraverse currentNode = initialiseNode(prevNode.getList().get(0), TE);
			
			// Initialize the next node
			NodeTraverse nextNode = new NodeTraverse(); 
			
			// Add conducting equipment to CE stack
			ceStack.add(prevNode); 
			
			Boolean traverse = true; 
			
			// Main traverse loop
			while (traverse) {					
				// Find next node
				nextNode = findNext(currentNode, prevNode); 
//				System.out.println("-------------------------------");
//				System.out.println("PreviousNode");
//				prevNode.print();
//				
//				System.out.println("CurrentNode");
//				currentNode.print();
//				
//				System.out.println("NextNode");
//				if (nextNode != null) {
//					nextNode.print();
//				}

				// Check conditions for network traverse
				switch(currentNode.getNodeType()) {
					// -------------- Terminal Case -------------- 
					case(TE): 
						// Remove terminal from un-visited terminal list
						untraversedTe.remove(currentNode.getID()); 

						// Update current node as next node 
						prevNode = currentNode; 
						currentNode = nextNode; 
						break;
					// -------------- Connecting Node Case -------------- 
					case(CN):
						// If there is no un-traversed terminal remaining, publish the CE stack 
						// and mark the next node as the CN on top of the CN stack
						if (nextNode == null) {							
							networkBranch.add((ArrayList<NodeTraverse>) ceStack.clone());
							
							// Update current node first CN with an un-traversed terminal and 
							// update the CN stack
							traverse = false;
							
							// Update current node first CN with an un-traversed terminal and 
							// update the CN stack
							for (ListIterator<NodeStack> itr = cnStack.listIterator(); itr.hasNext();) {
								NodeStack stack = itr.next(); 
								
								for (String terminal : stack.getList()) {
									if (untraversedTe.indexOf(terminal) != -1) {
										// Found connecting node with un-traversed terminals 
										// Update current node with CN node
										currentNode = stack.getNode();
										
										// Update CE stack with corresponding CE stack
										ceStack.clear();
										ceStack.addAll(stack.getStack());
																				
										// Continue traversing through the network
										traverse = true; 
//										itr.remove();
										break;
									}									
								}
								// Remove CN node with no un-traversed terminals
								itr.remove();
							}						
						}
						else {
							// Else, add CN to cnStack and update the current node as next node
							cnStack.add(new NodeStack(currentNode, ceStack));
						
							// Update current node as next node 
							prevNode = currentNode; 
							currentNode = nextNode;
						}
 
						break;
					// -------------- Conducting Equipment Case -------------- 
					case(CE):
						// Add to CE stack
						ceStack.add(currentNode); 
						
						// If there is no un-traversed terminal remaining or next node is open breaker,
						// or current node is a bus-bar section, publish the CE stack and mark 
						// the next node as next CN from the CN stack
						if (nextNode == null) {							
							networkBranch.add((ArrayList<NodeTraverse>) ceStack.clone());
							traverse = false; 

							// Update current node first CN with an un-traversed terminal and 
							// update the CN stack
							for (ListIterator<NodeStack> itr = cnStack.listIterator(); itr.hasNext();) {
								NodeStack stack = itr.next(); 
								
								for (String terminal : stack.getList()) {
									if (untraversedTe.indexOf(terminal) != -1) {
										// Found connecting node with un-traversed terminals 
										// Update current node with CN node
										currentNode = stack.getNode();
										
										// Update CE stack with corresponding CE stack
										ceStack.clear();
										ceStack.addAll(stack.getStack());
										
										// Continue traversing through the network
										traverse = true; 
//										itr.remove();
										break;
									}									
								}
								// Remove CN node with no un-traversed terminals
								itr.remove();
							}
						}
						else {
							// Update current node as next node 
							prevNode = currentNode; 
							currentNode = nextNode;
						} 
						break;
				}
			}
		}
	}
	
	public NodeTraverse findNext(NodeTraverse currentNode, NodeTraverse prevNode) {		
		switch(currentNode.getNodeType()) {
			// --------- Terminal case ---------
			case(TE):	
				// Check previous node
				switch(prevNode.getNodeType()) {
					case(CN): 
						// Find next conducting equipment
						ArrayList<String> conductID = database.selectSQL(TERMINAL_, 
																		 RDF_ID_, 
																		 currentNode.getID(),
																		 CONDUCT_ID_); 
					
						return initialiseNode(conductID.get(0), CE); 
					case(CE):
						// Find next connecting node
						ArrayList<String> connectID1 = database.selectSQL(TERMINAL_, 
																		  RDF_ID_, 
																		  currentNode.getID(),
																		  CONNECT_ID_); 
					
						return initialiseNode(connectID1.get(0), CN); 
					default:
						System.err.println("Error in finding next node to traverse [prevNode type]");
				}
				break;
			// --------- Connecting node case ---------
			case(CN):
				// Return next un-traversed terminal
				for (String terminal : currentNode.getList()) {
					if (untraversedTe.indexOf(terminal) != -1) {
						return initialiseNode(terminal, TE); 
					}
				}
			
				// Else return nothing 
				return null; 
			// --------- Conducting equipment case ---------
			case(CE):
				// Check if breaker
				if (currentNode.getCeType().equals(BREAKER_)) {
					// Check state of the breaker
					ArrayList<String> state = database.selectSQL(BREAKER_, 
																 RDF_ID_, 
																 currentNode.getID(),
																 STATE_); 
					
					// If breaker is open, return nothing
					if (state.get(0).equals("true")) {
						return null; 
					}
				}
							
				// Return next un-traversed terminal
				for (String terminal : currentNode.getList()) {
					if (untraversedTe.indexOf(terminal) != -1) {
						return initialiseNode(terminal, TE); 
					}
				}
				// Else return nothing 
				return null; 
			default:
				System.err.println("Error in finding next node to traverse [nextNode type]");				
		}
		return null; 
	}
		
	public NodeTraverse initialiseNode(String id, String... type) {
		// Check input for type (should be of length 1 or 2)
		// type[0] = type of the node 
		// type[1] = CE type of the node
		if (type.length > 2 || type.length < 1) {
			System.err.println("Error with intialising traverse node");
			return null; 
		}
		
		// Initialize node
		NodeTraverse node = new NodeTraverse(id, type[0]); 
		
		// Set CE type  
		if (type[0] == CE) {
			// Already defined CE type
			if (type.length == 2) {
				node.setCeType(type[1]);
			}
			// Undefined CE type
			else {
				for (String ceType : CE_TYPES_) {
					ArrayList<String> search = database.selectSQL(ceType, 
																 RDF_ID_, 
															 	 id,
															 	 RDF_ID_); 
					
					if (search.size() > 0) {
						// Found CE type
						node.setCeType(ceType); 
						break;
					}
				}
			}

		}
		
		// Add list of terminals for CE or CN types
		switch(type[0]) {
			case(CN):
				ArrayList<String> terminals = database.selectSQL(TERMINAL_, 
																 CONNECT_ID_, 
															 	 id,
															 	 RDF_ID_); 
				node.setTerminal(terminals);
				break;
			case(CE):
				ArrayList<String> terminals1 = database.selectSQL(TERMINAL_, 
																  CONDUCT_ID_, 
																  id,
															 	  RDF_ID_); 
				node.setTerminal(terminals1);
				break; 
		}
		
		return node; 
	}
	
	public void printBranches() {
		for (int i = 0; i < networkBranch.size(); i++ ) {
			System.out.println("Branch # " + i);
			
			for (NodeTraverse node : networkBranch.get(i)) {
				System.out.println(node.getID() + " " + node.getCeType());
			}
		}
	}
}
