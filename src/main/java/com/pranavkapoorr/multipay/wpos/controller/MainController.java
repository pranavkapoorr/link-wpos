package com.pranavkapoorr.multipay.wpos.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pranav
 */

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.ModelFactory;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	private static final String propertiesPath = "dynamic/config.properties";
	private Properties config;
	
    public MainController() {
      loadProperties();
    }
    
     @RequestMapping(value="/home")
     public String MainPage(Model model) {
    	 String tileText = config.getProperty("text-tile", null);
    	 if(tileText != null) {
    		 model.addAttribute("tiles",Arrays.asList(tileText.split(",")));
    	 }
    	 String leadText = config.getProperty("leadtext","<H2>Point Of Sale</H2>");
    	 String finalText = null;
    	 if(leadText.contains("png")||leadText.contains("jpg")||leadText.contains("jpeg")) {
    		 finalText = "<img style='float:left' alt='MultiPay'  height='10' src='"+leadText+"'>";
    	 }else {
    		 finalText = "<h2>" + leadText + "</h2>";
    	 }
    	 model.addAttribute("leadtext", finalText);
    	 model.addAttribute("title", config.getProperty("title","MultiPay Ltd."));
        return "SIMULATORx";
     }
     
     private void loadProperties() {
 		config = new Properties();
 		try {
 			InputStream in = new FileInputStream(propertiesPath);
 			config.load(in);
 			in.close();
 		} catch (FileNotFoundException e) {
 			System.err.println("config missing exception -> " + e.getMessage());
 			System.exit(0);
 		} catch (IOException e) {
 			System.err.println("config missing exception -> " + e.getMessage());
 			System.exit(0);
 		}
 		//if(config.getProperty("Connection_Type").equalsIgnoreCase("TCP_IP")){
 	}
}
