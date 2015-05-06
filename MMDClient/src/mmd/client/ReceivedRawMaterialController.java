/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmd.client;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author maine
 */
public class ReceivedRawMaterialController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initializing rrm controller");
    }
    
    @FXML
    private void handleNewReceiveButton() {
        Date now = new Date();
        Timestamp ts = new Timestamp(now.getTime());

        System.out.println(ts);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/_receiveRawMaterialForm.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Receive Raw Material");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  
}
