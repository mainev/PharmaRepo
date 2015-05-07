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
import javafx.collections.ObservableList;
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
import mmd.client.entity.ReceivedPackagingMaterial;
import mmd.client.service.ReceivedPackagingMaterialService;
import mmd.client.utils.DateFormatter;

/**
 *
 * @author maine
 */
public class ReceivedPackagingMaterialController implements Initializable {
    
    //**************************TABLE ****************************************/
    @FXML
    private TableView<ReceivedPackagingMaterial> _tableViewReceivedPackagingMaterial;
    @FXML
    private TableColumn<ReceivedPackagingMaterial, String> _colCode;
    @FXML
    private TableColumn<ReceivedPackagingMaterial, String> _colDescription;
    @FXML
    private TableColumn<ReceivedPackagingMaterial, String> _colDateReceived;
    @FXML
    private TableColumn<ReceivedPackagingMaterial, String> _colRrNo;
    @FXML
    private TableColumn<ReceivedPackagingMaterial, Double> _colQuantity;
    @FXML
    private TableColumn<ReceivedPackagingMaterial, String> _colUnit;
    @FXML
    private TableColumn<ReceivedPackagingMaterial, String> _colQcControlNo;
    @FXML
    private TableColumn<ReceivedPackagingMaterial, String> _colReceivedBy;
    //***********************************************************************//
    
    private final ReceivedPackagingMaterialService receivedPackagingMaterialService = new ReceivedPackagingMaterialService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initializing rpm controller");
        
        initReceivedPackagingMaterialTableView();
    }
    
    private void initReceivedPackagingMaterialTableView() {
        
        _colCode.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPackagingMaterialId().getCode()));
        _colDescription.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPackagingMaterialId().getDescription()));
        _colDateReceived.setCellValueFactory(c -> new SimpleStringProperty(DateFormatter.convertToString(c.getValue().getDateReceived())));
        _colRrNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRrNo()));
        _colQuantity.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getQuantity()));
        _colUnit.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUnit()));
        _colQcControlNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getQcControlNo()));
        _colReceivedBy.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getReceivedBy()));
        
        ObservableList<ReceivedPackagingMaterial> receivedPackagingMaterialList = receivedPackagingMaterialService
                .getReceivedPackagingMaterialList();
        
        _tableViewReceivedPackagingMaterial.setItems(receivedPackagingMaterialList);
    }
    
    @FXML
    private void handleNewButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/_receivePackagingMaterialForm.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Receive Packaging Material");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            System.out.println("EXCEPTIONS: \n"+e.getMessage());

        }
    }
    
}
