package assignment;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import assignment.cim.*;


public class CimData extends XmlConsts {
	// Required CIM classes 	
	private ArrayList <BaseVoltage> baseVoltage_ = new ArrayList<BaseVoltage>();
	private ArrayList <Substation> substation_ = new ArrayList<Substation>();
	private ArrayList <VoltageLevel> voltageLevel_ = new ArrayList<VoltageLevel>();
	private ArrayList <GeneratingUnit> generatingUnit_ = new ArrayList<GeneratingUnit>();
	private ArrayList <SyncMachine> syncMachine_ = new ArrayList<SyncMachine>();
	private ArrayList <RegulatingControl> regulatingControl_ = new ArrayList<RegulatingControl>();
	private ArrayList <PowerTransformer> powerTrans_ = new ArrayList<PowerTransformer>();
	private ArrayList <EnergyConsumer> energyConsumer_ = new ArrayList<EnergyConsumer>();
	private ArrayList <PowerTransformerEnd> powerTransEnd_ = new ArrayList<PowerTransformerEnd>();
	private ArrayList <Breaker> breaker_ = new ArrayList<Breaker>();
	private ArrayList <RatioTapChanger> ratioTapChanger_ = new ArrayList<RatioTapChanger>();
	
	// Required CIM class for Y-matrix 
	private ArrayList <BusbarSection> busbar_ = new ArrayList<BusbarSection>(); 
	private ArrayList <ConnectivityNode> connectNode_ = new ArrayList<ConnectivityNode>(); 
	private ArrayList <Terminal> terminal_ = new ArrayList<Terminal>(); 
	private ArrayList <Line> line_ = new ArrayList<Line>(); 
	private ArrayList <Shunt> shunt_ = new ArrayList<Shunt>(); 

