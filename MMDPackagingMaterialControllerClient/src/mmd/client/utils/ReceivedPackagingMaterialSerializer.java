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
import rdr.client.entity.ReceivedPackagingMaterial;

/**
 *
 * @author jemuel
 */
public class ReceivedPackagingMaterialSerializer {
       private final String pattern = "yyyy-MM-dd'T'HH:mm:ss";
    public ReceivedPackagingMaterialSerializer() {       
        
    }
    
    public String serializeReceivedPm(ReceivedPackagingMaterial rpm){
    
        Gson gson = new GsonBuilder().setDateFormat(pattern).create();
        String json = gson.toJson(rpm);

       return json;
    }

    /**
     * Returns an ObservableList of received raw materials from json string
     * @param jsonOutput
     * @return 
     */
    public ObservableList<ReceivedPackagingMaterial> deserializeList(String jsonOutput) {
        ObservableList<ReceivedPackagingMaterial> observableReceivedPMList = FXCollections.observableArrayList();
        
        Gson gson = new GsonBuilder().setDateFormat(pattern).create();
        
        Type datasetListType = new TypeToken<Collection<ReceivedPackagingMaterial>>() {
        }.getType();
        List<ReceivedPackagingMaterial> datasets = gson.fromJson(jsonOutput, datasetListType);
        
        datasets.forEach(pm->observableReceivedPMList.add(pm));

        return observableReceivedPMList;
    }
    
     public ReceivedPackagingMaterial deserialize(String jsonOutput) {
        Gson gson = new GsonBuilder().setDateFormat(pattern).create();
        Type dataType = new TypeToken<ReceivedPackagingMaterial>() {
        }.getType();
        ReceivedPackagingMaterial data = gson.fromJson(jsonOutput, dataType);
        return data;
    }
}
