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
import rdr.client.domain.PackagingMaterialStockCard;

/**
 *
 * @author Admin
 */
public class PackagingMaterialStockCardSerializer {

    private final String pattern = "yyyy-MM-dd'T'HH:mm:ss";

    public PackagingMaterialStockCardSerializer() {
    }

    /**
     * Returns an ObservableList of transferred raw materials from json string
     *
     * @param jsonOutput
     * @return
     */
    public ObservableList<PackagingMaterialStockCard> deserializeList(String jsonOutput) {
        ObservableList<PackagingMaterialStockCard> observableStockCardList = FXCollections.observableArrayList();

        Gson gson = new GsonBuilder().setDateFormat(pattern).create();

        Type datasetListType = new TypeToken<Collection<PackagingMaterialStockCard>>() {
        }.getType();
        List<PackagingMaterialStockCard> datasets = gson.fromJson(jsonOutput, datasetListType);

        datasets.forEach(pm -> observableStockCardList.add(pm));

        return observableStockCardList;
    }

    public PackagingMaterialStockCard deserialize(String jsonOutput) {
        Gson gson = new GsonBuilder().setDateFormat(pattern).create();
        Type dataType = new TypeToken<PackagingMaterialStockCard>() {
        }.getType();
        PackagingMaterialStockCard data = gson.fromJson(jsonOutput, dataType);
        return data;
    }

    public String serialize(PackagingMaterialStockCard tpm) {
        Gson gson = new GsonBuilder().setDateFormat(pattern).create();
        String json = gson.toJson(tpm);

        return json;
    }

    public String serializeList(List<PackagingMaterialStockCard> tpmList) {
        Gson gson = new GsonBuilder().setDateFormat(pattern).create();
        String json = gson.toJson(tpmList);

        return json;
    }
}
