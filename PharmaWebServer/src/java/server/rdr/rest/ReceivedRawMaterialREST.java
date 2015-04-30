/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.rdr.rest;

import java.io.IOException;
import java.text.ParseException;
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
import javax.xml.bind.JAXBException;
import server.rdr.domain.RawMaterialStockCard;
import server.rdr.entity.ReceivedRawMaterial;
import server.rdr.facade.ReceivedRawMaterialFacade;
import server.utils.DateFormatter;

/**
 * REST Web Service
 *
 * @author Maine
 */
@Path("receivedrm")
@RequestScoped
public class ReceivedRawMaterialREST {

    @Context
    private UriInfo context;

    @Inject
    private ReceivedRawMaterialFacade receivedRmFacade;

    /**
     * Creates a new instance of ReceivedRmREST
     */
    public ReceivedRawMaterialREST() {
    }

    /**
     * Retrieves representation of an instance of
     * rdr.inventory.rest.ReceivedRmREST
     *
     * @return an instance of java.lang.String
     * @throws java.io.IOException
     */
    @GET
    @Produces({"application/json"})
    public List<ReceivedRawMaterial> getJson() throws IOException, JAXBException {
        return receivedRmFacade.findAll();
    }

    /**
     * PUT method for updating or creating an instance of ReceivedRmREST
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ReceivedRawMaterial createTrackInJSON(ReceivedRawMaterial receivedRm) {

        return receivedRmFacade.save(receivedRm);

    }

    @GET
    @Path("/rmstockcard")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RawMaterialStockCard> getRmStockCard(@QueryParam("startDate") String startDate,
            @QueryParam("endDate") String endDate) throws ParseException {
     
        
       return receivedRmFacade.getReceivedRmBetweenDate(DateFormatter.convertToDate(startDate),
                DateFormatter.convertToDate(endDate));
    }
    
     @GET
    @Path("/quarantine")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReceivedRawMaterial> getQuarantineReceivedRm() throws IOException, JAXBException {        
        return receivedRmFacade.findAllQuarantine();
    }
    
    @POST
    @Path("/approve")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ReceivedRawMaterial approveReceivedRm(ReceivedRawMaterial transferredRm) {
        return receivedRmFacade.approveReceivedRm(transferredRm);          
    }
    
}