	public CimData (String filepath) {
		try {		
			// Parse the specified file
			File file = new File(filepath);	
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(file);
			
			doc.getDocumentElement().normalize();
			
			// For each 
			for (String cimClass : CIM_CLASSES) {
				NodeList nodeList = doc.getElementsByTagName(cimClass);

				for (int i = 0; i < nodeList.getLength(); i++) {
					Element element = (Element) nodeList.item(i);
					
					addElement(element, cimClass);
				}
			}					
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	private void addElement(Element element, String object) {
		switch (object) {
			case BASE_VOLTAGE: 
				baseVoltage_.add(new BaseVoltage(element));
				break;
			case SUBSTATION: 
				substation_.add(new Substation(element)); 
				break;
			case VOLTAGE_LEVEL: 
				voltageLevel_.add(new VoltageLevel(element)); 
			  	break;
			case GENERATING_UNIT: 
				generatingUnit_.add(new GeneratingUnit(element));
				break;
			case SYNC_MACHINE: 
				syncMachine_.add(new SyncMachine(element)); 
				break;
			case REG_CONTROL: 
				regulatingControl_.add(new RegulatingControl(element));
				break;
			case POWER_TRANS: 
				powerTrans_.add(new PowerTransformer(element));
				break;
			case ENERGY_CONSUMER: 
				energyConsumer_.add(new EnergyConsumer(element));
				break;
			case POWER_TRANS_END: 
				powerTransEnd_.add(new PowerTransformerEnd(element)); 
				break;
			case BREAKER: 
				breaker_.add(new Breaker(element)); 
				break;
			case RATIO_TAP: 
				ratioTapChanger_.add(new RatioTapChanger(element)); 
				break;
			case BUSBAR:
				busbar_.add(new BusbarSection(element)); 
				break; 
			case CONNECT_NODE:
				connectNode_.add(new ConnectivityNode(element)); 
				break; 
			case TERMINAL: 
				terminal_.add(new Terminal(element)); 
				break; 
			case LINE: 
				line_.add(new Line(element)); 
				break; 
			case SHUNT:
				shunt_.add(new Shunt(element)); 
				break;
			default:
				System.err.println("Error: Incorrect CIM object");
		}
	}
	
	// Function to return all the SQL queries to create all the  
	// tables for the database
	public ArrayList<String> CreateTables() {
		ArrayList<String> query = new ArrayList<String>();
		
		// Add SQL queries for creating tables to array (required objects)
		query.add(new BaseVoltage().createTable());
		query.add(new Substation().createTable());
		query.add(new VoltageLevel().createTable());
		query.add(new GeneratingUnit().createTable());
		query.add(new RegulatingControl().createTable());
		query.add(new PowerTransformer().createTable());
		query.add(new EnergyConsumer().createTable());
		query.add(new PowerTransformerEnd().createTable());
		query.add(new Breaker().createTable());
		query.add(new RatioTapChanger().createTable());
		query.add(new SyncMachine().createTable());
		
		// Add SQL queries for creating tables to array (for Y bus)
		query.add(new BusbarSection().createTable());
		query.add(new ConnectivityNode().createTable());
		query.add(new Terminal().createTable());
		query.add(new Line().createTable());
		query.add(new Shunt().createTable());

		return query; 
	}
	
	// Function to create SQL queries to insert all the rows for base voltage
	public ArrayList<String> insertBaseVoltage() {
		ArrayList<String> query = new ArrayList<String>(); 
		
		// Insert all BaseVoltage elements
		for (BaseVoltage object : baseVoltage_) {
			query.add(object.insertTable()); 
		}

		return query; 
	}
	
	// Function to create SQL queries to insert all the rows for sub*station
	public ArrayList<String> insertSubstation() {
		ArrayList<String> query = new ArrayList<String>(); 
		
		// Insert all Sub-station elements
		for (Substation object : substation_) {
			query.add(object.insertTable()); 
		}
		
		return query; 
	}
	
	// Function to create SQL queries to insert all the rows for voltage level
	public ArrayList<String> insertVoltageLevel() {
		ArrayList<String> query = new ArrayList<String>(); 
		
		// Insert all VoltageLevel elements
		for (VoltageLevel object : voltageLevel_) {
			query.add(object.insertTable()); 
		}
		
		return query; 
	}
	
	// Function to create SQL queries to insert all the rows for generating unit
	public ArrayList<String> insertGeneratingUnit() {
		ArrayList<String> query = new ArrayList<String>(); 
		
		// Insert all GeneratingUnit elements
		for (GeneratingUnit object : generatingUnit_) {
			query.add(object.insertTable()); 
		}
		
		return query; 
	}
	
	// Function to create SQL queries to insert all the rows for sync machine
	public ArrayList<String> insertSyncMachine() {
		ArrayList<String> query = new ArrayList<String>(); 
			
		// Insert all SyncMachine elements
		for (SyncMachine object : syncMachine_) {
			query.add(object.insertTable()); 
		}
		
		return query; 
	}
	
	// Function to create SQL queries to insert all the rows for regulating control
	public ArrayList<String> insertRegulatingControl() {
		ArrayList<String> query = new ArrayList<String>(); 
	
		// Insert all RegulatingControl elements
		for (RegulatingControl object : regulatingControl_) {
			query.add(object.insertTable()); 
		}		
		
		return query; 
	}

	// Function to create SQL queries to insert all the rows for power transformer
	public ArrayList<String> insertPowerTransformer() {
		ArrayList<String> query = new ArrayList<String>(); 
			
		// Insert all PowerTransformer elements
		for (PowerTransformer object : powerTrans_) {
			query.add(object.insertTable()); 
		}	
		
		return query;
	}
		
	// Function to create SQL queries to insert all the rows for energy consumer
	public ArrayList<String> insertEnergyConsumer() {
		ArrayList<String> query = new ArrayList<String>(); 
						
		// Insert all EnergyConsumer elements
		for (EnergyConsumer object : energyConsumer_) {
			query.add(object.insertTable()); 
		}	
	
		return query;
	}
	
	// Function to create SQL queries to insert all the rows for power transformer end
	public ArrayList<String> insertPowerTransformerEnd() {
		ArrayList<String> query = new ArrayList<String>(); 
			
		// Insert all PowerTransformerEnd elements
		for (PowerTransformerEnd object : powerTransEnd_) {
			query.add(object.insertTable()); 
		}		
		
		return query;
	}
	
	// Function to create SQL queries to insert all the rows for breaker
	public ArrayList<String> insertBreaker() {
		ArrayList<String> query = new ArrayList<String>(); 
				
		// Insert all Breaker elements
		for (Breaker object : breaker_) {
			query.add(object.insertTable()); 
		}	
		
		return query;
	}
	
	// Function to create SQL queries to insert all the rows for ratio tap changer
	public ArrayList<String> insertRatioTapChanger() {
		ArrayList<String> query = new ArrayList<String>(); 		

		// Insert all Breaker elements
		for (RatioTapChanger object : ratioTapChanger_) {
			query.add(object.insertTable()); 
		}	
		
		return query;
	}

	// Function to create SQL queries to insert all the rows for breaker
	public ArrayList<String> insertBusbar() {
		ArrayList<String> query = new ArrayList<String>(); 		

		// Insert all Breaker elements
		for (BusbarSection object : busbar_) {
			query.add(object.insertTable()); 
		}	
		
		return query;
	}
	
	// Function to create SQL queries to insert all the rows for connecting node
	public ArrayList<String> insertConnectNode() {
		ArrayList<String> query = new ArrayList<String>(); 		

		// Insert all Breaker elements
		for (ConnectivityNode object : connectNode_) {
			query.add(object.insertTable()); 
		}	
		
		return query;
	}
	
	// Function to create SQL queries to insert all the rows for terminal
	public ArrayList<String> insertTerminal() {
		ArrayList<String> query = new ArrayList<String>(); 		

		// Insert all Breaker elements
		for (Terminal object : terminal_) {
			query.add(object.insertTable()); 
		}	
		
		return query;
	}
	
	// Function to create SQL queries to insert all the rows for ac line
	public ArrayList<String> insertLine() {
		ArrayList<String> query = new ArrayList<String>(); 		

		// Insert all Breaker elements
		for (Line object : line_) {
			query.add(object.insertTable()); 
		}	
		
		return query;
	}
	
	// Function to create SQL queries to insert all the rows for shunt
	public ArrayList<String> insertShunt() {
		ArrayList<String> query = new ArrayList<String>(); 		

		// Insert all Breaker elements
		for (Shunt object : shunt_) {
			query.add(object.insertTable()); 
		}	
		
		return query;
	}
}