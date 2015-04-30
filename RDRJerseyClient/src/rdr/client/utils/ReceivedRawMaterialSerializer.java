/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import rdr.client.entity.ReceivedRawMaterial;

/**
 *
 * @author Admin
 */
public class ReceivedRawMaterialSerializer {
    
    //private final String pattern = "MM/dd/yyyy";
    private final String pattern = "yyyy-MM-dd'T'HH:mm:ss";
    public ReceivedRawMaterialSerializer() {       
        
    }
    
    public String serializeReceivedRm(ReceivedRawMaterial rrm){
    
       // Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        Gson gson = new GsonBuilder().setDateFormat(pattern).create();
        String json = gson.toJson(rrm);

       return json;
    }

    /**
     * Returns an ObservableList of received raw materials from json string
     * @param jsonOutput
     * @return 
     */
    public ObservableList<ReceivedRawMaterial> deserializeList(String jsonOutput) {
        ObservableList<ReceivedRawMaterial> observableReceivedRMList = FXCollections.observableArrayList();
        
        Gson gson = new GsonBuilder().setDateFormat(pattern).create();
        
        Type datasetListType = new TypeToken<Collection<ReceivedRawMaterial>>() {
        }.getType();
        List<ReceivedRawMaterial> datasets = gson.fromJson(jsonOutput, datasetListType);
        
        datasets.forEach(rm->observableReceivedRMList.add(rm));

        return observableReceivedRMList;
    }
    
     public ReceivedRawMaterial deserialize(String jsonOutput) {
        Gson gson = new GsonBuilder().setDateFormat(pattern).create();
        Type dataType = new TypeToken<ReceivedRawMaterial>() {
        }.getType();
        ReceivedRawMaterial data = gson.fromJson(jsonOutput, dataType);
        return data;
    }
}
