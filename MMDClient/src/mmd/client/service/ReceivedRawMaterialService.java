/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmd.client.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.Date;
import javafx.collections.ObservableList;
import mmd.client.entity.RawMaterial;
import mmd.client.entity.ReceivedRawMaterial;
import mmd.client.utils.ReceivedRawMaterialSerializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class ReceivedRawMaterialService {

    private DefaultClientConfig defaultClientConfig;
    private Client client;
    private WebResource webResource;
    private final String BASE_URI = "http://localhost:8080/PharmaWebServer/webresources/mmd/tolling/received/rawmaterial";
    private final ReceivedRawMaterialSerializer receivedRawMaterialSerializer = new ReceivedRawMaterialSerializer();

    public ReceivedRawMaterialService() {
        initClient();
    }

    private void initClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }

    public ObservableList<ReceivedRawMaterial> getReceivedRawMaterialList() {

        webResource = client.resource(BASE_URI);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);

        return receivedRawMaterialSerializer.deserializeList(jsonOutput);
    }

    public void createReceivedRawMaterial(RawMaterial rawMaterial, Date dateReceived, double quantity, String unit,
            String batchNo, String qcControlNo, Date mfgDate, Date expDate, String rrNo, String receivedBy) {

        ReceivedRawMaterial receivedRawMaterial = new ReceivedRawMaterial(rawMaterial, dateReceived, quantity, unit,
                batchNo, qcControlNo, mfgDate, expDate, rrNo, receivedBy);

        String input = receivedRawMaterialSerializer.serializeReceivedRawMaterial(receivedRawMaterial);

        webResource = client.resource(BASE_URI + "/create");
        webResource.type("application/json")
                .post(ClientResponse.class, input);
    }
}
