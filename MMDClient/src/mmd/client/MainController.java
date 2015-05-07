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
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import mmd.client.controls.MyTreeView;
import mmd.client.service.PackagingMaterialService;

/**
 *
 * @author maine
 */
public class MainController implements Initializable {

    @FXML
    AnchorPane _anchorPaneReceive;

    @FXML
    AnchorPane _anchorPanePackagingMaterialMenu;
    @FXML
    AnchorPane _anchorPaneRawMaterialMenu;

    @FXML
    StackPane _stackPane;

    MyTreeView treeViewMainMenu = new MyTreeView();
    PackagingMaterialService pmService = new PackagingMaterialService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initializing main controller");
        configureMenu();

    }

    private void configureMenu() {    
        
        //packaging material
        _anchorPanePackagingMaterialMenu.getChildren().add(treeViewMainMenu);
        treeViewMainMenu.getSelectionModel().selectedItemProperty().addListener(
                (ob, ov, nv) -> {
                    String parent = nv.parentProperty().get().getValue();
                    String child = nv.getValue();

                    if (child.equals("Receive")) {
                        openReceivePackagingMaterial();
                    } else if (child.equals("Issue")) {
                        openIssuePackagingMaterial();
                    }
                });
        
        //raw material
        MyTreeView treeViewRawMatMenu = new MyTreeView();
        _anchorPaneRawMaterialMenu.getChildren().add(treeViewRawMatMenu);
        treeViewRawMatMenu.getSelectionModel().selectedItemProperty().addListener(
                (ob, ov, nv) -> {
                    String parent = nv.parentProperty().get().getValue();
                    String child = nv.getValue();

                    if (child.equals("Receive")) {
                        openReceiveRawMaterial();
                    } else if (child.equals("Transfer")) {
//                        openReceiveRawMaterial();
                    }
                });
    }

    private void openReceivePackagingMaterial() {
        ScreenNavigator.loadScreen(ScreenNavigator.RECEIVE_PACKAGING_MATERIAL_SCREEN);
    }

    private void openIssuePackagingMaterial() {
        ScreenNavigator.loadScreen(ScreenNavigator.ISSUE_PACKAGING_MATERIAL_SCREEN);
    }

    private void openReceiveRawMaterial() {
        ScreenNavigator.loadScreen(ScreenNavigator.RECEIVE_RAW_MATERIAL_SCREEN);
    }

    public void setScreen(Node node) {
        _stackPane.getChildren().setAll(node);
    }
}
