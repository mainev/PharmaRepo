/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import mbr.client.ScreenNavigator;

/**
 *
 * @author maine
 */
public class MainController implements Initializable {

    @FXML
    StackPane _mainStackPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void openMBRListView(){
        ScreenNavigator.loadScreen(ScreenNavigator.MBR_LIST_SCREEN);
    }
    
    public void setScreen(Node node) {
        _mainStackPane.getChildren().setAll(node);
    }

}
