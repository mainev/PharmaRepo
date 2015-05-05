/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmd.client.service;

import mmd.client.utils.ReceivedPackagingMaterialSerializer;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mmd.client.entity.ReceivedPackagingMaterial;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class ReceivedPackagingMaterialService {

    private DefaultClientConfig defaultClientConfig;
    private Client client;
    private WebResource webResource;
    private final String BASE_URI = "http://localhost:8080/PharmaWebServer/webresources/mmd/received/packagingmaterial";

    public ReceivedPackagingMaterialService() {
        configClient();
    }

    private void configClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }
    
     public ObservableList<ReceivedPackagingMaterial> getReceivedPackagingMaterialList() {
        ObservableList<ReceivedPackagingMaterial> allReceivedPms = FXCollections.observableArrayList();

        webResource = client.resource(BASE_URI);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);

        ReceivedPackagingMaterialSerializer rps = new ReceivedPackagingMaterialSerializer();
        allReceivedPms = rps.deserializeList(jsonOutput);

        return allReceivedPms;
    }
}
