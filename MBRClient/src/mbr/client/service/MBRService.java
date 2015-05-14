/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.Date;
import javafx.collections.ObservableList;
import mbr.client.entity.MBR;
import mbr.client.entity.PackagingSize;
import mbr.client.entity.Product;
import mbr.client.utils.MBRSerializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class MBRService {

    public DefaultClientConfig defaultClientConfig;
    public Client client;
    public WebResource webResource;
    public String BASE_URI = "http://localhost:8080/PharmaWebServer/webresources/mbr";

    public MBRService() {
        initClient();
    }

    private void initClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }

    public ObservableList<MBR> getMBRList() {

        webResource = client.resource(BASE_URI);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);

        return MBRSerializer.deserializeList(jsonOutput);
    }

    public void createMBR(Product productId, PackagingSize packagingSizeId, Double batchSize, String batchNo, String unit, Date mfgDate, Date expDate,
            String poNo) {

        MBR mbr = new MBR(productId, packagingSizeId, batchSize, batchNo, unit, mfgDate, expDate, poNo);
        String input = MBRSerializer.serializeMBR(mbr);

        webResource = client.resource(BASE_URI + "/create");
        webResource.queryParam("mbr_product_id", String.valueOf(productId.getId()))
                .queryParam("mbr_packaging_size_id", String.valueOf(packagingSizeId.getId()))
                .type("application/json")
                .post(ClientResponse.class, input);

    }
}
