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
import mmd.client.entity.PackagingMaterial;
import mmd.client.service.PackagingMaterialService;

/**
 *
 * @author maine
 */
public class ReceivePackagingMaterialFormController implements Initializable {

    @FXML
    GridPane _gridPaneReceiveForm;

    @FXML
    Button _buttonCancel;

    MyAutoCompleteTextField packagingMaterialTextField;
    PackagingMaterialService pmService = new PackagingMaterialService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initializing receive form controller...");

        configPackagingMaterialTextField();
    }

    private void configPackagingMaterialTextField() {
        ObservableList<PackagingMaterial> packagingMaterialList = pmService.getPackagingMaterialList();
        
        packagingMaterialTextField = new MyAutoCompleteTextField(FXCollections.observableArrayList(packagingMaterialList));
        _gridPaneReceiveForm.add(packagingMaterialTextField, 1, 0);
        packagingMaterialTextField.setTextFieldMargin(10, 10, 0, 10);
    }

    @FXML
    private void handleCancelButton() {
        Stage stage = (Stage) _buttonCancel.getScene().getWindow();
        stage.close();
    }
    
    

}
