/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmd.client.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author maine
 */
public class MetricUnit {

    static ObservableList<String> liquidUnit = FXCollections.observableArrayList();
    {
        liquidUnit.add("mcL");
        liquidUnit.add("mL");
        liquidUnit.add("L");
    }

    static ObservableList<String> powderUnit = FXCollections.observableArrayList();
    {
        powderUnit.add("g");
        powderUnit.add("mcg");
        powderUnit.add("mg");
        powderUnit.add("kg");
    }
    
    public static ObservableList<String> getPowderUnit(){
        return powderUnit;
    }
    
    public static ObservableList<String> getLiquidUnit(){
        return liquidUnit;
    }
}
