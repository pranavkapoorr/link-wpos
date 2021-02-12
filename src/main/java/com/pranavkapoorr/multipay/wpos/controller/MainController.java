package com.pranavkapoorr.multipay.wpos.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pranav
 */
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pranavkapoorr.multipay.wpos.DataBase;
import com.pranavkapoorr.multipay.wpos.SessionUser;
import com.pranavkapoorr.multipay.wpos.app.Application;
import com.pranavkapoorr.multipay.wpos.model.IpsJson;
import com.pranavkapoorr.multipay.wpos.service.LinkService;
import java.net.InetSocketAddress;
import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@Scope("session")
public class MainController {
    //private final ActorRef tcpSender;
    public static volatile String statusMessage="";
    public static volatile String receipt="";
    //private final ObjectMapper mapper;
  //  private int userId=0;
    @Autowired
    private LinkService service;
    @Autowired
    private SessionUser user;
    @Autowired
    public DataBase dataBase;
   
    public MainController() {
        System.out.println("hahahahahahahahahahahahahahaqhahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahah");
        //this.mapper = new ObjectMapper();
        
    }
    
    
     @RequestMapping(value="/home")
     public ModelAndView MainPage(HttpSession session ) {
         ModelMap model = new ModelMap("name",user.getUserId());
       //  dataBase.getDataBase().put(user.getUserId(), user);
        return new ModelAndView("SIMULATORx",model);
        
     }
    @RequestMapping(value="/statusMessage", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String statusMessageFunction() {
             return "statusMessage"+"\n\n"+dataBase.getDataBaseStatusMessage().get(user.getUserId());
        
    }
    @RequestMapping(value="/receipt", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String receiptFunction() {
          return "receiptMessage"+"\n\n"+dataBase.getDataBaseReceiptMessage().get(user.getUserId());
    }
    
    @RequestMapping(value="/payment", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String payment(@RequestParam(value="terminalIp")String terminalIp,@RequestParam(value = "terminalPort")String terminalPort,
            @RequestParam(value="amount")String amount, @RequestParam(value="printFlag")String printFlag, @RequestParam(value="GTbit")String GTbit,@RequestParam(value="GTmessage",defaultValue = "none")String GTmessage) throws InterruptedException, JsonProcessingException{
       statusMessage="";
       receipt="";
       service.payment(amount, terminalIp, terminalPort, GTmessage, printFlag, Application.tcpSender);
        return "Payment";
    }
    
    @RequestMapping(value="/refund", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String refund(@RequestParam(value="terminalIp")String terminalIp,@RequestParam(value = "terminalPort")String terminalPort,
            @RequestParam(value="amount")String amount, @RequestParam(value="printFlag")String printFlag, @RequestParam(value="GTbit")String GTbit,@RequestParam(value="GTmessage",defaultValue = "none")String GTmessage) throws InterruptedException, JsonProcessingException{
       statusMessage="";
       receipt="";
       service.refund(amount, terminalIp, terminalPort, GTmessage, printFlag, Application.tcpSender);
        return "refund";
    }
 @RequestMapping(value="/reversal", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String reversal(@RequestParam(value="terminalIp")String terminalIp,@RequestParam(value = "terminalPort")String terminalPort,
            @RequestParam(value="amount")String amount, @RequestParam(value="printFlag")String printFlag, @RequestParam(value="GTbit")String GTbit,@RequestParam(value="GTmessage",defaultValue = "none")String GTmessage) throws InterruptedException, JsonProcessingException{
       statusMessage="";
       receipt="";
       service.reversal(terminalIp, terminalPort, GTmessage, printFlag, Application.tcpSender);
        return "reversal";
    }
    @RequestMapping(value="/dll", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String firstDll(@RequestParam(value="terminalIp")String terminalIp,@RequestParam(value = "terminalPort")String terminalPort,
            @RequestParam(value="amount")String amount, @RequestParam(value="printFlag")String printFlag, @RequestParam(value="GTbit")String GTbit,@RequestParam(value="GTmessage",defaultValue = "none")String GTmessage) throws InterruptedException, JsonProcessingException{
       statusMessage="";
       receipt="";
       service.firstDLL(terminalIp, terminalPort, printFlag, Application.tcpSender);
        return "firstDLL";
    }
    @RequestMapping(value="/xreport", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String xReport(@RequestParam(value="terminalIp")String terminalIp,@RequestParam(value = "terminalPort")String terminalPort,
            @RequestParam(value="amount")String amount, @RequestParam(value="printFlag")String printFlag, @RequestParam(value="GTbit")String GTbit,@RequestParam(value="GTmessage",defaultValue = "none")String GTmessage) throws InterruptedException, JsonProcessingException{
       statusMessage="";
       receipt="";
       service.xReport(terminalIp, terminalPort, printFlag, Application.tcpSender);
        return "xReport";
    }
    @RequestMapping(value="/zreport", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String zReport(@RequestParam(value="terminalIp")String terminalIp,@RequestParam(value = "terminalPort")String terminalPort,
            @RequestParam(value="amount")String amount, @RequestParam(value="printFlag")String printFlag, @RequestParam(value="GTbit")String GTbit,@RequestParam(value="GTmessage",defaultValue = "none")String GTmessage) throws InterruptedException, JsonProcessingException{
       statusMessage="";
       receipt="";
       service.zReport(terminalIp, terminalPort, printFlag, Application.tcpSender);
        return "zReport";
    }
    @RequestMapping(value="/terminalstatust", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String terminalStatus(@RequestParam(value="terminalIp")String terminalIp,@RequestParam(value = "terminalPort")String terminalPort,
            @RequestParam(value="amount")String amount, @RequestParam(value="printFlag")String printFlag, @RequestParam(value="GTbit")String GTbit,@RequestParam(value="GTmessage",defaultValue = "none")String GTmessage) throws InterruptedException, JsonProcessingException{
       statusMessage="";
       receipt="";
       service.terminalStatus(terminalIp, terminalPort, printFlag, Application.tcpSender);
        return "terminalStatus";
    }
    @RequestMapping(value="/reprint", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String reprintReceipt(@RequestParam(value="terminalIp")String terminalIp,@RequestParam(value = "terminalPort")String terminalPort,
            @RequestParam(value="amount")String amount, @RequestParam(value="printFlag")String printFlag, @RequestParam(value="GTbit")String GTbit,@RequestParam(value="GTmessage",defaultValue = "none")String GTmessage) throws InterruptedException, JsonProcessingException{
       statusMessage="";
       receipt="";
       service.reprintReceipt(terminalIp, terminalPort, printFlag, Application.tcpSender);
        return "Reprint Receipt";
    }
}
