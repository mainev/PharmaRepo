/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mbr.client.entity.MBR;

/**
 *
 * @author maine
 */
public class MBRSerializer {

    private static final String pattern = "yyyy-MM-dd'T'HH:mm:ss";

    public static String serializeMBR(MBR mbr) {

        Gson gson = new GsonBuilder().setDateFormat(pattern).create();
        String json = gson.toJson(mbr);

        return json;
    }
    
    public static ObservableList<MBR> deserializeList(String jsonOutput) {
        ObservableList<MBR> observableReceivedPMList = FXCollections.observableArrayList();

        Gson gson = new GsonBuilder().setDateFormat(pattern).create();

        Type datasetListType = new TypeToken<Collection<MBR>>() {
        }.getType();
        List<MBR> datasets = gson.fromJson(jsonOutput, datasetListType);

        datasets.forEach(pm -> observableReceivedPMList.add(pm));

        return observableReceivedPMList;
    }

}
