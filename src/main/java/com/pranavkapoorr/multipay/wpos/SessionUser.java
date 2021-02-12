/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pranavkapoorr.multipay.wpos;

import akka.actor.ActorRef;
import java.util.Random;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author Pranav
 */
@Component
public class SessionUser {
    private int userId;
    public void setUserId(int userId) {
		this.userId = userId;
	}

	private String statusMessage="";
    public void setTcpActor(ActorRef tcpActor) {
		this.tcpActor = tcpActor;
	}

	private String receipt;
    
    private ActorRef tcpActor;

    public int getUserId() {
        return userId;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getReceipt() {
        return receipt;
    }
    public ActorRef getTcpActor() {
        return tcpActor;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }
}
