/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmd.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import mmd.client.controls.ControlledScreen;
import mmd.client.controls.ScreensController;

/**
 *
 * @author maine
 */
public class ReceiveRawMaterialViewController implements Initializable, ControlledScreen {

    ScreensController myController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    
   

}
