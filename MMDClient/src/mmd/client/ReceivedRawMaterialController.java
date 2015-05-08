/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmd.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mmd.client.entity.ReceivedRawMaterial;
import mmd.client.service.ReceivedRawMaterialService;
import mmd.client.utils.DateFormatter;

/**
 *
 * @author maine
 */
public class ReceivedRawMaterialController implements Initializable {

    //*************************TABLE******************************************//
    @FXML
    TableView<ReceivedRawMaterial> _tableViewReceivedRawMaterial;
    @FXML
    TableColumn<ReceivedRawMaterial, String> _colCode;
    @FXML
    TableColumn<ReceivedRawMaterial, String> _colName;
    @FXML
    TableColumn<ReceivedRawMaterial, String> _colDateReceived;
    @FXML
    TableColumn<ReceivedRawMaterial, String> _colRrNo;
    @FXML
    TableColumn<ReceivedRawMaterial, Double> _colQuantity;
    @FXML
    TableColumn<ReceivedRawMaterial, String> _colUnit;
    @FXML
    TableColumn<ReceivedRawMaterial, String> _colBatchNo;
    @FXML
    TableColumn<ReceivedRawMaterial, String> _colQcControlNo;
    @FXML
    TableColumn<ReceivedRawMaterial, String> _colManufacturingDate;
    @FXML
    TableColumn<ReceivedRawMaterial, String> _colExpirationDate;
    @FXML
    TableColumn<ReceivedRawMaterial, String> _colReceivedBy;
    //**********************************************************************//

    private final ReceivedRawMaterialService receivedRawMaterialService = new ReceivedRawMaterialService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initializing rrm controller");
        initReceivedRawMaterialTableView();
    }

    @FXML
    private void handleNewButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/_receiveRawMaterialForm.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Receive Raw Material");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            System.out.println("EXCEPTIONS: \n" + e.getMessage());
        }
    }
    
    

    private void initReceivedRawMaterialTableView() {
        _tableViewReceivedRawMaterial.setItems(receivedRawMaterialService.getReceivedRawMaterialList());
        _colCode.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRawMaterialId().getCode()));
        _colName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRawMaterialId().getName()));
        _colDateReceived.setCellValueFactory(c -> new SimpleStringProperty(DateFormatter.convertToString(c.getValue().getDateReceived())));
        _colRrNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRrNo()));
        _colQuantity.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getQuantity()));
        _colUnit.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUnit()));
        _colBatchNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBatchNo()));
        _colQcControlNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getQcControlNo()));
        _colManufacturingDate.setCellValueFactory(c -> new SimpleStringProperty(DateFormatter.convertToString(c.getValue().getManufacturingDate())));
        _colExpirationDate.setCellValueFactory(c -> new SimpleStringProperty(DateFormatter.convertToString(c.getValue().getExpDate())));
        _colReceivedBy.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getReceivedBy()));

    }
}
