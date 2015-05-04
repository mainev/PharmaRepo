/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import rdr.client.controls.MyTreeView;
import rdr.client.service.ReceivedRawMaterialService;
import rdr.client.entity.ReceivedRawMaterial;
import rdr.client.service.TransferredRawMaterialService;

/**
 *
 * @author Admin
 */
public class MainViewController implements Initializable {

    @FXML
    AnchorPane anchorPaneHomeView;
  
    private static final ReceivedRawMaterialService receivedRmService = new ReceivedRawMaterialService();
    private static final TransferredRawMaterialService transferredRmService = new TransferredRawMaterialService();
    ObservableList<ReceivedRawMaterial> allReceivedRms = receivedRmService.getAllReceivedRms();
    
    @FXML
    AnchorPane anchorPaneContentHead;
   
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        anchorPaneHomeView.setVisible(true);
        anchorPaneTransferRm.setVisible(false);
        anchorPaneReceivedRm.setVisible(false);
        anchorPaneStockCard.setVisible(false);
        anchorPaneReportView.setVisible(false);
        anchorPaneReceivePmView.setVisible(false);
        anchorPaneTransferPmView.setVisible(false);

        initTreeView();
        initMaterialTypeChoiceBox();

        choiceBoxMaterialType.setItems(typeList);
        choiceBoxMaterialType.setVisible(false);
        label1.setVisible(false);

    }

    @FXML
    AnchorPane anchorPaneTransferRm;
    @FXML
    AnchorPane anchorPaneReceivedRm;
    @FXML
    AnchorPane anchorPaneReceivedPm;
    @FXML
    AnchorPane anchorPaneStockCard;
    @FXML
    AnchorPane anchorPaneReportView;
    @FXML
    AnchorPane anchorPaneReceivePmView;
    @FXML
    AnchorPane anchorPaneTransferPmView;

    @FXML
    public void openTransferRmView() {

        anchorPaneHomeView.setVisible(false);
        anchorPaneTransferRm.setVisible(true);
        anchorPaneReceivedRm.setVisible(false);
        anchorPaneStockCard.setVisible(false);
        anchorPaneReportView.setVisible(false);
        anchorPaneReceivePmView.setVisible(false);
        anchorPaneTransferPmView.setVisible(false);
        //  initQASampleTransferCheckBox();
    }

    @FXML
    public void openReceivedRmView() {
        anchorPaneHomeView.setVisible(false);
        anchorPaneTransferRm.setVisible(false);
        anchorPaneReceivedRm.setVisible(true);
        anchorPaneStockCard.setVisible(false);
        anchorPaneReportView.setVisible(false);
        anchorPaneReceivePmView.setVisible(false);
        anchorPaneTransferPmView.setVisible(false);
    }

    @FXML
    public void openStockCardView() {

        anchorPaneHomeView.setVisible(false);
        anchorPaneTransferRm.setVisible(false);
        anchorPaneReceivedRm.setVisible(false);
        anchorPaneStockCard.setVisible(true);

        anchorPaneReportView.setVisible(false);

        anchorPaneReceivePmView.setVisible(false);

        anchorPaneTransferPmView.setVisible(false);
    }

    @FXML
    public void openReportView() {

        anchorPaneHomeView.setVisible(false);
        anchorPaneTransferRm.setVisible(false);
        anchorPaneReceivedRm.setVisible(false);
        anchorPaneStockCard.setVisible(false);

        anchorPaneReportView.setVisible(true);

        anchorPaneReceivePmView.setVisible(false);

        anchorPaneTransferPmView.setVisible(false);
    }

    @FXML
    public void openReceivePmView() {

        anchorPaneHomeView.setVisible(false);
        anchorPaneTransferRm.setVisible(false);
        anchorPaneReceivedRm.setVisible(false);
        anchorPaneStockCard.setVisible(false);

        anchorPaneReportView.setVisible(false);

        anchorPaneReceivePmView.setVisible(true);

        anchorPaneTransferPmView.setVisible(false);
    }

    @FXML
    public void openTransferPmView() {

        anchorPaneHomeView.setVisible(false);
        anchorPaneTransferRm.setVisible(false);
        anchorPaneReceivedRm.setVisible(false);
        anchorPaneStockCard.setVisible(false);

        anchorPaneReportView.setVisible(false);

        anchorPaneReceivePmView.setVisible(false);

        anchorPaneTransferPmView.setVisible(true);
    }

//    @FXML
//    TreeView<String> treeViewMain;
//    TreeItem<String> treeItemRoot = new TreeItem<>("Root");
    @FXML
    BorderPane borderPaneInventoryMenu;

    ObservableList<String> typeList = FXCollections.observableArrayList("Raw Material", "Packaging Material");
    @FXML
    ChoiceBox<String> choiceBoxMaterialType;

    MyTreeView treeViewMain = new MyTreeView();
    String child = "";
    String parent = "";

    public void initTreeView() {

        borderPaneInventoryMenu.setCenter(treeViewMain);
        //     anchorPaneTreeView.getChildren().add(treeViewMain);

        treeViewMain.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observableValue, TreeItem<String> oldItem, TreeItem<String> newItem) {
                parent = newItem.parentProperty().get().getValue();
                child = newItem.getValue();

                if (child.equals("Report")) {
                    openReportView();
                    choiceBoxMaterialType.setVisible(false);
                    label1.setVisible(false);
                } else {
                    switch (child) {
                        case "Receive":
                            choiceBoxMaterialType.setValue("");
                            choiceBoxMaterialType.setValue("Raw Material");
                            break;
                        case "Transfer":
                            choiceBoxMaterialType.setValue("");
                            choiceBoxMaterialType.setValue("Raw Material");
                            break;
                        case "Stockcard":
                            choiceBoxMaterialType.setValue("");
                            choiceBoxMaterialType.setValue("Raw Material");
                            break;
                        default:
                            break;
                    }
                }

            }
        });
    }

    @FXML
    Label label1;

    public void initMaterialTypeChoiceBox() {
        choiceBoxMaterialType.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observableValue, String oldItem, String newItem) {

                String value = newItem;
                //  System.out.println(child);
                choiceBoxMaterialType.setVisible(true);
                label1.setVisible(true);
                if (child.equals("Receive") && value.equals("Raw Material")) {
                    openReceivedRmView();
                } else if (child.equals("Receive") && value.equals("Packaging Material")) {
                    openReceivePmView();
                } else if (child.equals("Transfer") && value.equals("Raw Material")) {

                    openTransferRmView();
                } else if (child.equals("Transfer") && value.equals("Packaging Material")) {
                    openTransferPmView();
                } else if (child.equals("Stockcard") && value.equals("Raw Material")) {
                    openStockCardView();
                }

            }
        });
    }

}
