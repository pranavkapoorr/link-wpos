package com.pranavkapoorr.multipay.wpos.service;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pranavkapoorr.multipay.wpos.app.Application;
import com.pranavkapoorr.multipay.wpos.model.IpsJson;
import akka.actor.ActorRef;

@Component
public class LinkService {
	
	public void payment(String amount,String pedIp,String pedPort, String GTmessage,String printFlag,ActorRef tcpSender, String timeout) throws JsonProcessingException {
			String msg = makeMessage(amount, pedIp, pedPort, GTmessage, printFlag, "Payment", timeout);
	       	tcpSender.tell(msg, Application.tcpSender);
	}
	public void refund(String amount,String pedIp,String pedPort, String GTmessage,String printFlag,ActorRef tcpSender, String timeout) throws JsonProcessingException {
		String msg = makeMessage(amount, pedIp, pedPort, GTmessage, printFlag, "Refund", timeout);
       	tcpSender.tell(msg, Application.tcpSender);
	}
	public void reversal(String pedIp,String pedPort, String GTmessage,String printFlag,ActorRef tcpSender, String timeout) throws JsonProcessingException {
		String msg = makeMessage(null, pedIp, pedPort, GTmessage, printFlag, "Reversal", timeout);
       	tcpSender.tell(msg, Application.tcpSender);
	}
	public void pedStatus(String pedIp,String pedPort,String printFlag,ActorRef tcpSender, String timeout) throws JsonProcessingException {
		String msg = makeMessage(null, pedIp, pedPort, null, printFlag, "PedStatus", timeout);
       	tcpSender.tell(msg, Application.tcpSender);
	}
	public void lastTransStatus(String pedIp,String pedPort,String printFlag,ActorRef tcpSender, String timeout) throws JsonProcessingException {
		String msg = makeMessage(null, pedIp, pedPort, null, printFlag, "LastTransactionStatus", timeout);
       	tcpSender.tell(msg, Application.tcpSender);
	}
	public void xReport(String pedIp,String pedPort,String printFlag,ActorRef tcpSender, String timeout) throws JsonProcessingException {
		String msg = makeMessage(null, pedIp, pedPort, null, printFlag, "PedBalance", timeout);
       	tcpSender.tell(msg, Application.tcpSender);
	}
	public void zReport(String pedIp,String pedPort,String printFlag,ActorRef tcpSender, String timeout) throws JsonProcessingException {
		String msg = makeMessage(null, pedIp, pedPort, null, printFlag, "EndOfDay", timeout);
       	tcpSender.tell(msg, Application.tcpSender);
	}
	public void firstDLL(String pedIp,String pedPort,String printFlag,ActorRef tcpSender, String timeout) throws JsonProcessingException {
		String msg = makeMessage(null, pedIp, pedPort, null, printFlag, "FirstDll", timeout);
       	tcpSender.tell(msg, Application.tcpSender);
	}
	public void updateDLL(String pedIp,String pedPort,String printFlag,ActorRef tcpSender, String timeout) throws JsonProcessingException {
		String msg = makeMessage(null, pedIp, pedPort, null, printFlag, "UpdateDll", timeout);
       	tcpSender.tell(msg, Application.tcpSender);
	}
	public void reprintReceipt(String pedIp,String pedPort,String printFlag,ActorRef tcpSender, String timeout) throws JsonProcessingException {
		String msg = makeMessage(null, pedIp, pedPort, null, printFlag, "ReprintReceipt", timeout);
       	tcpSender.tell(msg, Application.tcpSender);
	}
	public void probePed(String pedIp,String pedPort,String printFlag,ActorRef tcpSender, String timeout) throws JsonProcessingException {
		String msg = makeMessage(null, pedIp, pedPort, null, printFlag, "ProbePed", timeout);
       	tcpSender.tell(msg, Application.tcpSender);
	}
	private String makeMessage(String amount,String pedIp,String pedPort, String GTmessage,String printFlag, String operation, String timeout) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		IpsJson json = new IpsJson();
		if(amount != null) {
			json.setAmount(amount);
		}
		if(printFlag != null) {
			json.setPrintFlag(printFlag);
		}
		json.setPedIp(pedIp);
	    json.setPedPort(pedPort);
	    json.setOperationType(operation);
	   	if(GTmessage != null) {
	   		json.setTransactionReference(GTmessage);
	   	}
	   	json.setTimeOut(timeout);
		String msg = mapper.writeValueAsString(json);
		return msg;
	}
}
