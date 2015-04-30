/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Notifications;
import rdr.client.controls.MyNotifications;
import rdr.client.entity.ReceivedRawMaterial;
import rdr.client.entity.TransferredRawMaterial;
import rdr.client.service.TransferredRawMaterialService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class StockCardController implements Initializable {

    @FXML
    TextField textFieldRmCode;
    @FXML
    TextField textFieldRRNo;

    @FXML
    Label labelStockcardRawMat;
    @FXML
    Label labelRRCode;
    @FXML
    Label labelDateReceived;
    @FXML
    Label labelExpDate;
    @FXML
    Label labelBatchNo;
    @FXML
    Label labelPurchases;

    //=======================Table Transferred RM==============================
    @FXML
    TableView<TransferredRawMaterial> tableViewTransferredRm;

    @FXML
    TableColumn<TransferredRawMaterial, String> colDateTransferred;
    @FXML
    TableColumn<TransferredRawMaterial, String> colTos;
    @FXML
    TableColumn<TransferredRawMaterial, String> colSo;
    @FXML
    TableColumn<TransferredRawMaterial, String> colProduct;
    @FXML
    TableColumn<TransferredRawMaterial, Double> colQuantity;
    @FXML
    TableColumn<TransferredRawMaterial, String> colUnit;

    //=========================================================================
    @FXML
    AnchorPane anchorPaneRrFields;
    @FXML
    AnchorPane anchorPaneTransferredRm;
   
    private static final TransferredRawMaterialService transferredRmService = new TransferredRawMaterialService();
    private ObservableList<TransferredRawMaterial> trmList = FXCollections.observableArrayList();
   // SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initTransferredRmTableView();
    }

    private void initTransferredRmTableView() {
        tableViewTransferredRm.setItems(trmList);
        colDateTransferred.setCellValueFactory(c -> c.getValue().dateTransferredProperty());
        colTos.setCellValueFactory(new PropertyValueFactory("tosNo"));
        colSo.setCellValueFactory(new PropertyValueFactory("soNo"));
        colProduct.setCellValueFactory(c -> c.getValue().getProductId().brandNameProperty());
        colQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        colUnit.setCellValueFactory(new PropertyValueFactory("unit"));

    }

    @FXML
    private void handleSearchButton(ActionEvent e) {
        if (textFieldRmCode.getText() != null && textFieldRRNo.getText() != null) {
            String rmCode = textFieldRmCode.getText();
            String rrNo = textFieldRRNo.getText();

            trmList = transferredRmService.searchTransferredRm(rmCode, rrNo);
            if (!trmList.isEmpty()) {
              
                ReceivedRawMaterial rrInstance = trmList.get(0).getReceivedRmId();

                updateRRFields(rrInstance);
                tableViewTransferredRm.setItems(trmList);
               
            } else {
                //notification.setText("No results found.");
                clearAllRRFields();
                MyNotifications.displayError("No results found.");
                
            }
        }
    }

    private void updateRRFields(ReceivedRawMaterial rrInstance) {
        labelStockcardRawMat.setText(rrInstance.getRawMaterialId().getName());
        labelRRCode.setText(rrInstance.getRawMaterialId().getCode() + "-" + rrInstance.getRrNo());
        labelDateReceived.setText(rrInstance.dateReceivedProperty().getValue());
        labelExpDate.setText(rrInstance.expDateProperty().getValue());
        labelBatchNo.setText(rrInstance.getBatchNo());
        labelPurchases.setText(String.valueOf(rrInstance.getQuantity()) + " " + rrInstance.getUnit());
    }

    private void clearAllRRFields() {
        labelStockcardRawMat.setText("");
        labelRRCode.setText("");
        labelDateReceived.setText("");
        labelExpDate.setText("");
        labelBatchNo.setText("");
        labelPurchases.setText("");
    }
}
