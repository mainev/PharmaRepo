/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.rdr.rest;

import java.io.IOException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import server.rdr.entity.TransferredPackagingMaterial;
import server.rdr.facade.TransferredPackagingMaterialFacade;

/**
 * REST Web Service
 *
 * @author Maine
 */
@Path("transferredpm")
@RequestScoped
public class TransferredPackagingMaterialREST {

    @Context
    private UriInfo context;

    @Inject
    private TransferredPackagingMaterialFacade transferredPmFacade;
    /**
     * Creates a new instance of TransferredPmREST
     */
    public TransferredPackagingMaterialREST() {
    }

    /**
     * Retrieves representation of an instance of rdr.inventory.rest.TransferredPmREST
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public List<TransferredPackagingMaterial> getJson() {
        //TODO return proper representation object
        return transferredPmFacade.findAll();
    }

    /**
     * PUT method for updating or creating an instance of TransferredPmREST
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
    
    @POST
    @Path("/createTransferredPm")
    @Consumes(MediaType.APPLICATION_JSON)
    public String createTransferredPm(TransferredPackagingMaterial tpm) {       
       
   // System.out.println("ang tpm kai : " + tpm.getTsNo());

        transferredPmFacade.save(tpm);
            return "";
    }
    
    @GET
    @Path("/availablerpm")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TransferredPackagingMaterial> getTransferredPm(@QueryParam("pmId") String pmId) throws IOException{       
       
      //  System.out.println("ang pmCode na dumating d2 sa TransferredPmREST ay : " + pmCode);
        
        return transferredPmFacade.getAvailablePm(Integer.parseInt(pmId));
    }
    
    @GET
    @Path("/quarantinepm")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransferredPackagingMaterial> getQuarantinePm(@QueryParam("pmId") String pmId) throws IOException{
        return transferredPmFacade.getQuarantinePm(Integer.parseInt(pmId));
    }
    
    
    @POST
    @Path("/transfertommd")
    @Consumes(MediaType.APPLICATION_JSON)
   // @Produces(MediaType.APPLICATION_JSON)
    public void transferToMMD( List<TransferredPackagingMaterial> tpmList) {
   
        transferredPmFacade.transferToMMD(tpmList);
      //  return "yow";
    }
    
    @POST
    @Path("/generateBundles")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransferredPackagingMaterial> generateBundles( List<TransferredPackagingMaterial> tpmList) {
 
        return transferredPmFacade.generateBundles(tpmList);
    }
    
    @GET
    @Produces({"application/json"})
    @Path("/quarantinebundles")
    public List<TransferredPackagingMaterial> getQuarantineTransferredRm(@QueryParam("receivedPmID") String receivedPmID) throws IOException {
     //   System.out.println("ang receivedPmID kai : aw " + receivedPmID);
        return transferredPmFacade.getQuarantineTransferredPm(Integer.parseInt(receivedPmID));
    }
    
    @POST
    @Path("/approve")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TransferredPackagingMaterial approveTransferredPm(TransferredPackagingMaterial transferredPm) {
        return transferredPmFacade.approveReceivedRm(transferredPm);          
    }
    
    
}
