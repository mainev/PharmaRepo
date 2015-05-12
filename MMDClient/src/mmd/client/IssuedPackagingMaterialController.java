/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmd.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mmd.client.entity.IssuedPackagingMaterial;

/**
 *
 * @author maine
 */
public class IssuedPackagingMaterialController implements Initializable {

    //*************************TABLE******************************************/
    @FXML
    TableView<IssuedPackagingMaterial> _tableViewIssuedPackagingMaterial;
    @FXML
    TableColumn<IssuedPackagingMaterial, String> _colCode;
    @FXML
    TableColumn<IssuedPackagingMaterial, String> _colDescription;
    @FXML
    TableColumn<IssuedPackagingMaterial, String> _colDateIssued;
    @FXML
    TableColumn<IssuedPackagingMaterial, String> _colProduct;
    @FXML
    TableColumn<IssuedPackagingMaterial, Double> _colQuantity;
    @FXML
    TableColumn<IssuedPackagingMaterial, String> _colUnit;
    @FXML
    TableColumn<IssuedPackagingMaterial, String> _colIssuedBy;

    //************************************************************************//
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handleNewButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/_transactPackagingMaterialForm.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Packaging Material Transaction");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            System.out.println("EXCEPTIONS: \n" + e.getMessage());
        }
        
        
    }
}
