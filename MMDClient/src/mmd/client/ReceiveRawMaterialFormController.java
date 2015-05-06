/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmd.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mmd.client.controls.MyAutoCompleteTextField;
import mmd.client.entity.RawMaterial;
import mmd.client.service.RawMaterialService;


public class ReceiveRawMaterialFormController implements Initializable {

    @FXML
    GridPane _gridPaneReceiveForm;

    @FXML
    Button _buttonCancel;

    MyAutoCompleteTextField rawMaterialTextField;
    RawMaterialService rmService = new RawMaterialService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initializing receive form controller...");

        configPackagingMaterialTextField();
    }

    private void configPackagingMaterialTextField() {
        ObservableList<RawMaterial> rawMaterialList = rmService.getRawMaterialList();
        
        rawMaterialTextField = new MyAutoCompleteTextField(FXCollections.observableArrayList(rawMaterialList));
        _gridPaneReceiveForm.add(rawMaterialTextField, 1, 0);
        rawMaterialTextField.setTextFieldMargin(10, 10, 0, 10);
    }

    @FXML
    private void handleCancelButton() {
        Stage stage = (Stage) _buttonCancel.getScene().getWindow();
        stage.close();
    }
    
    

}
