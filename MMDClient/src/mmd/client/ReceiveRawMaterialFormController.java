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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mmd.client.controls.MyAutoCompleteTextField;
import mmd.client.entity.RawMaterial;
import mmd.client.service.RawMaterialService;
import mmd.client.service.ReceivedRawMaterialService;
import mmd.client.utils.DateConverter;
import mmd.client.utils.MetricUnit;

/**
 *
 * @author maine
 */
public class ReceiveRawMaterialFormController implements Initializable {

    @FXML
    ChoiceBox _choiceBoxUnit;
    @FXML
    DatePicker _datePickerManufacturingDate;
    @FXML
    DatePicker _datePickerExpirationDate;
    @FXML
    TextField _textFieldRrNo;
    @FXML
    TextField _textFieldBatchNo;
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

    MyAutoCompleteTextField rawMaterialTextField;
    RawMaterialService rawMaterialService = new RawMaterialService();
    ReceivedRawMaterialService receivedRawMaterialService = new ReceivedRawMaterialService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initRawMaterialTextField();
        configChoiceBoxItem();
    }

    private void configChoiceBoxItem() {
        _choiceBoxUnit.setOnMouseClicked((e) -> {

            if (rawMaterialTextField.getSelectedItem() != null) {
                RawMaterial item = (RawMaterial) rawMaterialTextField.getSelectedItem();
                _choiceBoxUnit.show();
                if (item.getClassification().equals("LIQUID")) {
                    _choiceBoxUnit.setItems(MetricUnit.getLiquidUnit());
                } else {
                    _choiceBoxUnit.setItems(MetricUnit.getPowderUnit());
                }
            } else {
                _choiceBoxUnit.setItems(MetricUnit.getLiquidUnit());
            }
        });

    }

    private void initRawMaterialTextField() {
        ObservableList<RawMaterial> rawMaterialList = rawMaterialService.getRawMaterialList();

        rawMaterialTextField = new MyAutoCompleteTextField(FXCollections.observableArrayList(rawMaterialList));
        _gridPaneReceiveForm.add(rawMaterialTextField, 1, 0);
        rawMaterialTextField.setTextFieldMargin(10, 10, 0, 10);
    }

    @FXML
    private void handleAddButton() {
        RawMaterial rawMaterial = (RawMaterial) rawMaterialTextField.getSelectedItem();
        String rrNo = _textFieldRrNo.getText();
        String qcControlNo = _textFieldQcControlNo.getText();
        Double quantity = Double.parseDouble(_textFieldQuantity.getText());
        String receivedBy = _textFieldReceivedBy.getText();
        Date dateReceived = new Date();
        Date manufacturingDate = DateConverter.convertLocalDateToDate(_datePickerManufacturingDate.getValue());
        Date expirationDate = DateConverter.convertLocalDateToDate(_datePickerExpirationDate.getValue());
        String batchNo = _textFieldBatchNo.getText();
        //temporary unit
        String unit = "unit";

        receivedRawMaterialService.createReceivedRawMaterial(rawMaterial, dateReceived, quantity, unit,
                batchNo, qcControlNo, manufacturingDate, expirationDate, rrNo, receivedBy);

        Stage stage = (Stage) _buttonOk.getScene().getWindow();
        stage.close();
        ScreenNavigator.loadScreen(ScreenNavigator.RECEIVE_RAW_MATERIAL_SCREEN);

    }

    @FXML
    private void handleAddAndRepeatButton() {
        RawMaterial rawMaterial = (RawMaterial) rawMaterialTextField.getSelectedItem();
        String rrNo = _textFieldRrNo.getText();
        String qcControlNo = _textFieldQcControlNo.getText();
        Double quantity = Double.parseDouble(_textFieldQuantity.getText());
        String receivedBy = _textFieldReceivedBy.getText();
        Date dateReceived = new Date();
        Date manufacturingDate = DateConverter.convertLocalDateToDate(_datePickerManufacturingDate.getValue());
        Date expirationDate = DateConverter.convertLocalDateToDate(_datePickerExpirationDate.getValue());
        String batchNo = _textFieldBatchNo.getText();
        //temporary unit

        String unit = "unit";

        receivedRawMaterialService.createReceivedRawMaterial(rawMaterial, dateReceived, quantity, unit,
                batchNo, qcControlNo, manufacturingDate, expirationDate, rrNo, receivedBy);
        ScreenNavigator.loadScreen(ScreenNavigator.RECEIVE_RAW_MATERIAL_SCREEN);
        clearAllFields();
    }

    @FXML
    private void handleCancelButton() {
        Stage stage = (Stage) _buttonCancel.getScene().getWindow();
        stage.close();
    }

    private void clearAllFields() {
        rawMaterialTextField.clearAll();
        _textFieldRrNo.setText("");
        _textFieldQcControlNo.setText("");
        _textFieldQuantity.setText("");
        _textFieldReceivedBy.setText("");
        _datePickerManufacturingDate.setValue(null);
        _datePickerExpirationDate.setValue(null);
        _textFieldBatchNo.setText("");
    }

}
