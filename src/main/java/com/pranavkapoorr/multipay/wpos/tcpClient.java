package com.pranavkapoorr.multipay.wpos;


import akka.NotUsed;
import java.net.InetSocketAddress;
import akka.actor.*;
import akka.io.*;
import akka.util.ByteString;
import akka.io.Tcp.*;
import akka.stream.*;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Source;
import java.util.HashMap;
import java.util.concurrent.CompletionStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.pranavkapoorr.multipay.wpos.controller.MainController;

public class tcpClient extends AbstractActor {
           
	    private final ActorRef tcpActor;
	    private final InetSocketAddress remote;
	    private int senderId;
	    private final WebSocketSession session;
	    public static Props props(InetSocketAddress remote, WebSocketSession session) {
	        return Props.create(tcpClient.class, remote, session);
	    }

	    private tcpClient(InetSocketAddress cloudIpAndPort, WebSocketSession session) {
	        this.remote = cloudIpAndPort;
	        this.tcpActor = Tcp.get(getContext().system()).manager();
                this.tcpActor.tell(TcpMessage.connect(remote), getSelf());
            this.session = session;
	      }

    @Override
    public void preStart() throws Exception {
        System.out.println("starting tcp client!!"); 
      
    }
	    

		@Override
		public Receive createReceive() {
			return receiveBuilder()
				.match(CommandFailed.class, conn->{
					session.sendMessage(new TextMessage("Connection Failed"));
						getContext().stop(getSelf());
					})
				.match(Connected.class, conn->{
                        session.sendMessage(new TextMessage("Connected"));
			            getSender().tell(TcpMessage.register(getSelf()), getSelf());
			            getContext().become(connected(getSender()));
			            
					})
                                .build();
		}
		 private Receive connected(ActorRef serverConnection) {
		        return receiveBuilder()
		               .match(Received.class, msg->{
		            	   String message = msg.data().utf8String();
		            	   			System.out.println("incoming from Link -> "+message);
		            	   			session.sendMessage(new TextMessage(message));
                                       //DataBase.getDataBaseReceiptMessage().put(senderId,message);
                                    
		               })
		               .match(String.class,msg->{
		            	   			System.out.println("outgoing string to Link -> " +msg);
                                    serverConnection.tell(TcpMessage.write(ByteString.fromString(msg)), getSelf());
                		})
		               .match(ConnectionClosed.class, closed->{
		            	   session.sendMessage(new TextMessage("Connection Closed"));
                                    getContext().stop(getSelf());
		               })
                                .match(CommandFailed.class, conn->{
                                     MainController.statusMessage=("connectin Failed:"+conn);
                                     MainController.receipt="";
                                     getContext().stop(getSelf());
                        	})
                                    .build();
		    }

    @Override
    public void postStop() throws Exception {
        System.out.println("stopping tcp client!!");//To change body of generated methods, choose Tools | Templates.
    }
                
			
	}


