/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pranavkapoorr.multipay.wpos.app;

/**
 *
 * @author Pranav
 */

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import java.net.InetSocketAddress;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.pranavkapoorr.multipay.wpos.tcpClient;

@SpringBootApplication
@ComponentScan(basePackages="com.pranavkapoorr.multipay.wpos")
public class Application {
public static ActorRef tcpSender = null;
    public Application() {
        //ActorSystem system = ActorSystem.create("webInterface");
     
      //tcpSender   = system.actorOf(tcpClient.props(new InetSocketAddress("192.168.86.60", 40001)));
    }
 
    public static void main(String[] args) {
        
        SpringApplication.run(Application.class, args);
    }

}