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
import rdr.client.entity.TransferredRawMaterial;

/**
 *
 * @author Admin
 */
public class TransferredRawMaterialSerializer {

      //private final String pattern = "MM/dd/yyyy";
    private final String pattern = "yyyy-MM-dd'T'HH:mm:ss";
    
    public TransferredRawMaterialSerializer() {
    }

    /**
     * Returns an ObservableList of transferred raw materials from json string
     *
     * @param jsonOutput
     * @return
     */
    public ObservableList<TransferredRawMaterial> deserializeList(String jsonOutput) {
        ObservableList<TransferredRawMaterial> observableTransferredRmList = FXCollections.observableArrayList();

        Gson gson = new GsonBuilder().setDateFormat(pattern).create();

        Type datasetListType = new TypeToken<Collection<TransferredRawMaterial>>() {
        }.getType();
        List<TransferredRawMaterial> datasets = gson.fromJson(jsonOutput, datasetListType);

        datasets.forEach(rm -> observableTransferredRmList.add(rm));

        return observableTransferredRmList;
    }

    public TransferredRawMaterial deserialize(String jsonOutput) {
        Gson gson = new GsonBuilder().setDateFormat(pattern).create();
        Type dataType = new TypeToken<TransferredRawMaterial>() {
        }.getType();
        TransferredRawMaterial data = gson.fromJson(jsonOutput, dataType);
        return data;
    }

    public String serialize(TransferredRawMaterial trm) {

        Gson gson = new GsonBuilder().setDateFormat(pattern).create();
        String json = gson.toJson(trm);

        return json;
    }
    
    public String serializeList(List<TransferredRawMaterial> trmList){
        Gson gson = new GsonBuilder().setDateFormat(pattern).create();
        String json = gson.toJson(trmList);

        return json;
    }
}
