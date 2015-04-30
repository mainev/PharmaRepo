/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import rdr.client.domain.RawMaterialStockCard;
import rdr.client.utils.ReceivedRawMaterialSerializer;
import rdr.client.entity.ReceivedRawMaterial;
import rdr.client.utils.DateFormatter;
import rdr.client.utils.RawMaterialStockCardSerializer;

/**
 *
 * @author Admin
 */
public class ReceivedRawMaterialService {

    private DefaultClientConfig defaultClientConfig;
    private Client client;
    private WebResource webResource;
    private final String BASE_URI = "http://localhost:8080/PharmaWebServer/webresources/receivedrm";

    public ReceivedRawMaterialService() {
        initClient();
    }

    private void initClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }

    public ObservableList<ReceivedRawMaterial> getAllReceivedRms() {
        ObservableList<ReceivedRawMaterial> allReceivedRms = FXCollections.observableArrayList();

        webResource = client.resource(BASE_URI);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);

        ReceivedRawMaterialSerializer rms = new ReceivedRawMaterialSerializer();
        allReceivedRms = rms.deserializeList(jsonOutput);

        return allReceivedRms;
    }

    public ReceivedRawMaterial addReceivedRm(ReceivedRawMaterial rrm) throws UniformInterfaceException {
        ReceivedRawMaterialSerializer rms = new ReceivedRawMaterialSerializer();
        String output = "";

            WebResource webResource = client
                    .resource(BASE_URI + "/add");

            String input = rms.serializeReceivedRm(rrm);
            ClientResponse response = webResource.type("application/json")
                    .post(ClientResponse.class, input);
            output = response.getEntity(String.class);


        return rms.deserialize(output);
    }

    /**
     * Returns a list of raw materials received between two dates.
     * @param startDate
     * @param endDate
     * @return 
     */
    public List<RawMaterialStockCard> getReceivedRmBetweenDates(Date startDate, Date endDate) {
      //  List<RMStockCard> rmStockCard = new ArrayList();
        webResource = client.resource(BASE_URI + "/rmstockcard");
        ClientResponse response = webResource
                .queryParam("startDate", DateFormatter.convertToString(startDate))
                .queryParam("endDate", DateFormatter.convertToString(endDate))
                .accept("application/json")
                .get(ClientResponse.class);

        String jsonOutput = response.getEntity(String.class);

        RawMaterialStockCardSerializer rms = new RawMaterialStockCardSerializer();
        return rms.deserializeList(jsonOutput);

    }

}
