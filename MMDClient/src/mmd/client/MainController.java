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
    AnchorPane _anchorPaneMenu;

    @FXML
    StackPane _stackPane;

    MyTreeView treeViewMenu = new MyTreeView();
    PackagingMaterialService pmService = new PackagingMaterialService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initializing main controller");
        initMenu();
    }

    private void initMenu() {
        treeViewMenu.setPrefWidth(220);
        _anchorPaneMenu.getChildren().add(treeViewMenu);
        treeViewMenu.getSelectionModel().selectedItemProperty().addListener(
                (ob, ov, nv) -> {
                    String parent = nv.parentProperty().get().getValue();
                    String child = nv.getValue();

                    switch (parent) {
                        case "Raw Material":
                            if (child.equals("Receive")) {
                                openReceiveRawMaterial();
                            } else {
                                openIssueRawMaterial();
                            }
                            break;

                        case "Packaging Material":
                            if (child.equals("Receive")) {
                                openReceivePackagingMaterial();
                            } else {
                                openIssuePackagingMaterial();
                            }
                            break;

                        default:
                            break;

                    }
                });

    }

    private void openIssuePackagingMaterial() {
        ScreenNavigator.loadScreen(ScreenNavigator.ISSUE_PACKAGING_MATERIAL_SCREEN);
    }

    private void openIssueRawMaterial() {

    }

    private void openReceivePackagingMaterial() {
        ScreenNavigator.loadScreen(ScreenNavigator.RECEIVE_PACKAGING_MATERIAL_SCREEN);
    }

    private void openReceiveRawMaterial() {
        ScreenNavigator.loadScreen(ScreenNavigator.RECEIVE_RAW_MATERIAL_SCREEN);
    }

    public void setScreen(Node node) {
        _stackPane.getChildren().setAll(node);
    }
}
