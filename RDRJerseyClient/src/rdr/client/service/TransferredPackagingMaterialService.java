/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client.service;

import rdr.client.utils.TransferPackagingMaterialSerializer;
import rdr.client.entity.TransferredPackagingMaterial;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import rdr.client.entity.PackagingMaterial;

/**
 *
 * @author jemuel
 */
public class TransferredPackagingMaterialService {
    
   
    private DefaultClientConfig defaultClientConfig;
    private Client client;
    private WebResource webResource;
    private final String BASE_URI = "http://localhost:8080/PharmaWebServer/webresources/transferredpm";

    public TransferredPackagingMaterialService() {
        initClient();
    }

    private void initClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }
    
    public void addTransferredPm(TransferredPackagingMaterial tpm){
        try { 
            TransferPackagingMaterialSerializer tmps = new TransferPackagingMaterialSerializer();
		Client client = Client.create();
 
		WebResource webResource = client
		   .resource("http://localhost:8080/PharmaWebServer/webresources/transferredpm/createTransferredPm");
 
		String input = tmps.serializeTransferredPm(tpm);
 
		ClientResponse response = webResource.type("application/json")
		   .post(ClientResponse.class, input);
 
		//System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		//System.out.println(output);
 
	  } catch (Exception e) { 
		e.printStackTrace(); 
	  } 
	}    

    public ObservableList<TransferredPackagingMaterial> getAvailablePM(PackagingMaterial selectedPM) {
         ObservableList<TransferredPackagingMaterial> allTransferredPm = FXCollections.observableArrayList();

         webResource = client.resource(BASE_URI + "/availablerpm");
        
       ClientResponse response = webResource.queryParam("pmId", String.valueOf(selectedPM.getId()))
                                            .accept("application/json")
                                            .get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);

        TransferPackagingMaterialSerializer tpms = new TransferPackagingMaterialSerializer();
        allTransferredPm = tpms.deserializeList(jsonOutput);
        return allTransferredPm;
    }
    
    public ObservableList<TransferredPackagingMaterial> getQuarantinePm(PackagingMaterial selectedPm){
        ObservableList<TransferredPackagingMaterial> quarantineList = FXCollections.observableArrayList();
        
        webResource = client.resource(BASE_URI + "/quarantinepm");
        ClientResponse response = webResource.queryParam("pmId", String.valueOf(selectedPm.getId()))
                                             .accept("application/json")
                                            .get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);
        TransferPackagingMaterialSerializer tpms = new TransferPackagingMaterialSerializer();
        quarantineList = tpms.deserializeList(jsonOutput);
        return quarantineList;
    }
    
//    private void initClient() {
//        defaultClientConfig = new DefaultClientConfig();
//        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
//        client = Client.create(defaultClientConfig);
//    }

    public void transferPMToMMD(ObservableList<TransferredPackagingMaterial> outgoingPM) {
    //    ObservableList<TransferredPm> allTransferredPm = FXCollections.observableArrayList();

         
        TransferPackagingMaterialSerializer tpms = new TransferPackagingMaterialSerializer();
//        
//        DefaultClientConfig defaultClientConfig= new DefaultClientConfig();
//        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
//        Client client;
//        client = Client.create(defaultClientConfig);
//        WebResource webResource;
//        String BASE_URI = "http://localhost:8080/RDRWebServer/webresources/transferredpm/transfertommd";
        webResource = client.resource(BASE_URI + "/transfertommd");
        
        String input = tpms.serializeList(outgoingPM);
        
        ClientResponse response = webResource.type("application/json")
                                            .post(ClientResponse.class, input);
        
     //   String jsonOutput = response.getEntity(String.class);

        
    //    allTransferredPm = tpms.deserializeList(jsonOutput);
       
    
    }

    public void generateBundles(ObservableList<TransferredPackagingMaterial> transferredPmBundles) {
       

         
        TransferPackagingMaterialSerializer tpms = new TransferPackagingMaterialSerializer();
        
        DefaultClientConfig defaultClientConfig= new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        Client client;
        client = Client.create(defaultClientConfig);
        WebResource webResource;
        String BASE_URI = "http://localhost:8080/PharmaWebServer/webresources/transferredpm/generateBundles";
        webResource = client.resource(BASE_URI);
        
        String input = tpms.serializeList(transferredPmBundles);
        
        ClientResponse response = webResource.type("application/json")
                                            .post(ClientResponse.class, input);
        
        String jsonOutput = response.getEntity(String.class);
        
        ObservableList<TransferredPackagingMaterial> generatedBundlesFromDb = tpms.deserializeList(jsonOutput);
        
        
       
    }
        
}
