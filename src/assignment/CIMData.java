package assignment;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import assignment.cim.*;


public class CimData extends CimConsts {
	// CIM classes 		
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

	public CimData (String filepath) {
		try {		
			File file = new File(filepath);	
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(file);
			
			doc.getDocumentElement().normalize();
			
			for (String cimClass : CIM_CLASSES) {
//				System.out.println("Parsing data for " + cimClass + "...\n");
				
				NodeList nodeList = doc.getElementsByTagName(cimClass);

				for (int i = 0; i < nodeList.getLength(); i++) {
					Element element = (Element) nodeList.item(i);
					
					addElement(element, cimClass);
				}
				
//				printCIMData(cimClass);
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
			default:
				System.err.println("Error: Incorrect CIM object");
		}
	}
	
	public String[] createTables() {		
		return new String[] {baseVoltage_.get(0).createTable(),
							 substation_.get(0).createTable(),
							 voltageLevel_.get(0).createTable(),
							 generatingUnit_.get(0).createTable(),
							 regulatingControl_.get(0).createTable(),
							 powerTrans_.get(0).createTable(),
							 energyConsumer_.get(0).createTable(),
							 powerTransEnd_.get(0).createTable(),
							 breaker_.get(0).createTable(),
							 ratioTapChanger_.get(0).createTable(),
							 syncMachine_.get(0).createTable()};
	}
	
	public ArrayList<String> insertBaseVoltage() {
		ArrayList<String> command = new ArrayList<String>(); 
		
		// Insert all BaseVoltage elements
		for (BaseVoltage object : baseVoltage_) {
			command.add(object.insertTable()); 
		}

		return command; 
	}
	
	public ArrayList<String> insertSubstation() {
		ArrayList<String> command = new ArrayList<String>(); 
		
		// Insert all Substation elements
		for (Substation object : substation_) {
			command.add(object.insertTable()); 
		}
		
		return command; 
	}
	
	public ArrayList<String> insertVoltageLevel() {
		ArrayList<String> command = new ArrayList<String>(); 
		
		// Insert all VoltageLevel elements
		for (VoltageLevel object : voltageLevel_) {
			command.add(object.insertTable()); 
		}
		
		return command; 
	}
	
	public ArrayList<String> insertGeneratingUnit() {
		ArrayList<String> command = new ArrayList<String>(); 
		
		// Insert all GeneratingUnit elements
		for (GeneratingUnit object : generatingUnit_) {
			command.add(object.insertTable()); 
		}
		
		return command; 
	}
	
	public ArrayList<String> insertSyncMachine() {
		ArrayList<String> command = new ArrayList<String>(); 
			
		// Insert all SyncMachine elements
		for (SyncMachine object : syncMachine_) {
			command.add(object.insertTable()); 
		}
		
		return command; 
	}
	
	public ArrayList<String> insertRegulatingControl() {
		ArrayList<String> command = new ArrayList<String>(); 
	
		// Insert all RegulatingControl elements
		for (RegulatingControl object : regulatingControl_) {
			command.add(object.insertTable()); 
		}		
		
		return command; 
	}

	public ArrayList<String> insertPowerTransformer() {
		ArrayList<String> command = new ArrayList<String>(); 
			
		// Insert all PowerTransformer elements
		for (PowerTransformer object : powerTrans_) {
			command.add(object.insertTable()); 
		}	
		
		return command;
	}
		

	public ArrayList<String> insertEnergyConsumer() {
		ArrayList<String> command = new ArrayList<String>(); 
						
		// Insert all EnergyConsumer elements
		for (EnergyConsumer object : energyConsumer_) {
			command.add(object.insertTable()); 
		}	
	
		return command;
	}
	
	public ArrayList<String> insertPowerTransformerEnd() {
		ArrayList<String> command = new ArrayList<String>(); 
			
		// Insert all PowerTransformerEnd elements
		for (PowerTransformerEnd object : powerTransEnd_) {
			command.add(object.insertTable()); 
		}		
		
		return command;
	}
	
	public ArrayList<String> insertBreaker() {
		ArrayList<String> command = new ArrayList<String>(); 
				
		// Insert all Breaker elements
		for (Breaker object : breaker_) {
			command.add(object.insertTable()); 
		}	
		
		return command;
	}
	
	public ArrayList<String> insertRatioTapChanger() {
		ArrayList<String> command = new ArrayList<String>(); 		

		// Insert all Breaker elements
		for (RatioTapChanger object : ratioTapChanger_) {
			command.add(object.insertTable()); 
		}	
		
		return command;
	}
	
	public void printCIMData(String object) {
		switch (object) {
		case BASE_VOLTAGE: 
			for (BaseVoltage tmp : baseVoltage_) {
				tmp.printData();
			}
			break;
		case SUBSTATION: 
			for (Substation tmp : substation_) {
				tmp.printData();
			}
			break;
		case VOLTAGE_LEVEL: 
			for (VoltageLevel tmp : voltageLevel_) {
				tmp.printData();
			}
		  	break;
		case GENERATING_UNIT: 
			for (GeneratingUnit tmp : generatingUnit_) {
				tmp.printData();
			}
			break;
		case SYNC_MACHINE: 
			for (SyncMachine tmp : syncMachine_) {
				tmp.printData();
			}
			break;
		case REG_CONTROL: 
			for (RegulatingControl tmp : regulatingControl_) {
				tmp.printData();
			}
			break;
		case POWER_TRANS: 
			for (PowerTransformer tmp : powerTrans_) {
				tmp.printData();
			}
			break;
		case ENERGY_CONSUMER: 
			for (EnergyConsumer tmp : energyConsumer_) {
				tmp.printData();
			}
			break;
		case POWER_TRANS_END: 
			for (PowerTransformerEnd tmp : powerTransEnd_) {
				tmp.printData();
			}
			break;
		case BREAKER: 
			for (Breaker tmp : breaker_) {
				tmp.printData();
			}
			break;
		case RATIO_TAP: 
			for (RatioTapChanger tmp : ratioTapChanger_) {
				tmp.printData();
			}
			break;
		default:
			System.err.println("Error: Incorrect CIM object");
		}
	}
}
