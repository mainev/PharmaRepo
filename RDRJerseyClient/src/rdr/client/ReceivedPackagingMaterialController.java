/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import rdr.client.controls.MyAutoCompleteTextField;
import rdr.client.controls.MyNotifications;
import rdr.client.entity.PackagingMaterial;
import rdr.client.entity.ReceivedPackagingMaterial;
import rdr.client.entity.TransferredPackagingMaterial;
import rdr.client.service.PackagingMaterialService;
import rdr.client.service.ReceivedPackagingMaterialService;
import rdr.client.service.TransferredPackagingMaterialService;

/**
 * FXML Controller class
 *
 * @author jemuel
 */
public class ReceivedPackagingMaterialController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    TextField textFieldRRNoReceivedPm;
    @FXML
    TextField textFieldSupplierReceivedPm;
    @FXML
    TextField textFieldDRNoReceivedPm;
    @FXML
    TextField textFieldPONoReceivedPm;
    @FXML
    TextField textFieldQuantityReceivedPm;
    @FXML
    TextField textFieldQuantityPerBundleReceivedPm;
    @FXML
    TextField textFieldUnitCost;
   
    
    //table columns for RECEIVED PACKAGING MATERIAL
    @FXML
    private TableColumn<ReceivedPackagingMaterial, String> colPMCodeReceivedPm;
    @FXML
    private TableColumn<ReceivedPackagingMaterial, String> colRRNumReceivedPm;
    @FXML
    private TableColumn<ReceivedPackagingMaterial, String> colDRNumReceivedPm;
    @FXML
    private TableColumn<ReceivedPackagingMaterial, String> colSupplierReceivedPm;
    @FXML
    private TableColumn<ReceivedPackagingMaterial, String> colPONumReceivedPm;
    @FXML
    private TableColumn<ReceivedPackagingMaterial, Integer> colQuantityReceivedPm;
    @FXML
    private TableColumn<ReceivedPackagingMaterial, Date> colDateReceivedReceivedPm;
    @FXML
    private TableColumn<ReceivedPackagingMaterial, String> colPMDescriptionReceivedPm;
    
    @FXML
    TableView<ReceivedPackagingMaterial> receivedPMTable;
    
    private static final PackagingMaterialService pmService = new PackagingMaterialService();
    private static final ReceivedPackagingMaterialService receivedPmService =  new ReceivedPackagingMaterialService();
    ObservableList<ReceivedPackagingMaterial> allReceivedPms = receivedPmService.getAllReceivedPms();
   
    MyAutoCompleteTextField packMatTextField;
    @FXML
    GridPane upperGrid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        initReceivedPMTable();
        
        packMatTextField = new MyAutoCompleteTextField(pmService.getAllPackagingMaterials());
        upperGrid.add(packMatTextField, 1, 0);
    }
    
       @FXML
    ListView<PackagingMaterial> listViewFilteredPM;
  
 
    @FXML
    public void receivePackagingMaterial() {
        String rrNum = textFieldRRNoReceivedPm.getText();
        String supplier = textFieldSupplierReceivedPm.getText();
        String drNum = textFieldDRNoReceivedPm.getText();
        String poNo = textFieldPONoReceivedPm.getText(); 
        Integer quantity = Integer.parseInt(textFieldQuantityReceivedPm.getText());
        Double unitCost = Double.parseDouble(textFieldUnitCost.getText());
//        String unit = (String) choiceBoxUnitReceivedPm.getValue();
        Integer quantityPerBundle = Integer.parseInt(textFieldQuantityPerBundleReceivedPm.getText());

        ReceivedPackagingMaterial rpm = new ReceivedPackagingMaterial();
        
        rpm.setQuantity(quantity);
   //     rpm.setUnit(unit);
        rpm.setRrNo(rrNum);
        rpm.setPoNo(poNo);
        rpm.setSupplier(supplier);
        rpm.setDrNo(drNum);
        rpm.setPackagingMaterialId((PackagingMaterial)packMatTextField.getSelectedItem());
        rpm.setDateReceived(new Date());
        rpm.setQuantityPerBundle(quantityPerBundle);
        rpm.setUnitCost(unitCost);
        
        //to avoid "division by zero" exception
        double numberOfBundles=0;
        if(quantityPerBundle!=0)
           numberOfBundles = quantity/quantityPerBundle;
        
        ObservableList<TransferredPackagingMaterial> transferredPmBundles = FXCollections.observableArrayList();
        
        TransferredPackagingMaterialService tpms = new TransferredPackagingMaterialService();
        TransferredPackagingMaterial tpm;
     
        int sobra = quantity%quantityPerBundle;
        ReceivedPackagingMaterial receivedPm;
        if((quantityPerBundle <= quantity) && (quantityPerBundle>0)){
         
            System.out.println("quantityPerBundle is VALID.");
            receivedPm = receivedPmService.addReceivedPm(rpm);
                               allReceivedPms.add(receivedPm);
                               MyNotifications.displayInformation("Packaging material received.");
            if(sobra>0){
                tpm = new TransferredPackagingMaterial();
                tpm.setQuantity(sobra);
                tpm.setReceivedPmId(receivedPm);
                tpm.setStatus("QUARANTINE");
                tpm.setIsTransferred(Boolean.FALSE);
                tpm.setBundleNo((short)++numberOfBundles);
                
                transferredPmBundles.add(tpm);
//                tpms.addTransferredPm(tpm);
                --numberOfBundles;
            }
                
            while(numberOfBundles>=1){
                tpm = new TransferredPackagingMaterial();
                tpm.setQuantity(quantityPerBundle);
                tpm.setReceivedPmId(receivedPm);
                tpm.setStatus("QUARANTINE");
                tpm.setIsTransferred(Boolean.FALSE);
                tpm.setBundleNo((short)numberOfBundles--);
                transferredPmBundles.add(tpm);
//                tpms.addTransferredPm(tpm);           
            }
            tpms.generateBundles(transferredPmBundles);
        }
        else{
            MyNotifications.displayWarning("Quantity per bundle is not valid.");
          
        }
        
        
  //      clearAllReceivedRmField();
    }
    
     public void initReceivedPMTable(){
        colPMCodeReceivedPm.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPackagingMaterialId().getCode()) );
        colRRNumReceivedPm.setCellValueFactory(new PropertyValueFactory("rrNo"));
        colDRNumReceivedPm.setCellValueFactory(new PropertyValueFactory("drNo"));
        colSupplierReceivedPm.setCellValueFactory(new PropertyValueFactory("supplier"));
        colPONumReceivedPm.setCellValueFactory(new PropertyValueFactory("poNo"));
        colQuantityReceivedPm.setCellValueFactory(new PropertyValueFactory("quantity"));
        colPMDescriptionReceivedPm.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPackagingMaterialId().getDescription()) );
       
        receivedPMTable.setItems(allReceivedPms);
        
    
    }

    
}
