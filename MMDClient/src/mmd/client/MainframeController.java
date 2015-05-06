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
import mmd.client.controls.ScreensController;
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
    @FXML
    AnchorPane _anchorPaneMainContent;

    MyTreeView treeViewMainMenu = new MyTreeView();
    PackagingMaterialService pmService = new PackagingMaterialService();
    ScreensController mainContainer = new ScreensController();

    public static String receivePackagingMaterialScreenID = "receivePackagingMaterialView";
    public static String receivePackagingMaterialScreenFile = "view/ReceivePackagingMaterialView.fxml";
    public static String receiveRawMaterialScreenID = "receiveRawMaterialView";
    public static String receiveRawMaterialScreenFile = "view/ReceiveRawMaterialView.fxml";

    @Override
    public void initialize(URL url, ResourceBundle rb) {

       // hideAllViews();
        configureMenu();

        mainContainer.loadScreen(MainframeController.receivePackagingMaterialScreenID, MainframeController.receivePackagingMaterialScreenFile);
        mainContainer.loadScreen(MainframeController.receiveRawMaterialScreenID, MainframeController.receiveRawMaterialScreenFile);
        mainContainer.setScreen(MainframeController.receivePackagingMaterialScreenID);
        _anchorPaneMainContent.getChildren().add(mainContainer);

    }

    public void displayReceivePackagingMaterialScreen() {
        mainContainer.setScreen(MainframeController.receivePackagingMaterialScreenID);
    }

    public void displayReceiveRawMaterialScreen() {
        mainContainer.setScreen(MainframeController.receiveRawMaterialScreenID);
    }

    private void configureMenu() {
        _anchorPanePackagingMaterialMenu.getChildren().add(treeViewMainMenu);
        treeViewMainMenu.getSelectionModel().selectedItemProperty().addListener(
                (ob, ov, nv) -> {
                    String parent = nv.parentProperty().get().getValue();
                    String child = nv.getValue();

                    if (child.equals("Receive")) {
                        //openReceiveView();
                        displayReceivePackagingMaterialScreen();
                    } else if (child.equals("Transfer")) {
                       // openTransferView();
                        displayReceiveRawMaterialScreen();
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
     /** Holder of a switchable vista. */
    @FXML
    private StackPane vistaHolder;

    /**
     * Replaces the vista displayed in the vista holder with a new vista.
     *
     * @param node the vista node to be swapped in.
     */
    public void setVista(Node node) {
        vistaHolder.getChildren().setAll(node);
    }

}
