/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mbr.client.entity.Product;

/**
 *
 * @author Admin
 */
public class ProductSerializer {

    public ObservableList<Product> deserializeList(String jsonOutput) {
        ObservableList<Product> observableProductList = FXCollections.observableArrayList();
        Gson gson = new Gson();
        Type datasetListType = new TypeToken<Collection<Product>>() {
        }.getType();
        List<Product> datasets = gson.fromJson(jsonOutput, datasetListType);

        datasets.forEach(rm -> observableProductList.add(rm));

        return observableProductList;
    }
}
