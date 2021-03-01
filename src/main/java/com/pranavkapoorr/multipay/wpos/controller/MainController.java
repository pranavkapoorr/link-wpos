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

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
 
    public MainController() {
      
    }
    
     @RequestMapping(value="/home")
     public ModelAndView MainPage(HttpSession session ) {
        return new ModelAndView("SIMULATORx");
     }
}
