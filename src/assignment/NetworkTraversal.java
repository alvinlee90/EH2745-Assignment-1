package assignment;

import java.util.ArrayList;

/** NetworkTraversal traverses through the network in order to identify
 * the bus branches. 
 * 
 * @author Alvin Lee
 *
 */

public class NetworkTraversal extends CimConsts {	
	private static final String CE = "CE_TYPE"; 
	private static final String CN = "CN_TYPE"; 
	private static final String TE = "TE_TYPE"; 

	private Database database; 
	
	private ArrayList<BusBranch> busBranch = new ArrayList<BusBranch>(); 
	
	private ArrayList<ArrayList<NodeTraverse>> networkBranch = new ArrayList<ArrayList<NodeTraverse>>(); ; 
	
	private ArrayList<String> untraversedTe = new ArrayList<String>(); 
	
	public NetworkTraversal() {}
	
	public NetworkTraversal(Database data) {
		// Initialize database variable
		database = data; 
						
		// Traverse network and get bus branches
		traverseNetwork(SHUNT_);
		findBranches(SHUNT_); 
		
		traverseNetwork(LINE_);
		findBranches(LINE_); 
		
//		traverseNetwork(POWER_TRANS_END_);
		
		// Print data
		busBranch.get(0).printTitle();
		for (BusBranch print : busBranch) {
			print.print();
		}
	}
	
