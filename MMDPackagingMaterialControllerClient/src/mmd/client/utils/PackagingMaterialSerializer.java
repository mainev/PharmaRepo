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
import rdr.client.entity.PackagingMaterial;
/**
 *
 * @author jemuel
 */
public class PackagingMaterialSerializer {
    
    public PackagingMaterialSerializer() {
        
    }

    /**
     * Returns an ObservableList of raw materials from json string
     * @param jsonOutput
     * @return 
     */
    public ObservableList<PackagingMaterial> deserializeList(String jsonOutput) {
        ObservableList<PackagingMaterial> observablePMList = FXCollections.observableArrayList();
        Gson gson = new Gson();
        Type datasetListType = new TypeToken<Collection<PackagingMaterial>>() {
        }.getType();
        List<PackagingMaterial> datasets = gson.fromJson(jsonOutput, datasetListType);
        
        datasets.forEach(pm->observablePMList.add(pm));

        return observablePMList;
    }
    
     public String serialize(PackagingMaterial pm){
    
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        String json = gson.toJson(pm);

       return json;
    }
}
