/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pranavkapoorr.multipay.wpos;

import java.util.HashMap;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Pranav
 */
@Component
@Scope("singleton")
public class DataBase {
    private final static HashMap<Integer,SessionUser>  dataBaseStatusMessage = new HashMap<>();;
    private final static HashMap<Integer,SessionUser>  dataBaseReceiptMessage = new HashMap<>();;

    
    public static HashMap getDataBaseStatusMessage(){
        return dataBaseStatusMessage;
    }
    public static HashMap getDataBaseReceiptMessage(){
        return dataBaseReceiptMessage;
    }
    public void showDataBase(){
        dataBaseStatusMessage.forEach((I,U)->{
            System.out.println(I +"-"+ U.getStatusMessage() +"-" + U.getReceipt());
        });
        System.out.println("------------------------------------------------------");
         dataBaseReceiptMessage.forEach((I,U)->{
            System.out.println(I +"-"+ U.getStatusMessage() +"-" + U.getReceipt());
        });
    }
}
