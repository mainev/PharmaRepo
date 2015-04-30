/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import rdr.client.controls.MyAutoCompleteTextField;
import rdr.client.controls.MyNotifications;
import rdr.client.entity.RawMaterial;
import rdr.client.entity.ReceivedRawMaterial;
import rdr.client.service.RawMaterialService;
import rdr.client.service.ReceivedRawMaterialService;
import rdr.client.service.TransferredRawMaterialService;
import rdr.client.utils.DateConverter;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ReceivedRawMaterialController implements Initializable {

    @FXML
    GridPane upperGrid;
//    @FXML
//    ListView<RawMaterial> listViewFilteredRM;
//    @FXML
//    TextField textFieldRM;
    @FXML
    TextField textFieldRRNo;
    @FXML
    TextField textFieldBatchNo;
    @FXML
    TextField textFieldPurchasedQty;
    @FXML
    ChoiceBox choiceBoxUnit;
    @FXML
    DatePicker datePickerExpirationDate;
    @FXML
    TextField textFieldReceivedRmAmountPerContainer;
    @FXML
    TextField textFieldUnitCost;

    @FXML
    TableView<ReceivedRawMaterial> receivedRMTable;
    //=========================================================
    //table columns
    @FXML
    private TableColumn<ReceivedRawMaterial, String> colRMCode;
    @FXML
    private TableColumn<ReceivedRawMaterial, String> colReceivedRmName;
    @FXML
    private TableColumn<ReceivedRawMaterial, String> colRRNum;
    @FXML
    private TableColumn<ReceivedRawMaterial, String> colBatchNum;
    @FXML
    private TableColumn<ReceivedRawMaterial, String> colDateReceived;
    @FXML
    private TableColumn<ReceivedRawMaterial, String> colExpirationDate;
    @FXML
    private TableColumn<ReceivedRawMaterial, String> colQuantity;
    @FXML
    private TableColumn<ReceivedRawMaterial, String> colUnit;
    @FXML
    private TableColumn<ReceivedRawMaterial, Double> colUnitCost;
    //=========================================================

    private static final RawMaterialService rmService = new RawMaterialService();
    private static final ReceivedRawMaterialService receivedRmService = new ReceivedRawMaterialService();
    private static final TransferredRawMaterialService transferredRmService = new TransferredRawMaterialService();
    MyAutoCompleteTextField rmTextField;
    ValidationSupport vs = new ValidationSupport();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

//        initTextFieldRMCode();
//        initRMListView();
        initReceivedRmTable();
        initChoiceBoxUnit();

        rmTextField = new MyAutoCompleteTextField(rmService.getAllRawMaterials());
        upperGrid.add(rmTextField, 1, 0);

        vs.registerValidator(textFieldRRNo, Validator.createEmptyValidator("Text is required"));
    }

    private void initChoiceBoxUnit() {
        choiceBoxUnit.setOnMouseClicked((e) -> {
           
            if (rmTextField.getSelectedItem() != null) {
                RawMaterial item = (RawMaterial) rmTextField.getSelectedItem();
                choiceBoxUnit.show();
                if (item.getClassification().equals("LIQUID")) {
                    choiceBoxUnit.setItems(liquidUnit);
                } else {
                    choiceBoxUnit.setItems(powderUnit);
                }
            }
        });

    }

    private void initReceivedRmTable() {
        receivedRMTable.setItems(receivedRmService.getAllReceivedRms());

        colRMCode.setCellValueFactory(c -> c.getValue().getRawMaterialId().codeProperty());
        colReceivedRmName.setCellValueFactory(c -> c.getValue().getRawMaterialId().nameProperty());
        colRRNum.setCellValueFactory(new PropertyValueFactory("rrNo"));
        colBatchNum.setCellValueFactory(new PropertyValueFactory("batchNo"));
        colDateReceived.setCellValueFactory(c -> c.getValue().dateReceivedProperty());
        colExpirationDate.setCellValueFactory(c -> c.getValue().expDateProperty());
        colQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        colUnit.setCellValueFactory(new PropertyValueFactory("unit"));
        colUnitCost.setCellValueFactory(new PropertyValueFactory("unitCost"));
    }

    private ObservableList<RawMaterial> rmList = FXCollections.observableArrayList();
    /*
     public void initTextFieldRMCode() {
     rmList = rmService.getAllRawMaterials();
     listViewFilteredRM.setItems(rmList);

     textFieldRM.focusedProperty().addListener((ob, ov, nv) -> {
     if (nv) {
     listViewFilteredRM.setVisible(true);
     listViewFilteredRM.getSelectionModel().clearSelection();
     } else {
     listViewFilteredRM.setVisible(false);
     listViewFilteredRM.getSelectionModel().clearSelection();
     }
     });
        
     textFieldRM.setOnMouseClicked(new EventHandler<MouseEvent>() {

     public void handle(MouseEvent me) {
     selectedRawMaterial = null;
     listViewFilteredRM.setVisible(true);
     listViewFilteredRM.getSelectionModel().clearSelection();

     }
     });

     textFieldRM.textProperty().addListener(new ChangeListener<String>() {

     @Override
     public void changed(ObservableValue<? extends String> observable,
     String oldValue, String newValue) {
     if (newValue.isEmpty()) {
     listViewFilteredRM.setVisible(false);
     } else if (oldValue.isEmpty() || !newValue.isEmpty()) {

     listViewFilteredRM.setVisible(true);
     listViewFilteredRM.getSelectionModel().clearSelection();

     //  Platform.runLater(() -> {
     ObservableList<RawMaterial> filteredProductList = rmListFilterer(rmList, newValue);

     listViewFilteredRM.setItems(filteredProductList);
     //  });
     }

     }
     });
     }
     */
    /*
     public void initRMListView() {

     listViewFilteredRM.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
     listViewFilteredRM.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RawMaterial>() {
     @Override
     public void changed(ObservableValue<? extends RawMaterial> observable,
     RawMaterial oldValue, RawMaterial newValue) {

     Platform.runLater(() -> {
     if (newValue != null) {
     selectedRawMaterial = newValue;
     textFieldRM.setText(newValue.getRawMatName());
     listViewFilteredRM.setVisible(false);
     listViewFilteredRM.getSelectionModel().clearSelection();
     Platform.runLater(() -> {
     configureRmUnitChoiceBox(newValue, choiceBoxUnit);
     });

     }
     });
     }
     });

     }
     */

    /*
     private ObservableList<RawMaterial> rmListFilterer(ObservableList<RawMaterial> rmList, String input) {
     ObservableList<RawMaterial> filteredRMList = FXCollections.observableArrayList();

     rmList.forEach(r -> {
     String rmCode = r.getRawMatCode().toLowerCase();
     String rmName = r.getRawMatName().toLowerCase();

     String inputString = input.toLowerCase();

     if (rmCode.contains(inputString) && !filteredRMList.contains(r)) {
     filteredRMList.add(r);
     }
     if (rmName.contains(inputString) && !filteredRMList.contains(r)) {
     filteredRMList.add(r);
     }

     });
     return filteredRMList;
     }
     */
    ObservableList<String> liquidUnit = FXCollections.observableArrayList();

    {
        liquidUnit.add("mcL");
        liquidUnit.add("mL");
        liquidUnit.add("L");
    }

    ObservableList<String> powderUnit = FXCollections.observableArrayList();

    {
        powderUnit.add("g");
        powderUnit.add("mcg");
        powderUnit.add("mg");
        powderUnit.add("kg");
    }

    @FXML
    private void clearAllReceivedRmField() {
        rmTextField.setSelectedItem(null);
        rmTextField.getTextField().setText("");
        textFieldRRNo.setText("");
        textFieldBatchNo.setText("");
        textFieldPurchasedQty.setText("");
        choiceBoxUnit.setValue(null);
        datePickerExpirationDate.setValue(null);
        textFieldReceivedRmAmountPerContainer.setText("");
        textFieldUnitCost.setText("");
    }

    /*
     public void configureRmUnitChoiceBox(RawMaterial selectedRM, ChoiceBox box) {
     if (selectedRM != null) {
     if (selectedRM.getClassification().equals("LIQUID")) {
     box.setItems(liquidUnit);
     } else {
     box.setItems(powderUnit);
     }
     }
     }
     */
    @FXML
    private void receiveRawMaterial() {
        String rrNum = textFieldRRNo.getText();
        String batchNum = textFieldBatchNo.getText();
        double quantity = Double.parseDouble(textFieldPurchasedQty.getText());
        String unit = (String) choiceBoxUnit.getValue();
        LocalDate expDate = datePickerExpirationDate.getValue();
        double amountPerContainer = Double.parseDouble(textFieldReceivedRmAmountPerContainer.getText());
        double unitCost = Double.parseDouble(textFieldUnitCost.getText());

        DateConverter dateConverter = new DateConverter();
        Date convertedExpDate = dateConverter.convertLocalDateToDate(expDate);
        ReceivedRawMaterial rrm = new ReceivedRawMaterial();
        rrm.setBatchNo(batchNum);
        rrm.setQuantity(quantity);
        rrm.setUnit(unit);
        rrm.setRrNo(rrNum);
        rrm.setDateReceived(new Date());
        rrm.setExpDate(convertedExpDate);
        rrm.setRawMaterialId((RawMaterial) rmTextField.getSelectedItem());
        rrm.setAmountPerContainer(amountPerContainer);
        rrm.setUnitCost(unitCost);

        rrm = receivedRmService.addReceivedRm(rrm);
        transferredRmService.generateContainer(rrm);
        receivedRMTable.setItems(receivedRmService.getAllReceivedRms());
        clearAllReceivedRmField();
        MyNotifications.displayInformation(rrm.getQuantity() + " " + rrm.getUnit() + " of " + rrm.getRawMaterialId().getName() + " received.");

    }
}