	// Function to traverse through the network and return all possible paths
	private void traverseNetwork(String device) {		
		networkBranch.clear(); 
		
		// Get all the RDF id's
		ArrayList<String> initialIDs = database.getID(device); 
		
		// Loop through end-device to find branches 
		for (String initialID : initialIDs) {
			// Initialize empty connectivity node stack
			NodeStack cnStack = new NodeStack();
			ArrayList<NodeTraverse> ceStack = new ArrayList<NodeTraverse>(); 

			// Initialize the previous NodeTraverse for the [bus-bar]
			NodeTraverse prevNode = initialiseNode(initialID, CE); 
			NodeTraverse savedPrevNode = prevNode; 
			
			untraversedTe.clear();
			untraversedTe.addAll(database.selectSQL(TERMINAL_, CONNECTED_, "true")); 

//			NodeTraverse currentNode = initialiseNode(prevNode.getList().get(0), TE);
			for (String initialTerminal : prevNode.getList()) {
				
				prevNode = savedPrevNode; 
				
				// Initialize the current NodeTraverse for connected [terminal]
				NodeTraverse currentNode = initialiseNode(initialTerminal, TE);

				NodeTraverse nextNode = findNext(currentNode, prevNode); 

				// Add conducting equipment to CE stack
				ceStack.clear();
				ceStack.add(prevNode); 

				// Else, add CN to cnStack and update the current node as next node
				cnStack = new NodeStack(nextNode, ceStack);

				Boolean traverse = true; 
				
				// Main traverse loop
				while (traverse) {					
					// Find next node
					nextNode = findNext(currentNode, prevNode); 
						
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
								if (ceStack.size() > 1) {
									addBranch(ceStack);
//									networkBranch.add((ArrayList<NodeTraverse>) ceStack.clone());
								}
								
								traverse = false;
								
								// Update current node CN with an un-traversed terminal 
								for (String terminal : cnStack.getList()) {
									if (untraversedTe.indexOf(terminal) != -1) {
										// Found connecting node with un-traversed terminals 
										// Update current node with CN node
										currentNode = cnStack.getNode();
										
										// Update CE stack with corresponding CE stack
										ceStack.clear();
										ceStack.addAll(cnStack.getStack());
										
										// Continue traversing through the network
										traverse = true; 
										break;
									}									
								}	
							}
							else {							
								// Update current node as next node 
								prevNode = currentNode; 
								currentNode = nextNode;
							}
	 
							break;
						// -------------- Conducting Equipment Case -------------- 
						case(CE):
							// Add to CE stack
							if (currentNode.getCeType() != BREAKER_) {
								ceStack.add(currentNode); 
							}
							
							// If there is no un-traversed terminal remaining or next node is open breaker,
							// or current node is a bus-bar section, publish the CE stack and mark 
							// the next node as next CN from the CN stack
							if (nextNode == null) {		
								if (ceStack.size() > 1) {
									addBranch(ceStack);
								}
								
								traverse = false; 
								
								// Update current node first CN with an un-traversed terminal
								for (String terminal : cnStack.getList()) {
									if (untraversedTe.indexOf(terminal) != -1) {
										// Found connecting node with un-traversed terminals 
										// Update current node with CN node
										currentNode = cnStack.getNode();
										
										// Update CE stack with corresponding CE stack
										ceStack.clear();
										ceStack.addAll(cnStack.getStack());
										
										// Continue traversing through the network
										traverse = true; 
										break;
									}
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
	}
	
	// Function to find the next connected/valid node 
	private NodeTraverse findNext(NodeTraverse currentNode, NodeTraverse prevNode) {		
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
				// Check breaker
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
					
				if (currentNode.getCeType().equals(BUSBAR_)) {
					return null;
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
	
	// Function to initialise a traverse node for network traversing 
	private NodeTraverse initialiseNode(String id, String... type) {
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
				ArrayList<String> terminals1 = new ArrayList<String>(); 
			
				if (node.getCeType() == POWER_TRANS_END_) {
					terminals1 = database.selectSQL(POWER_TRANS_END_, 
													RDF_ID_, 
													id,
													TERMINAL_ID_); 

				}
				else {
					terminals1 = database.selectSQL(TERMINAL_, 
													CONDUCT_ID_, 
													id,
													RDF_ID_); 
				}
				node.setTerminal(terminals1);
				break; 
		}
		
		return node; 
	}
	
	public void addBranch(ArrayList<NodeTraverse> stack) {			
			networkBranch.add((ArrayList<NodeTraverse>) stack.clone());
	}
	
	// Function to get all the bus branches
	public void findBranches(String ceType) {
		switch(ceType) {
			case(SHUNT_):
				// Find Shunt -> Bus-bar
				for (ArrayList<NodeTraverse> branch : networkBranch) {
					if (branch.size() == 2) {
						if (branch.get(1).getCeType() == BUSBAR_) {
							String shuntName = database.selectSQL(SHUNT_, RDF_ID_, branch.get(0).getID(), NAME_).get(0);
							String busbarName = database.selectSQL(BUSBAR_, RDF_ID_, branch.get(1).getID(), NAME_).get(0); 

							busBranch.add(new BusBranch(branch.get(0).getID(),
														SHUNT_,
														shuntName,
														branch.get(1).getID(),
														BUSBAR_,
														busbarName)); 
						}
					}
				}
				break;
			case(LINE_): 
				// Find Busbar -> Line -> Busbar
				ArrayList<String> history = new ArrayList<String>();
			
				for (ArrayList<NodeTraverse> branch : networkBranch) {
					if (branch.size() == 2 && history.indexOf(branch.get(0).getID()) == -1) {
						for (ArrayList<NodeTraverse> compare : networkBranch) {
							if (compare.size() == 2) {
								// Same AC Line
								if (branch.get(0).getID() == compare.get(0).getID() &&
										branch.get(1).getID() != compare.get(1).getID()) {
									history.add(branch.get(0).getID()); 
									
									String busbarName1 = database.selectSQL(BUSBAR_, RDF_ID_, branch.get(1).getID(), NAME_).get(0);
									String busbarName2 = database.selectSQL(BUSBAR_, RDF_ID_, compare.get(1).getID(), NAME_).get(0); 

									BusBranch bBranch = new BusBranch(branch.get(1).getID(), 
												 					  BUSBAR_,
												 					  busbarName1,
												 					  compare.get(1).getID(),
												 					  BUSBAR_,
																	  busbarName2); 
									
									String lineName = database.selectSQL(LINE_, RDF_ID_, branch.get(0).getID(), NAME_).get(0); 
									
									bBranch.updateDevice(branch.get(0).getID(), lineName, LINE_);
									
									busBranch.add(bBranch); 

								}
							}
						}
					}
				}
				break;
		}
	}
	
	public void printBranches() {
		for (int i = 0; i < networkBranch.size(); i++ ) {
			System.out.println("\nBranch # " + i);
			
			for (NodeTraverse node : networkBranch.get(i)) {
				System.out.println(node.getID() + " " + node.getCeType());
			}
		}
	}
}
