/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client;

import mbr.client.controller.MainController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author maine
 */
public class ScreenNavigator {

    public static final String MAIN = "view/Main.fxml";
     public static final String WELCOME_SCREEN = "view/welcome.fxml";
    public static final String MBR_LIST_SCREEN = "view/mbr/list.fxml";

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
         //   e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
