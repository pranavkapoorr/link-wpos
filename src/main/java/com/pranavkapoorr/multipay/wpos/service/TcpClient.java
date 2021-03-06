package com.pranavkapoorr.multipay.wpos.service;


import java.net.InetSocketAddress;
import java.sql.Timestamp;
import akka.actor.*;
import akka.io.*;
import akka.util.ByteString;
import akka.io.Tcp.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class TcpClient extends AbstractActor {
           
	    private final ActorRef tcpActor;
	    private final InetSocketAddress remote;
	    private final WebSocketSession session;
	    public static Props props(InetSocketAddress remote, WebSocketSession session) {
	        return Props.create(TcpClient.class, remote, session);
	    }

	    private TcpClient(InetSocketAddress cloudIpAndPort, WebSocketSession session) {
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
					session.sendMessage(new TextMessage("Connection Failed -> " + getTimeStamp()));
						getContext().stop(getSelf());
					})
				.match(Connected.class, conn->{
                        session.sendMessage(new TextMessage("Connected -> " + getTimeStamp()));
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
                                    
		               })
		               .match(String.class,msg->{
		            	   			System.out.println("outgoing string to Link -> " +msg);
                                    serverConnection.tell(TcpMessage.write(ByteString.fromString(msg)), getSelf());
                		})
		               .match(ConnectionClosed.class, closed->{
		            	   session.sendMessage(new TextMessage("Connection Closed -> " + getTimeStamp()));
                                    getContext().stop(getSelf());
		               })
                                .match(CommandFailed.class, conn->{
        
                                     getContext().stop(getSelf());
                        	})
                                    .build();
		    }

    @Override
    public void postStop() throws Exception {
    	session.sendMessage(new TextMessage("Connection Closed -> " + getTimeStamp()));
        System.out.println("stopping tcp client!!");//To change body of generated methods, choose Tools | Templates.
    }
    
    private String getTimeStamp() {
    	return new Timestamp(System.currentTimeMillis()).toString();
    }
			
}


