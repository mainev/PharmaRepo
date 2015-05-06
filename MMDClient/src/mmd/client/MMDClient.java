/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmd.client;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author maine
 */
public class MMDClient extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       /*
        Parent root = FXMLLoader.load(getClass().getResource("view/Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
        stage.setTitle("MMD Management System");
        */
        
        stage.setTitle("MMD Management System");

        stage.setScene(
            createScene(
                loadMainPane()
            )
        );

        stage.show();

    }
    
    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        AnchorPane mainPane = (AnchorPane) loader.load(
            getClass().getResourceAsStream(
                ScreenNavigator.MAIN
            )
        );

        MainController mainController = loader.getController();

        ScreenNavigator.setMainController(mainController);
       // ScreenNavigator.loadScreen(ScreenNavigator.RECEIVE_PACKAGING_MATERIAL_SCREEN);

        return mainPane;
    }
    
     private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(
            mainPane
        );
        return scene;
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
