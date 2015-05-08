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
import java.util.Date;
import javafx.collections.ObservableList;
import mmd.client.entity.PackagingMaterial;
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
    private final ReceivedPackagingMaterialSerializer receivedPackagingMaterialSerializer = new ReceivedPackagingMaterialSerializer();

    public ReceivedPackagingMaterialService() {
        configClient();
    }

    private void configClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }

    public ObservableList<ReceivedPackagingMaterial> getReceivedPackagingMaterialList() {

        webResource = client.resource(BASE_URI);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);

        return receivedPackagingMaterialSerializer.deserializeList(jsonOutput);
    }

    public void createReceivedPackagingMaterial(PackagingMaterial packagingMaterial, Date dateReceived, String rrNo,
            String qcControlNo, Double quantity, String unit, String receivedBy) {

        ReceivedPackagingMaterial receivedPackagingMaterial = new ReceivedPackagingMaterial(packagingMaterial, dateReceived,
                rrNo, qcControlNo, quantity, unit, receivedBy);
        String input = receivedPackagingMaterialSerializer.serializeReceivedPackagingMaterial(receivedPackagingMaterial);
       // System.out.println("json input " + input);
        webResource = client.resource(BASE_URI + "/create");
        webResource.type("application/json")
                .post(ClientResponse.class, input);

    }
}
