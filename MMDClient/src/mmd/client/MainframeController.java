/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmd.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import mmd.client.controls.MyTreeView;
import mmd.client.service.PackagingMaterialService;

/**
 *
 * @author maine
 */
public class MainframeController implements Initializable {

    @FXML
    AnchorPane _anchorPaneReceive;
    
    @FXML
    AnchorPane _anchorPanePackagingMaterialMenu;
    @FXML
    AnchorPane _anchorPaneRawMaterialMenu;

 

    MyTreeView treeViewMainMenu = new MyTreeView();
    PackagingMaterialService pmService = new PackagingMaterialService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        hideAllViews();
        configureMenu();
        
       
        
    }

    private void configureMenu() {
        _anchorPanePackagingMaterialMenu.getChildren().add(treeViewMainMenu);
        treeViewMainMenu.getSelectionModel().selectedItemProperty().addListener(
                (ob, ov, nv) -> {
                    String parent = nv.parentProperty().get().getValue();
                    String child = nv.getValue();

                    if (child.equals("Receive")) {
                        openReceiveView();
                    } else if (child.equals("Transfer")) {
                        openTransferView();
                    }
                });
    }

   
    private void hideAllViews() {
        _anchorPaneReceive.setVisible(false);
    }

    private void openReceiveView() {
        _anchorPaneReceive.setVisible(true);
    }

    private void openTransferView() {
        _anchorPaneReceive.setVisible(false);
    }

}
