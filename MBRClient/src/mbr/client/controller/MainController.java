/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mbr.client.ScreenNavigator;

/**
 *
 * @author maine
 */
public class MainController implements Initializable {

    @FXML
    StackPane _mainStackPane;
    @FXML
    ToggleButton _menuButtonMBR;

    final ToggleGroup menuToggleGroup = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setMenuButtonToggleGroup();
        addToggleGroupListener();

    }

    private void addToggleGroupListener() {
        _menuButtonMBR.setUserData(ScreenNavigator.MBR_LIST_SCREEN);

        menuToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle toggle, Toggle new_toggle) {
                if (new_toggle == null) {
                    ScreenNavigator.loadScreen(ScreenNavigator.WELCOME_SCREEN);
                } else {
                    ScreenNavigator.loadScreen((String) menuToggleGroup.getSelectedToggle().getUserData());
                }
            }
        });
    }

    private void setMenuButtonToggleGroup() {
        _menuButtonMBR.setToggleGroup(menuToggleGroup);
    }

    public void createNewMBR() {
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

    public void setScreen(Node node) {
        _mainStackPane.getChildren().setAll(node);
    }

    public void closeApp() {
        Stage stage = (Stage) _mainStackPane.getScene().getWindow();
        stage.close();
    }

}
