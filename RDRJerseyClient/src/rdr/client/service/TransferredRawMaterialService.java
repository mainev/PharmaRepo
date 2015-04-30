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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import rdr.client.entity.Product;
import rdr.client.entity.RawMaterial;
import rdr.client.entity.ReceivedRawMaterial;
import rdr.client.entity.TransferredRawMaterial;
import rdr.client.utils.RawMaterialSerializer;
import rdr.client.utils.TransferredRawMaterialSerializer;

/**
 *
 * @author Admin
 */
public class TransferredRawMaterialService {

    private DefaultClientConfig defaultClientConfig;
    private Client client;
    private WebResource webResource;
    private final String BASE_URI = "http://localhost:8080/PharmaWebServer/webresources/transferredrm";

    public TransferredRawMaterialService() {
        initClient();
    }

    private void initClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }

    public ObservableList<TransferredRawMaterial> getAllTransferredRms() {
        ObservableList<TransferredRawMaterial> allTransferredRms = FXCollections.observableArrayList();

        webResource = client.resource(BASE_URI);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);

        TransferredRawMaterialSerializer rms = new TransferredRawMaterialSerializer();
        allTransferredRms = rms.deserializeList(jsonOutput);

        return allTransferredRms;
    }

    public void transferRm(Product product, String tsNo, String soNo, List<TransferredRawMaterial> selectedRmToTransfer) {
        try {
           // System.out.println(selectedRmToTransfer);
            TransferredRawMaterialSerializer tms = new TransferredRawMaterialSerializer();
            RawMaterialSerializer rmSerializer = new RawMaterialSerializer();
            Client client = Client.create();

            webResource = client.resource("http://localhost:8080/PharmaWebServer/webresources/transferredrm/transfer");

            String jsonInput = tms.serializeList(selectedRmToTransfer);

            ClientResponse response = webResource.type("application/json")
                    .post(ClientResponse.class, jsonInput);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateContainer(ReceivedRawMaterial rrm) throws UniformInterfaceException{
        
            int totalContainer = rrm.getTotalNoOfContainer();
            double amountPerContainer = rrm.getAmountPerContainer();
            LinkedList<TransferredRawMaterial> generatedTrmList = new LinkedList();
            for (short i = 1; i <= totalContainer; i++) {
                TransferredRawMaterial trm = new TransferredRawMaterial();
                trm.setContainerNo(i);
                trm.setQuantity(amountPerContainer);
                trm.setProductId(null);
                trm.setDateTransferred(null);
                trm.setSoNo(null);
                trm.setTsNo(null);
                trm.setUnit(rrm.getUnit());
                trm.setStatus("QUARANTINE");
                trm.setIsTransferred(false);

                generatedTrmList.add(trm);
            }
            
            if(rrm.getRemainder()!=0){
                generatedTrmList.getLast().setQuantity(rrm.getRemainder());
            }
            TransferredRawMaterialSerializer trmSerializer = new TransferredRawMaterialSerializer();
           
            webResource = client.resource(BASE_URI + "/generatecontainer");

            String input = trmSerializer.serializeList(generatedTrmList);

            ClientResponse response = webResource
                    .queryParam("receivedRmId", String.valueOf(rrm.getId()))
                    .type("application/json")
                    .post(ClientResponse.class, input);

    }

    public ObservableList<TransferredRawMaterial> getTransferableRmList(RawMaterial rawmat) {
        ObservableList<TransferredRawMaterial> transferrableRmList = FXCollections.observableArrayList();

        webResource = client.resource(BASE_URI + "/gettransferablerm");
        ClientResponse response = webResource.queryParam("rmId", String.valueOf(rawmat.getId()))
                .accept("application/json")
                .get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);
        TransferredRawMaterialSerializer tms = new TransferredRawMaterialSerializer();
        transferrableRmList = tms.deserializeList(jsonOutput);
        return transferrableRmList;
    }

    public ObservableList<TransferredRawMaterial> searchTransferredRm(String rmCode, String rrNo) {
   
        webResource = client.resource(BASE_URI + "/findbyrmcodeandrrno");
        ClientResponse response = webResource
                .queryParam("rmCode", rmCode)
                .queryParam("rrNo", rrNo)
                .accept("application/json")
                .get(ClientResponse.class);

        String jsonOutput = response.getEntity(String.class);
        TransferredRawMaterialSerializer tms = new TransferredRawMaterialSerializer();
      //  System.out.println(tms.deserializeList(jsonOutput));
        return tms.deserializeList(jsonOutput);

    }
    
     public ObservableList<TransferredRawMaterial> getQuarantinePm(RawMaterial selectedRawMaterial){
        ObservableList<TransferredRawMaterial> quarantineList = FXCollections.observableArrayList();
        
        webResource = client.resource(BASE_URI + "/quarantinerm");
        ClientResponse response = webResource.queryParam("rmId", String.valueOf(selectedRawMaterial.getId()))
                                             .accept("application/json")
                                            .get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);
        TransferredRawMaterialSerializer trms = new TransferredRawMaterialSerializer();
        quarantineList = trms.deserializeList(jsonOutput);
        return quarantineList;
    }
}
