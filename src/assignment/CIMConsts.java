package assignment;

public class CimConsts {
	protected static final String BASE_VOLTAGE_ = "BASE_VOLTAGE"; 
	protected static final String SUBSTATION_ = "SUBSTATION"; 
	protected static final String VOLTAGE_LEVEL_ = "VOLTAGE_LEVEL"; 
	protected static final String GENERATING_UNIT_ = "GENERATING_UNIT"; 
	protected static final String SYNC_MACHINE_ = "SYNC_MACHINE"; 
	protected static final String REG_CONTROL_ = "REG_CONTROL"; 
	protected static final String POWER_TRANS_ = "POWER_TRANS"; 
	protected static final String ENERGY_CONSUMER_ = "ENERGY_CONSUMER"; 
	protected static final String POWER_TRANS_END_ = "POWER_TRANS_END"; 
	protected static final String BREAKER_ = "BREAKER"; 
	protected static final String RATIO_TAP_ = "RATIO_TAP"; 
	
	protected static final String BUSBAR_ = "BUSBAR_SECTION";
	protected static final String CONNECT_NODE_ = "CONNECTIVITY_NODE"; 
	protected static final String TERMINAL_ = "TERMINAL"; 
	protected static final String LINE_ = "LINE"; 
	protected static final String SHUNT_ = "SHUNT"; 
	
	protected static final String RDF_ID_ = "RDFID"; 
	protected static final String NAME_ = "NAME";
	protected static final String NOMINAL_VOLTAGE_ = "NOMINAL_VOLTAGE";
	protected static final String STATE_ = "STATE";
	protected static final String EQUIP_CONTAINER_ID_ = "EQUIPMENT_CONTAINER_ID"; 
	protected static final String BASE_VOLTAGE_ID_ = "BASE_VOLTAGE_ID"; 
	protected static final String P_ = "P";
	protected static final String Q_ = "Q";
	protected static final String MAX_P_ = "MAX_P";
	protected static final String MIN_P_ = "MIN_P";
	protected static final String R_ = "R";
	protected static final String X_ = "X";
	protected static final String BCH_ = "BCH"; 
	protected static final String GCH_ = "GCH"; 
	protected static final String TRANSFORMER_ID_ = "TRANSFORMER_ID"; 
	protected static final String TARGET_VALUE_ = "TARGET_VALUE";
	protected static final String B_ = "B";
	protected static final String G_ = "G";
	protected static final String REGION_ID_ = "REGION_ID"; 
	protected static final String S_ = "S"; 
	protected static final String GEN_UNIT_ID_ = "GENERATING_UNIT_ID"; 
	protected static final String REG_CONTROL_ID_ = "REGULATING_CONTROL_ID"; 
	protected static final String CONDUCT_ID_ = "CONDUCTING_EQUIPMENT_ID";
	protected static final String CONNECT_ID_ = "CONNECTING_NODE_ID"; 
	protected static final String CONNECTED_ = "CONNECTED";
	protected static final String SUBSTATION_ID_ = "SUBSTATION_ID";

	protected static final String[] CIM_CLASSES_ = {BASE_VOLTAGE_, 
											        SUBSTATION_,
											        VOLTAGE_LEVEL_,
											        GENERATING_UNIT_,
											        SYNC_MACHINE_,
											        REG_CONTROL_,
											        POWER_TRANS_,
											        ENERGY_CONSUMER_,
											        POWER_TRANS_END_,
											        BREAKER_,
											        RATIO_TAP_,
											        BUSBAR_,
											        CONNECT_NODE_,
											        TERMINAL_,
											        LINE_,
											        SHUNT_};
	
	protected static final String[] CE_TYPES_ = {SYNC_MACHINE_,
												 BUSBAR_,
												 ENERGY_CONSUMER_,
												 POWER_TRANS_,
												 BREAKER_,
												 SHUNT_,
												 LINE_}; 
}
