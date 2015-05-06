/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmd.client;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import mmd.client.MainController;

/**
 *
 * @author maine
 */
public class ScreenNavigator {

    public static final String MAIN = "view/Main.fxml";
    public static final String RECEIVE_PACKAGING_MATERIAL_SCREEN = "view/ReceivedPackagingMaterial.fxml";
    public static final String RECEIVE_RAW_MATERIAL_SCREEN = "view/ReceivedRawMaterial.fxml";

    private static MainController mainController;

    public static void setMainController(MainController mainController) {
        ScreenNavigator.mainController = mainController;
    }

    public static void loadScreen(String fxml) {
        try {
            mainController.setScreen(
                    FXMLLoader.load(
                            ScreenNavigator.class.getResource(fxml)
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
