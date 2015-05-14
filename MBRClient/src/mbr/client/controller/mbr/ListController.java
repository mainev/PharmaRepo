/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.controller.mbr;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
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
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mbr.client.entity.MBR;
import mbr.client.service.MBRService;
import mbr.client.utils.DateConverter;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class ListController implements Initializable {

    @FXML
    HBox _headerHBox;

    @FXML
    TableView<MBR> _tableViewMBRList;
    @FXML
    TableColumn<MBR, String> _colProductCode;
    @FXML
    TableColumn<MBR, String> _colProductBrandName;
    @FXML
    TableColumn<MBR, String> _colBatchNo;
    @FXML
    TableColumn<MBR, String> _colBatchSize;
    @FXML
    TableColumn<MBR, LocalDate> _colMfgDate;
    @FXML
    TableColumn<MBR, LocalDate> _colExpDate;
    @FXML
    TableColumn<MBR, String> _colPoNo;
    @FXML
    TableColumn<MBR, String> _colPackSize;

    MBRService mbrService = new MBRService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initMBRListTableView();
    }

    private void initMBRListTableView() {
        _colProductCode.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getProductWithPackSizeId().getProductId().getCode()));
        _colProductBrandName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getProductWithPackSizeId().getProductId().getBrandName()));
        _colBatchNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBatchNo()));
        _colBatchSize.setCellValueFactory(c -> new SimpleStringProperty(""+c.getValue().getBatchSize()+" "+c.getValue().getUnit()));
        _colMfgDate.setCellValueFactory(c -> new SimpleObjectProperty(DateConverter.convertDateToLocalDate(c.getValue().getMfgDate())));
        _colExpDate.setCellValueFactory(c -> new SimpleObjectProperty(DateConverter.convertDateToLocalDate(c.getValue().getExpDate())));
        _colPoNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPoNo()));
        _colPackSize.setCellValueFactory(c -> new SimpleStringProperty(""+c.getValue().getBatchSize()+" "+c.getValue().getUnit()));
       
        ObservableList<MBR> mbrList = mbrService.getMBRList();
        _tableViewMBRList.setItems(mbrList);
    }

    @FXML
    public void handleNewButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mbr/client/view/mbr/new.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Create New MBR");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("EXCEPTIONS: \n" + e.getMessage());
        }
    }

}
