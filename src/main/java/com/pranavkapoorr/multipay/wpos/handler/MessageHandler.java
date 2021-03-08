package com.pranavkapoorr.multipay.wpos.handler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pranavkapoorr.multipay.wpos.model.IpsJson;
import com.pranavkapoorr.multipay.wpos.service.LinkService;
import com.pranavkapoorr.multipay.wpos.service.TcpClient;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;

@Component
public class MessageHandler extends TextWebSocketHandler{
	private ConcurrentHashMap<WebSocketSession,ActorRef> sessions = new ConcurrentHashMap<>();
	@Autowired
	private LinkService service;
	private final ObjectMapper mapper = new ObjectMapper();
	private final ActorSystem system = ActorSystem.create("webInterface");
	

	
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws InterruptedException, IOException {
		//System.err.println("sesion*************************** ->  "+session.getId() +  message.getPayload());
		
		String msg = message.getPayload();
		//for(WebSocketSession webSocketSession : sessions) {
			
			if(isValidJSON(msg, mapper)) {
				
				if(msg.contains("Start")){
					String ip = msg.substring(msg.indexOf(":\"")+2,msg.indexOf("-")).trim();
					String port = msg.substring(msg.indexOf("-")+1,msg.lastIndexOf("\""));
					ActorRef tcpSender = system.actorOf(TcpClient.props(new InetSocketAddress(ip, Integer.valueOf(port)),session));
					//ActorRef tcpSender = system.actorOf(tcpClient.props(new InetSocketAddress("192.168.86.60", 40001),session));
					sessions.put(session,tcpSender);
				}else if(msg.contains("Stop")){
					ActorRef tcpSender = sessions.get(session);
					tcpSender.tell(PoisonPill.getInstance(), ActorRef.noSender());//killing connection
				}else if(msg.contains("Payment")) {
					IpsJson json = mapper.readValue(msg, IpsJson.class);
					service.payment(json.getAmount(), json.getPedIp(), json.getPedPort(), json.getTransactionReference(), json.getPrintFlag(),sessions.get(session));
				}else if(msg.contains("Refund")) {
					IpsJson json = mapper.readValue(msg, IpsJson.class);
					service.refund(json.getAmount(), json.getPedIp(), json.getPedPort(), json.getTransactionReference(), json.getPrintFlag(),sessions.get(session));
				}else if(msg.contains("Reversal")) {
					IpsJson json = mapper.readValue(msg, IpsJson.class);
					service.reversal(json.getPedIp(), json.getPedPort(), json.getTransactionReference(), json.getPrintFlag(),sessions.get(session));
				}else if(msg.contains("FirstDll")) {
					IpsJson json = mapper.readValue(msg, IpsJson.class);
					service.firstDLL(json.getPedIp(), json.getPedPort(), json.getPrintFlag(),sessions.get(session));
				}else if(msg.contains("UpdateDll")) {
					IpsJson json = mapper.readValue(msg, IpsJson.class);
					service.updateDLL(json.getPedIp(), json.getPedPort(), json.getPrintFlag(),sessions.get(session));
				}else if(msg.contains("ProbePed")) {
					IpsJson json = mapper.readValue(msg, IpsJson.class);
					service.probePed(json.getPedIp(), json.getPedPort(), json.getPrintFlag(),sessions.get(session));
				}else if(msg.contains("PedBalance")) {
					IpsJson json = mapper.readValue(msg, IpsJson.class);
					service.xReport(json.getPedIp(), json.getPedPort(), json.getPrintFlag(),sessions.get(session));
				}else if(msg.contains("EndOfDay")) {
					IpsJson json = mapper.readValue(msg, IpsJson.class);
					service.zReport(json.getPedIp(), json.getPedPort(), json.getPrintFlag(),sessions.get(session));
				}else if(msg.contains("ReprintReceipt")) {
					IpsJson json = mapper.readValue(msg, IpsJson.class);
					service.reprintReceipt(json.getPedIp(), json.getPedPort(), json.getPrintFlag(),sessions.get(session));
				}else if(msg.contains("PedStatus")) {
					IpsJson json = mapper.readValue(msg, IpsJson.class);
					service.pedStatus(json.getPedIp(), json.getPedPort(), json.getPrintFlag(),sessions.get(session));
				}else if(msg.contains("LastTransactionStatus")) {
					IpsJson json = mapper.readValue(msg, IpsJson.class);
					service.lastTransStatus(json.getPedIp(), json.getPedPort(), json.getPrintFlag(),sessions.get(session));
				}
			//}
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session){
		//adding session to session list on connect
		//sessions.put(session,ActorRef.noSender());
		//System.err.println("session: "+session.getId());
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//removing session from session list on connection end
		if(sessions.get(session) != null) {
			sessions.get(session).tell(PoisonPill.getInstance(), ActorRef.noSender());//killing connection incase user refreshes page
		}
		sessions.remove(session);
	}
	
	public static boolean isValidJSON(String json, ObjectMapper mapper) throws IOException {
	    boolean valid = true;
	    try{ 
	        mapper.readTree(json);
	    } catch(JsonProcessingException e){
	        valid = false;
	    }
	    return valid;
	}
}
