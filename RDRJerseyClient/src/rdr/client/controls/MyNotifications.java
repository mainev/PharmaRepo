/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client.controls;

import org.controlsfx.control.Notifications;

/**
 *
 * @author Admin
 */
public class MyNotifications {
    
    
    public static void displayInformation(String text){
        Notifications.create()
                        .darkStyle()
                        .text(text)
                        .showInformation();
    }
    
    public static void displayConfirm(String text){
        Notifications.create()
                        .darkStyle()
                        .text(text)
                        .showConfirm();
    }
    
    public static void displayError(String text){
        Notifications.create()
                        .darkStyle()
                        .text(text)
                        .showError();
    }
    
    public static void displayWarning(String text){
        Notifications.create()
                        .darkStyle()
                        .text(text)
                        .showWarning();
    }
}
