/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.scene.layout.GridPane;
import rdr.client.controls.MyAutoCompleteTextField;
import rdr.client.controls.MyNotifications;
import rdr.client.entity.Product;
import rdr.client.entity.RawMaterial;
import rdr.client.entity.TransferredRawMaterial;
import rdr.client.service.ProductService;
import rdr.client.service.RawMaterialService;
import rdr.client.service.TransferredRawMaterialService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class TransferRawMaterialController implements Initializable {

//    @FXML
//    ListView<RawMaterial> listViewFilteredRmToTransfer;
//    @FXML
//    TextField textFieldRmToTransfer;
//    RawMaterial selectedRmToTransfer;
    @FXML
    Label clientLabel;

    private static final RawMaterialService rmService = new RawMaterialService();
    private ObservableList<RawMaterial> rmList = FXCollections.observableArrayList();

//    @FXML
//    private ListView<Product> listViewFilteredProduct;
//    @FXML
//    private TextField textFieldTransferForProduct;
    //   private Product selectedProduct;
    private ObservableList<Product> productList = FXCollections.observableArrayList();
    private static final ProductService productService = new ProductService();
    private static final TransferredRawMaterialService transferredRmService = new TransferredRawMaterialService();

    @FXML
    ListView<TransferredRawMaterial> listViewTransferrableRm;
    @FXML
    ListView<TransferredRawMaterial> listViewChosenRmToTransfer;
    @FXML
    Button buttonAddToRight;
    @FXML
    Button buttonRemoveFromRight;
    @FXML
    Button buttonAddAllToRight;
    @FXML
    Button buttonRemoveAllFromRight;
    @FXML
    Button buttonTransferRm;

    @FXML
    Label labelList1total;
    @FXML
    Label labelList2total;

    ObservableList<TransferredRawMaterial> transferrableRmList = FXCollections.observableArrayList();

    @FXML
    CheckBox checkboxQaSample;
//      listViewTransferrableRm.setDisable(false);
//        listViewChosenRmToTransfer

    @FXML
    GridPane upperGrid;
    MyAutoCompleteTextField<RawMaterial> rmTextField;
    MyAutoCompleteTextField<Product> productTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       labelList1total.setText("Total: " + getRmListTotal(listViewTransferrableRm.getItems()));
        labelList2total.setText("Total: " + getRmListTotal(listViewChosenRmToTransfer.getItems()));
        rmTextField = new MyAutoCompleteTextField(rmService.getAllRawMaterials());
        rmTextField.displaySuggestionsOnTop(true);
        productTextField = new MyAutoCompleteTextField(productService.getAllProducts());
        productTextField.displaySuggestionsOnTop(true);

        upperGrid.add(rmTextField, 1, 0);
        upperGrid.add(productTextField, 1, 1);

        initRawMaterialSelection();
        initQASampleTransferCheckBox();
        disableTransferRmFields();

    }

    private void initQASampleTransferCheckBox() {
        checkboxQaSample.setSelected(false);
        checkboxQaSample.selectedProperty().addListener(
                (ov, oldV, newV) -> {
                    if (rmTextField.getSelectedItem() != null) {
                        RawMaterial selectedRm = (RawMaterial) rmTextField.getSelectedItem();
                        evaluateSelectedRm(selectedRm, checkboxQaSample.isSelected());
                    
                    }

                });
    }

    public Double getRmListTotal(ObservableList<TransferredRawMaterial> rmList) {
        double total = 0.0;
        if (!rmList.isEmpty()) {
            for (TransferredRawMaterial trm : rmList) {
                total += trm.getQuantity();
            }
        }
        return total;
    }

    public void initRawMaterialSelection() {
        rmTextField.getListView().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        rmTextField.getListView().getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> {

            Platform.runLater(() -> {
                if (nv != null) {
                    RawMaterial selectedRm = (RawMaterial) nv;
                    rmTextField.setSelectedItem(selectedRm);
                    rmTextField.getTextField().setText(selectedRm.getName());
                    rmTextField.getListView().getSelectionModel().clearSelection();
                    evaluateSelectedRm(selectedRm, checkboxQaSample.isSelected());
             
                }
            });
        });

    }

    @FXML
    TextField textFieldTos;
    @FXML
    TextField textFieldSo;

    private void enableTransferRmFields() {
        textFieldTos.setDisable(false);
        textFieldSo.setDisable(false);
        productTextField.getTextField().setDisable(false);
        listViewTransferrableRm.setDisable(false);
        listViewChosenRmToTransfer.setDisable(false);
        buttonAddToRight.setDisable(false);
        buttonRemoveFromRight.setDisable(false);
        buttonAddAllToRight.setDisable(false);
        buttonRemoveAllFromRight.setDisable(false);
        buttonTransferRm.setDisable(false);
    }

    private void disableTransferRmFields() {
        textFieldTos.setDisable(true);
        textFieldSo.setDisable(true);
        productTextField.getTextField().setDisable(true);
        listViewTransferrableRm.setDisable(true);
        listViewChosenRmToTransfer.setDisable(true);
        buttonAddToRight.setDisable(true);
        buttonRemoveFromRight.setDisable(true);
        buttonAddAllToRight.setDisable(true);
        buttonRemoveAllFromRight.setDisable(true);
        buttonTransferRm.setDisable(true);

    }

    private void evaluateSelectedRm(RawMaterial selectedRmToTransfer, boolean qaSample) {
         //     if()
        if (!qaSample) {
            transferrableRmList = transferredRmService.getTransferableRmList(selectedRmToTransfer);
        } else {
            transferrableRmList = transferredRmService.getQuarantinePm(selectedRmToTransfer);
        }
        if (!transferrableRmList.isEmpty()) {
            clientLabel.setText("(Client: " + selectedRmToTransfer.getClientId().getName() + " )");
            enableTransferRmFields();
            listViewTransferrableRm.setItems(transferrableRmList);
            listViewChosenRmToTransfer.setItems(FXCollections.observableArrayList());
            listViewTransferrableRm.getSelectionModel().clearSelection();
            labelList1total.setText("Total: " + getRmListTotal(listViewTransferrableRm.getItems()));
            labelList2total.setText("Total: " + getRmListTotal(listViewChosenRmToTransfer.getItems()));
            MyNotifications.displayInformation(selectedRmToTransfer.getName() + " is available");

        } else {
            clientLabel.setText("");
            disableTransferRmFields();
            clearAllFields();
            labelList1total.setText("Total: " + getRmListTotal(listViewTransferrableRm.getItems()));
            labelList2total.setText("Total: " + getRmListTotal(listViewChosenRmToTransfer.getItems()));
            MyNotifications.displayError("No " + selectedRmToTransfer.getName()+ " available");
        }

    }

    private void clearAllFields() {
        clientLabel.setText("");
        productTextField.clearAll();
        textFieldTos.setText("");
        textFieldSo.setText("");
        listViewTransferrableRm.setItems(FXCollections.observableArrayList());
        listViewChosenRmToTransfer.setItems(FXCollections.observableArrayList());
        labelList1total.setText("Total: " + getRmListTotal(listViewTransferrableRm.getItems()));
        labelList2total.setText("Total: " + getRmListTotal(listViewChosenRmToTransfer.getItems()));
    }

    @FXML
    private void transferRawMaterial() {
        Product selectedProduct = (Product) productTextField.getSelectedItem();
        RawMaterial selectedRm = (RawMaterial) rmTextField.getSelectedItem();
        String ts = textFieldTos.getText();
        String so = textFieldSo.getText();
        double totalQty = 0;
        String unit = "";

        List<TransferredRawMaterial> trmList = listViewChosenRmToTransfer.getItems();
        for (TransferredRawMaterial trm : trmList) {
            
            trm.setDateTransferred(new Date());
            trm.setIsTransferred(true);
            trm.setSoNo(so);
            trm.setTsNo(ts);
            trm.setProductId(selectedProduct);
            totalQty += trm.getQuantity();
            unit = trm.getUnit();
        //    System.out.println(trm);
        }

        transferredRmService.transferRm(selectedProduct, ts, so, trmList);
        MyNotifications.displayInformation(totalQty + " " + unit + " of " + selectedRm.getName() + " have been transferred.");
      rmTextField.clearAll();
         clearAllFields();
        disableTransferRmFields();

    }

    public void addRawMatToTransfer() {
        if (listViewTransferrableRm.getSelectionModel().getSelectedItem() != null) {
            TransferredRawMaterial trm = listViewTransferrableRm.getSelectionModel().getSelectedItem();

            listViewChosenRmToTransfer.getItems().add(trm);
            listViewTransferrableRm.getItems().remove(trm);
            labelList1total.setText("Total: " + getRmListTotal(listViewTransferrableRm.getItems()));
            labelList2total.setText("Total: " + getRmListTotal(listViewChosenRmToTransfer.getItems()));
        }

    }

    @FXML
    private void removeRawMatToTransfer() {
        if (listViewChosenRmToTransfer.getSelectionModel().getSelectedItem() != null) {
            TransferredRawMaterial trm = listViewChosenRmToTransfer.getSelectionModel().getSelectedItem();
            listViewTransferrableRm.getItems().add(trm);
            listViewChosenRmToTransfer.getItems().remove(trm);
            labelList1total.setText("Total: " + getRmListTotal(listViewTransferrableRm.getItems()));
            labelList2total.setText("Total: " + getRmListTotal(listViewChosenRmToTransfer.getItems()));
        }
    }

    @FXML
    private void addAllRawMatToTransfer() {

        listViewChosenRmToTransfer.getItems().addAll(listViewTransferrableRm.getItems());
        listViewTransferrableRm.getItems().removeAll(listViewTransferrableRm.getItems());
        labelList1total.setText("Total: " + getRmListTotal(listViewTransferrableRm.getItems()));
        labelList2total.setText("Total: " + getRmListTotal(listViewChosenRmToTransfer.getItems()));
    }

    @FXML
    private void removeAllRawMatToTransfer() {
        listViewTransferrableRm.getItems().addAll(listViewChosenRmToTransfer.getItems());
        listViewChosenRmToTransfer.getItems().removeAll(listViewChosenRmToTransfer.getItems());
        labelList1total.setText("Total: " + getRmListTotal(listViewTransferrableRm.getItems()));
        labelList2total.setText("Total: " + getRmListTotal(listViewChosenRmToTransfer.getItems()));
    }

}
