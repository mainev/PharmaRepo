/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import rdr.client.controls.MyAutoCompleteTextField;
import rdr.client.controls.MyListView;
import rdr.client.controls.MyNotifications;
import rdr.client.entity.PackagingMaterial;
import rdr.client.entity.TransferredPackagingMaterial;
import rdr.client.service.PackagingMaterialService;
import rdr.client.service.TransferredPackagingMaterialService;

/**
 * FXML Controller class
 *
 * @author jemuel
 */
public class TransferPackagingMaterialController implements Initializable {

    /**
     * Initializes the controller class.
     */
//    @FXML
//    TextField textFieldPackMatCode;
    @FXML
    TextField textFieldTsNo;
    @FXML
    TextField textFieldPurpose;
    @FXML
    TextField textFieldPartialQuantity;

    @FXML
    ListView<PackagingMaterial> listViewFilteredPackMat;

    private static final PackagingMaterialService pmService = new PackagingMaterialService();
    private static final TransferredPackagingMaterialService transferredPmService = new TransferredPackagingMaterialService();

    MyListView listViewAvailablePm = new MyListView();
    MyListView listViewOutgoingPm = new MyListView();

    @FXML
    BorderPane borderPaneTransferPM;

    @FXML
    AnchorPane anchorPaneList1;
    @FXML
    AnchorPane anchorPaneList2;

    @FXML
    Label labelList1Total;
    @FXML
    Label labelList2Total;

    @FXML
    Label labelClient;

    @FXML
    Button buttonAddItem;
    @FXML
    Button buttonRemoveItem;
    @FXML
    Button buttonRemoveAll;
    @FXML
    Button buttonAddAll;
    @FXML
    Button buttonAddPartial;
    @FXML
    Button buttonOk;

    @FXML
    CheckBox checkboxQaSample;

    @FXML
    GridPane upperGrid;
    MyAutoCompleteTextField<PackagingMaterial> packMatTextField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        packMatTextField = new MyAutoCompleteTextField(pmService.getAllPackagingMaterials());
        packMatTextField.displaySuggestionsOnTop(true);
        upperGrid.add(packMatTextField, 1, 0);

        labelList1Total.setText("Total: " + getPmListTotal(listViewAvailablePm.getItems()));
        labelList2Total.setText("Total: " + getPmListTotal(listViewOutgoingPm.getItems()));

        initPartialQuantityTextField();
        anchorPaneList1.getChildren().add(listViewAvailablePm);
        anchorPaneList2.getChildren().add(listViewOutgoingPm);

        listViewAvailablePm.setPrefSize(305, 207);
        listViewOutgoingPm.setPrefSize(305, 207);
        disableTransferPmFields();
        buttonAddPartial.setDisable(true);

