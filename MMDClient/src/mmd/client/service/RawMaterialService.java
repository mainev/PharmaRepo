/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmd.client.service;

import mmd.client.utils.PackagingMaterialSerializer;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import mmd.client.entity.PackagingMaterial;
import mmd.client.entity.RawMaterial;
import mmd.client.utils.RawMaterialSerializer;

/**
 *
 * @author jemuel
 */
public class RawMaterialService {

    private DefaultClientConfig defaultClientConfig;
    private Client client;
    private WebResource webResource;
    private final String BASE_URI = "http://localhost:8080/PharmaWebServer/webresources/rawmaterial";

    public RawMaterialService() {
        initClient();
    }

    private void initClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }

    public ObservableList<RawMaterial> getRawMaterialList() {
        ObservableList<RawMaterial> allRawMaterials = FXCollections.observableArrayList();

        webResource = client.resource(BASE_URI);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);

        RawMaterialSerializer rms = new RawMaterialSerializer();
        allRawMaterials = rms.deserializeList(jsonOutput);
        return allRawMaterials;
    }
}
