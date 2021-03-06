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
import rdr.client.entity.RawMaterial;

/**
 *
 * @author Admin
 */
public class RawMaterialSerializer {

    public RawMaterialSerializer() {
    }

    /**
     * Returns an ObservableList of raw materials from json string
     * @param jsonOutput
     * @return 
     */
    public ObservableList<RawMaterial> deserializeList(String jsonOutput) {
        ObservableList<RawMaterial> observableRMList = FXCollections.observableArrayList();
        Gson gson = new Gson();
        Type datasetListType = new TypeToken<Collection<RawMaterial>>() {
        }.getType();
        List<RawMaterial> datasets = gson.fromJson(jsonOutput, datasetListType);
        
        datasets.forEach(rm->observableRMList.add(rm));

        return observableRMList;
    }
    
     public String serialize(RawMaterial rm){
    
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        String json = gson.toJson(rm);

       return json;
    }
}