        initPackagingMaterialSelection();
        initQASampleTransferCheckBox();
    }

    private void initQASampleTransferCheckBox() {
        checkboxQaSample.setSelected(false);
        checkboxQaSample.selectedProperty().addListener(
                (ov, oldV, newV) -> {

                    if (packMatTextField.getSelectedItem() != null) {
                        PackagingMaterial selectedPm = (PackagingMaterial) packMatTextField.getSelectedItem();
                        evaluateSelectedPm(selectedPm, checkboxQaSample.isSelected());
                    }

                });
    }

    public Double getPmListTotal(ObservableList<TransferredPackagingMaterial> rmList) {
        double total = 0;
        if (!rmList.isEmpty()) {
            for (TransferredPackagingMaterial tpm : rmList) {
                total += tpm.getQuantity();
            }
        }

        return total;
    }

    private void initPartialQuantityTextField() {

        textFieldPartialQuantity.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (newValue.isEmpty()) {
                    buttonAddPartial.setDisable(true);
                } else if (oldValue.isEmpty() || !newValue.isEmpty()) {
                    buttonAddPartial.setDisable(false);
                }
            }
        });

    }
    private ObservableList<PackagingMaterial> pmList = FXCollections.observableArrayList();

    public void initPackagingMaterialSelection() {
        packMatTextField.getListView().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        packMatTextField.getListView().getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> {

            Platform.runLater(() -> {
                if (nv != null) {
                    PackagingMaterial selectedPm = (PackagingMaterial) nv;
                    packMatTextField.setSelectedItem(selectedPm);
                    packMatTextField.getTextField().setText(selectedPm.getDescription());
                    packMatTextField.getListView().getSelectionModel().clearSelection();
                    evaluateSelectedPm(selectedPm, checkboxQaSample.isSelected());

                }
            });
        });

    }

    private void evaluateSelectedPm(PackagingMaterial selectedPmToTransfer, boolean value) {
        ObservableList<TransferredPackagingMaterial> transferrablePmList = FXCollections.observableArrayList();
        if (!value) {
            transferrablePmList = transferredPmService.getAvailablePM(selectedPmToTransfer);
        } else {
            transferrablePmList = transferredPmService.getQuarantinePm(selectedPmToTransfer);
        }

        if (!transferrablePmList.isEmpty()) {
            MyNotifications.displayInformation(selectedPmToTransfer.getDescription() + " is available");

            labelClient.setText("(Client: " + selectedPmToTransfer.getClientId().getName() + " )");
            enableTransferPmFields();
            listViewAvailablePm.setItems(transferrablePmList);
            listViewOutgoingPm.setItems(FXCollections.observableArrayList());
            listViewAvailablePm.getSelectionModel().clearSelection();
            labelList1Total.setText("Total: " + getPmListTotal(listViewAvailablePm.getItems()));
            labelList2Total.setText("Total: " + getPmListTotal(listViewOutgoingPm.getItems()));

        } else {
            labelClient.setText("");
            MyNotifications.displayError("No " + selectedPmToTransfer.getDescription() + " available");

            disableTransferPmFields();
            clearAllFields();
            labelList1Total.setText("Total: " + getPmListTotal(listViewAvailablePm.getItems()));
            labelList2Total.setText("Total: " + getPmListTotal(listViewOutgoingPm.getItems()));

        }

    }

    private void enableTransferPmFields() {
        boolean value = false;

        textFieldTsNo.setDisable(value);
        textFieldPurpose.setDisable(value);
        listViewAvailablePm.setDisable(value);
        listViewOutgoingPm.setDisable(value);
        buttonAddItem.setDisable(value);
        buttonRemoveItem.setDisable(value);
        buttonRemoveAll.setDisable(value);
        buttonAddAll.setDisable(value);
        //buttonAddPartial.setDisable(value);
        buttonOk.setDisable(value);
        textFieldPartialQuantity.setDisable(value);

    }

    private void disableTransferPmFields() {
        boolean value = true;

        textFieldTsNo.setDisable(value);
        textFieldPurpose.setDisable(value);
        listViewAvailablePm.setDisable(value);
        listViewOutgoingPm.setDisable(value);
        buttonAddItem.setDisable(value);
        buttonRemoveItem.setDisable(value);
        buttonRemoveAll.setDisable(value);
        buttonAddAll.setDisable(value);
        buttonAddPartial.setDisable(value);
        buttonOk.setDisable(value);
        textFieldPartialQuantity.setDisable(value);

    }

    private void clearAllFields() {
        //selectedPmToTransfer = null;
        //textFieldPackMatCode.setText("");
        labelClient.setText("");
        //packMatTextField.clearAll();
        textFieldTsNo.setText("");
        textFieldPurpose.setText("");
        listViewAvailablePm.setItems(FXCollections.observableArrayList());
        listViewOutgoingPm.setItems(FXCollections.observableArrayList());
        textFieldPartialQuantity.setText("");
        labelList1Total.setText("Total: " + getPmListTotal(listViewAvailablePm.getItems()));
        labelList2Total.setText("Total: " + getPmListTotal(listViewOutgoingPm.getItems()));
    }

    public void transferItem() {
        if (listViewAvailablePm.getSelectionModel().getSelectedItem() != null) {
            TransferredPackagingMaterial selectedAvailableItem = listViewAvailablePm.getSelectionModel().getSelectedItem();

            listViewAvailablePm.transferItem(selectedAvailableItem, listViewOutgoingPm);
            labelList1Total.setText("Total: " + getPmListTotal(listViewAvailablePm.getItems()));
            labelList2Total.setText("Total: " + getPmListTotal(listViewOutgoingPm.getItems()));
        }

    }

    public void transferItems() {

        listViewAvailablePm.transferItems(listViewAvailablePm.getItems(), listViewOutgoingPm);
        labelList1Total.setText("Total: " + getPmListTotal(listViewAvailablePm.getItems()));
        labelList2Total.setText("Total: " + getPmListTotal(listViewOutgoingPm.getItems()));
    }

    public void partialTransfer() {
        int qty = Integer.parseInt(textFieldPartialQuantity.getText());
        if (listViewAvailablePm.getSelectionModel().getSelectedItem() != null) {
            TransferredPackagingMaterial selectedAvailableItem = listViewAvailablePm.getSelectionModel().getSelectedItem();

            listViewAvailablePm.partialTransferItem(selectedAvailableItem, qty, listViewOutgoingPm);
            labelList1Total.setText("Total: " + getPmListTotal(listViewAvailablePm.getItems()));
            labelList2Total.setText("Total: " + getPmListTotal(listViewOutgoingPm.getItems()));
        }
    }

    public void returnOutgoingItem() {
        if (listViewOutgoingPm.getSelectionModel().getSelectedItem() != null) {
            TransferredPackagingMaterial outgoingItem = listViewOutgoingPm.getSelectionModel().getSelectedItem();

            listViewOutgoingPm.returnOutgoingItem(outgoingItem, listViewAvailablePm);
            labelList1Total.setText("Total: " + getPmListTotal(listViewAvailablePm.getItems()));
            labelList2Total.setText("Total: " + getPmListTotal(listViewOutgoingPm.getItems()));
        }
    }

    public void returnOutgoingItems() {
        listViewOutgoingPm.returnOutgoingItems(listViewOutgoingPm.getItems(), listViewAvailablePm);
        labelList1Total.setText("Total: " + getPmListTotal(listViewAvailablePm.getItems()));
        labelList2Total.setText("Total: " + getPmListTotal(listViewOutgoingPm.getItems()));
    }

    public void transferOutgoingPm() {
        String ts = textFieldTsNo.getText();
        String purpose = textFieldPurpose.getText();

        double total = 0;
        for (TransferredPackagingMaterial tpm : listViewOutgoingPm.getItems()) {
            tpm.setPurpose(purpose);
            tpm.setTsNo(ts);
            total += tpm.getQuantity();
        }
        transferredPmService.transferPMToMMD(listViewOutgoingPm.getItems());
        clearAllFields();
        disableTransferPmFields();
        packMatTextField.clearAll();
        MyNotifications.displayConfirm("Packaging material transferred.");
    }

}
