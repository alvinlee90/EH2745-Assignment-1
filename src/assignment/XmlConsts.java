package assignment;

public class XmlConsts {
	protected static final String BASE_VOLTAGE = "cim:BaseVoltage"; 
	protected static final String SUBSTATION = "cim:Substation"; 
	protected static final String VOLTAGE_LEVEL = "cim:VoltageLevel"; 
	protected static final String GENERATING_UNIT = "cim:GeneratingUnit"; 
	protected static final String SYNC_MACHINE = "cim:SynchronousMachine"; 
	protected static final String REG_CONTROL = "cim:RegulatingControl"; 
	protected static final String POWER_TRANS = "cim:PowerTransformer"; 
	protected static final String ENERGY_CONSUMER = "cim:EnergyConsumer"; 
	protected static final String POWER_TRANS_END = "cim:PowerTransformerEnd"; 
	protected static final String BREAKER = "cim:Breaker"; 
	protected static final String RATIO_TAP = "cim:RatioTapChanger"; 
	
	protected static final String BUSBAR = "cim:BusbarSection"; 
	protected static final String CONNECT_NODE = "cim:ConnectivityNode"; 
	protected static final String TERMINAL = "cim:Terminal"; 
	protected static final String LINE = "cim:ACLineSegment"; 
	protected static final String SHUNT = "cim:LinearShuntCompensator"; 
	
	// Array of all the CIM classes 
	protected static final String[] CIM_CLASSES = {BASE_VOLTAGE, 
		  									       SUBSTATION,
		 									       VOLTAGE_LEVEL,
		 									       GENERATING_UNIT,
		 									       SYNC_MACHINE,
		 									       REG_CONTROL,
		 									       POWER_TRANS,
											       ENERGY_CONSUMER,
											       POWER_TRANS_END,
											       BREAKER,
											       RATIO_TAP,
											       BUSBAR,
											       CONNECT_NODE,
											       TERMINAL,
											       LINE,
											       SHUNT};
}
