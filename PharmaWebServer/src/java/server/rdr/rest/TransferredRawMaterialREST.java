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
import server.rdr.entity.TransferredRawMaterial;
import server.rdr.facade.TransferredRawMaterialFacade;

/**
 * REST Web Service
 *
 * @author Maine
 */
@Path("transferredrm")
@RequestScoped
public class TransferredRawMaterialREST {

    @Context
    private UriInfo context;

    @Inject
    private TransferredRawMaterialFacade transferredRmFacade;

    public TransferredRawMaterialREST() {
    }

 
    @GET
    @Produces({"application/json"})
    public List<TransferredRawMaterial> getJson() {
        return transferredRmFacade.findAll();
    }

    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

  
    @POST
    @Path("/transfer")
    @Consumes(MediaType.APPLICATION_JSON)
   // @Produces(MediaType.APPLICATION_JSON)
    public void transferRawMaterial(List<TransferredRawMaterial> selectedRmToTransfer) {
       transferredRmFacade.updateTransferredRm(selectedRmToTransfer);
    }

    @POST
    @Path("/generatecontainer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransferredRawMaterial> generateContainer(@QueryParam("receivedRmId") String receivedRmId, List<TransferredRawMaterial> trmList) {
        int rrmId = Integer.parseInt(receivedRmId);
        transferredRmFacade.insertGeneratedContainer(trmList, rrmId);
        return trmList;
    }
    
    
    @GET
    @Produces({"application/json"})
    @Path("/gettransferablerm")
    public List<TransferredRawMaterial> getTransferableRm(@QueryParam("rmId") String rmId) throws IOException {
      //  System.out.println("TransferredRm Service rest");
        return transferredRmFacade.getTransferableRm(Integer.parseInt(rmId));
    }
    
    @GET
    @Produces({"application/json"})
    @Path("/findbyrmcodeandrrno")
    public List<TransferredRawMaterial> findByRmCodeAndRrNo(
            @QueryParam("rmCode") String rmCode,
            @QueryParam("rrNo") String rrNo) throws IOException{
        
        return transferredRmFacade.findByRmCodeAndRrNo(rmCode, rrNo);
    }
    
    @GET
    @Produces({"application/json"})
    @Path("/quarantinerm")
    public List<TransferredRawMaterial> getQuarantinePm(@QueryParam("rmId") String rmId) throws IOException{
        return transferredRmFacade.getQuarantineRm(Integer.parseInt(rmId));
    }
    
    @POST
    @Path("/approve")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TransferredRawMaterial approveTransferredRm(TransferredRawMaterial transferredRm) {
        return transferredRmFacade.approveTransferredRm(transferredRm);          
    }
}
