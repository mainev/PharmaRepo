/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.controller.mbr;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mbr.client.ScreenNavigator;
import mbr.client.controls.TextFieldWithSearch;
import mbr.client.entity.PackagingSize;
import mbr.client.entity.Product;
import mbr.client.service.MBRService;
import mbr.client.service.ProductService;
import mbr.client.utils.DateConverter;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class NewController implements Initializable {

    @FXML
    GridPane _gridPaneInput;

    @FXML
    TextField _textFieldBatchNo;
    @FXML
    TextField _textFieldBatchSize;
    @FXML
    ChoiceBox<String> _choiceBoxBatchSizeUnit;
    @FXML
    DatePicker _datePickerMfgDate;
    @FXML
    DatePicker _datePickerExpDate;
    @FXML
    TextField _textFieldPoNo;
    @FXML
    ChoiceBox<PackagingSize> _choiceBoxPackSize;

    @FXML
    VBox _vBoxProductSelection;
    TextFieldWithSearch<Product> textFieldWithProductSearch;
    ProductService productService = new ProductService();
    MBRService mbrService = new MBRService();
    Product selectedProduct;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initTextFieldWithProductSearch();

    }

    private void initTextFieldWithProductSearch() {
        ObservableList<Product> productList = productService.getProductList();
        textFieldWithProductSearch = new TextFieldWithSearch(productList);
        textFieldWithProductSearch.setAlignment(Pos.CENTER);
        _gridPaneInput.add(textFieldWithProductSearch, 1, 0);

        textFieldWithProductSearch.listViewSelectedItemProperty().addListener((ob, ov, nv) -> {
            if (nv != null) {
                selectedProduct = (Product) nv;
                _choiceBoxPackSize.setItems(selectedProduct.packagingSizeCollectionProperty());
                
                if(selectedProduct.getClassification().equals("LIQUID")){
                _choiceBoxBatchSizeUnit.setItems(FXCollections.observableArrayList("mL", "L"));
                }
                else{
                 _choiceBoxBatchSizeUnit.setItems(FXCollections.observableArrayList("mg","g", "kg"));
                }
            }
        });

    }
    
    @FXML
    private void handleAddButton(){
        String batchNo = _textFieldBatchNo.getText();
        Double batchSize = Double.parseDouble(_textFieldBatchSize.getText());
        String unit = _choiceBoxBatchSizeUnit.getValue();
        PackagingSize packagingSize = _choiceBoxPackSize.getValue();
        Date mfgDate = DateConverter.convertLocalDateToDate(_datePickerMfgDate.getValue());
        Date expDate = DateConverter.convertLocalDateToDate(_datePickerExpDate.getValue());
        String poNo = _textFieldPoNo.getText();
        
        mbrService.createMBR(selectedProduct,packagingSize, batchSize, batchNo, unit, mfgDate, expDate, poNo);
    
        Stage stage = (Stage) _gridPaneInput.getScene().getWindow();
        stage.close();
        ScreenNavigator.loadScreen(ScreenNavigator.MBR_LIST_SCREEN);
    }

    @FXML
    private void handleCancelButton() {
        Stage stage = (Stage) _gridPaneInput.getScene().getWindow();
        stage.close();
    }

   

}
