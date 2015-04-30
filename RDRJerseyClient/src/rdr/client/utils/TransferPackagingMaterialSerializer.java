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
import rdr.client.entity.TransferredPackagingMaterial;

/**
 *
 * @author jemuel
 */
public class TransferPackagingMaterialSerializer {
    
    public TransferPackagingMaterialSerializer(){
    
    }
    
    public String serializeList(List<TransferredPackagingMaterial> tpms){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        String json = gson.toJson(tpms);
        return json;
    }
    public String serializeTransferredPm(TransferredPackagingMaterial tpm){
    
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        String json = gson.toJson(tpm);

       return json;
    }
    public TransferredPackagingMaterial deserialize(String jsonOutput) {
 //       ObservableList<ReceivedRm> observableReceivedRMList = FXCollections.observableArrayList();
        
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        
        Type datasetListType = new TypeToken<TransferredPackagingMaterial>() {
        }.getType();
        TransferredPackagingMaterial transferredPm = gson.fromJson(jsonOutput, datasetListType);        
  
        return transferredPm;
    }
    
    public ObservableList<TransferredPackagingMaterial> deserializeList(String jsonOutput) {
        ObservableList<TransferredPackagingMaterial> observableTPMList = FXCollections.observableArrayList();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        Type datasetListType = new TypeToken<Collection<TransferredPackagingMaterial>>() {
        }.getType();
        
        List<TransferredPackagingMaterial> datasets = gson.fromJson(jsonOutput, datasetListType);
        
        datasets.forEach(tpm->observableTPMList.add(tpm));

        return observableTPMList;
    }
}
