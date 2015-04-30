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
import rdr.client.domain.PackagingMaterialStockCard;
import rdr.client.entity.ReceivedPackagingMaterial;
import rdr.client.utils.DateFormatter;
import rdr.client.utils.PackagingMaterialStockCardSerializer;
import rdr.client.utils.ReceivedPackagingMaterialSerializer;

/**
 *
 * @author jemuel
 */
public class ReceivedPackagingMaterialService {
    
      private DefaultClientConfig defaultClientConfig;
    private Client client;
    private WebResource webResource;
    private final String BASE_URI = "http://localhost:8080/PharmaWebServer/webresources/receivedpm";

    public ReceivedPackagingMaterialService() {
        initClient();
    }

    private void initClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }

    public ObservableList<ReceivedPackagingMaterial> getAllReceivedPms() {
        ObservableList<ReceivedPackagingMaterial> allReceivedPms = FXCollections.observableArrayList();

        webResource = client.resource(BASE_URI);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);

        ReceivedPackagingMaterialSerializer rps = new ReceivedPackagingMaterialSerializer();
        allReceivedPms = rps.deserializeList(jsonOutput);

        return allReceivedPms;
    }

    public ReceivedPackagingMaterial addReceivedPm(ReceivedPackagingMaterial rpm) throws UniformInterfaceException{
        ReceivedPackagingMaterialSerializer rps = new ReceivedPackagingMaterialSerializer();
        String output = "";
            
//        try {
//
//            Client client = Client.create();

            webResource = client.resource(BASE_URI + "/add");

            String input = rps.serializeReceivedPm(rpm);

            ClientResponse response = webResource.type("application/json")
                    .post(ClientResponse.class, input);

//		if (response.getStatus() != 201) {
//			throw new RuntimeException("Failed : HTTP error code : "
//			     + response.getStatus());
//		}
         //   System.out.println("Output from Server .... \n");
            output = response.getEntity(String.class);
         //   System.out.println(output);
            
           
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
         return rps.deserialize(output);
    }


//    public  ObservableList<ReceivedRm> getTransferableRmList(RawMaterial rawmat) {
//        ObservableList<ReceivedRm> transferrableRmList = FXCollections.observableArrayList();
//
//        webResource = client.resource(BASE_URI + "/gettransferablerm");
//        ClientResponse response = webResource.queryParam("rmCode", rawmat.getRawMatCode())
//                                            .accept("application/json")
//                                            .get(ClientResponse.class);
//        String jsonOutput = response.getEntity(String.class);
//      //  System.out.println("output from server to client: "+jsonOutput);
//        ReceivedRmSerializer rms = new ReceivedRmSerializer();
//        transferrableRmList = rms.deserializeList(jsonOutput);
//
//      //  System.out.println(transferrableRmList);
//       return transferrableRmList;
//    }

     /**
     * Returns a list of packaging materials received between two dates.
     * @param startDate
     * @param endDate
     * @return 
     */
    public List<PackagingMaterialStockCard> getReceivedPmBetweenDates(Date startDate, Date endDate) {
      //  List<RMStockCard> rmStockCard = new ArrayList();
        webResource = client.resource(BASE_URI + "/pmstockcard");
        ClientResponse response = webResource
                .queryParam("startDate", DateFormatter.convertToString(startDate))
                .queryParam("endDate", DateFormatter.convertToString(endDate))
                .accept("application/json")
                .get(ClientResponse.class);

        String jsonOutput = response.getEntity(String.class);

        PackagingMaterialStockCardSerializer rms = new PackagingMaterialStockCardSerializer();
        return rms.deserializeList(jsonOutput);

    }
   
}
