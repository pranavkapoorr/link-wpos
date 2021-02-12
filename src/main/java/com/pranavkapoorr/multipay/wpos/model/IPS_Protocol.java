package com.pranavkapoorr.multipay.wpos.model;


import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IPS_Protocol {
	@JsonProperty
	private String printFlag ;
	@JsonProperty
	private String messageCode ;
	@JsonProperty
	private String amount;
	@JsonProperty
	private String terminalIp;
	@JsonProperty
	private String statusMessageIp;
	@JsonProperty
	private String GTbit;
	@JsonProperty
	private String statusMessagePort;
	protected void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}
	protected void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	protected void setAmount(String amount) {
		this.amount = amount;
	}
	protected void setTerminalIp(String terminalIp) {
		this.terminalIp = terminalIp;
	}
	protected void setStatusMessageIp(String statusMessageIp) {
		this.statusMessageIp = statusMessageIp;
	}
	protected void setGTbit(String gTbit) {
		GTbit = gTbit;
	}
	protected void setStatusMessagePort(String statusMessagePort) {
		this.statusMessagePort = statusMessagePort;
	}
	protected void setTerminalPort(String terminalPort) {
		this.terminalPort = terminalPort;
	}
	protected void setGTmessage(String gTmessage) {
		GTmessage = gTmessage;
	}
	@JsonProperty
	private String terminalPort;
	@JsonProperty
	private String GTmessage;
	
	public IPS_Protocol() {
	//empty Constructor
	}
	protected HashMap<String, String> getParsedMap(){
		final HashMap<String, String> map = new HashMap<String,String>();
		map.put("printFlag", printFlag);
		map.put("messageCode",messageCode);
		map.put("amount",amount);
		map.put("terminalIp",terminalIp);
		map.put("statusMessageIp",statusMessageIp);
		map.put("GTbit",GTbit);
		map.put("statusMessagePort",statusMessagePort);
		map.put("terminalPort",terminalPort);
		map.put("GTmessage",GTmessage);
		return map;
	}
}
