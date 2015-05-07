/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmd.client;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mmd.client.controls.MyAutoCompleteTextField;
import mmd.client.entity.PackagingMaterial;
import mmd.client.service.PackagingMaterialService;
import mmd.client.service.ReceivedPackagingMaterialService;

/**
 *
 * @author maine
 */
public class ReceivePackagingMaterialFormController implements Initializable {

    @FXML
    TextField _textFieldRrNo;
    @FXML
    TextField _textFieldQcControlNo;
    @FXML
    TextField _textFieldQuantity;
    @FXML
    TextField _textFieldReceivedBy;
    @FXML
    GridPane _gridPaneReceiveForm;
    @FXML
    Button _buttonCancel;
    @FXML
    Button _buttonOk;

    MyAutoCompleteTextField packagingMaterialTextField;
    PackagingMaterialService packagingMaterialService = new PackagingMaterialService();
    ReceivedPackagingMaterialService receivedPackagingMaterialService = new ReceivedPackagingMaterialService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initializing receive form controller...");

        initPackagingMaterialTextField();
    }

    private void initPackagingMaterialTextField() {
        ObservableList<PackagingMaterial> packagingMaterialList = packagingMaterialService.getPackagingMaterialList();

        packagingMaterialTextField = new MyAutoCompleteTextField(FXCollections.observableArrayList(packagingMaterialList));
        _gridPaneReceiveForm.add(packagingMaterialTextField, 1, 0);
        packagingMaterialTextField.setTextFieldMargin(10, 10, 0, 10);
    }

    @FXML
    private void handleCancelButton() {
        Stage stage = (Stage) _buttonCancel.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void handleAddButton() {
        PackagingMaterial selectedPackagingMaterial = (PackagingMaterial) packagingMaterialTextField.getSelectedItem();
        String rrNo = _textFieldRrNo.getText();
        String qcControlNo = _textFieldQcControlNo.getText();
        Double quantity = Double.parseDouble(_textFieldQuantity.getText());
        String receivedBy = _textFieldReceivedBy.getText();
        Date dateReceived = new Date();
        //temporary unit
        String unit = "unit";

        receivedPackagingMaterialService.createReceivedPackagingMaterial(selectedPackagingMaterial, dateReceived,
                rrNo, qcControlNo, quantity, unit, receivedBy);

        Stage stage = (Stage) _buttonOk.getScene().getWindow();
        stage.close();
        ScreenNavigator.loadScreen(ScreenNavigator.RECEIVE_PACKAGING_MATERIAL_SCREEN);
    }

    @FXML
    private void handleAddAndRepeatButton() {
        PackagingMaterial selectedPackagingMaterial = (PackagingMaterial) packagingMaterialTextField.getSelectedItem();
        String rrNo = _textFieldRrNo.getText();
        String qcControlNo = _textFieldQcControlNo.getText();
        Double quantity = Double.parseDouble(_textFieldQuantity.getText());
        String receivedBy = _textFieldReceivedBy.getText();
        Date dateReceived = new Date();
        //temporary unit
        String unit = "unit";

        receivedPackagingMaterialService.createReceivedPackagingMaterial(selectedPackagingMaterial, dateReceived,
                rrNo, qcControlNo, quantity, unit, receivedBy);
        ScreenNavigator.loadScreen(ScreenNavigator.RECEIVE_PACKAGING_MATERIAL_SCREEN);
        clearAllFields();
    }

    private void clearAllFields() {
        packagingMaterialTextField.clearAll();
        _textFieldRrNo.setText("");
        _textFieldQcControlNo.setText("");
        _textFieldQuantity.setText("");
        _textFieldReceivedBy.setText("");
    }


}
