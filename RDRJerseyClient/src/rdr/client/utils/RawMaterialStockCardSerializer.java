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
import rdr.client.domain.RawMaterialStockCard;

/**
 *
 * @author Admin
 */
public class RawMaterialStockCardSerializer {
    
      public RawMaterialStockCardSerializer() {
    }

    /**
     * Returns an ObservableList of transferred raw materials from json string
     *
     * @param jsonOutput
     * @return
     */
    public ObservableList<RawMaterialStockCard> deserializeList(String jsonOutput) {
        ObservableList<RawMaterialStockCard> observableStockCardList = FXCollections.observableArrayList();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        Type datasetListType = new TypeToken<Collection<RawMaterialStockCard>>() {
        }.getType();
        List<RawMaterialStockCard> datasets = gson.fromJson(jsonOutput, datasetListType);

        datasets.forEach(rm -> observableStockCardList.add(rm));

        return observableStockCardList;
    }

    public RawMaterialStockCard deserialize(String jsonOutput) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        Type dataType = new TypeToken<RawMaterialStockCard>() {
        }.getType();
        RawMaterialStockCard data = gson.fromJson(jsonOutput, dataType);
        return data;
    }

    public String serialize(RawMaterialStockCard trm) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        String json = gson.toJson(trm);

        return json;
    }
    
    public String serializeList(List<RawMaterialStockCard> trmList){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        String json = gson.toJson(trmList);

        return json;
    }
}
