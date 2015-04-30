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
import server.rdr.domain.PackagingMaterialStockCard;
import server.rdr.entity.ReceivedPackagingMaterial;
import server.rdr.facade.ReceivedPackagingMaterialFacade;
import server.utils.DateFormatter;

/**
 * REST Web Service
 *
 * @author Maine
 */
@Path("receivedpm")
@RequestScoped
public class ReceivedPackagingMaterialREST {

    @Context
    private UriInfo context;

    @Inject
    private ReceivedPackagingMaterialFacade receivedPmFacade;

    public ReceivedPackagingMaterialREST() {
    }

    /**
     * Retrieves representation of an instance of
     * rdr.inventory.rest.ReceivedPmREST
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public List<ReceivedPackagingMaterial> getJson() {
        return receivedPmFacade.findAll();
    }

    /**
     * PUT method for updating or creating an instance of ReceivedPmREST
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
    public ReceivedPackagingMaterial createTrackInJSON(ReceivedPackagingMaterial receivedPm) {

        return receivedPmFacade.save(receivedPm);
        
    }
    
     @GET
    @Path("/pmstockcard")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PackagingMaterialStockCard> getPmStockCard(@QueryParam("startDate") String startDate,
            @QueryParam("endDate") String endDate) throws ParseException {
     
        
       return receivedPmFacade.getReceivedPmBetweenDate(DateFormatter.convertToDate(startDate),
                DateFormatter.convertToDate(endDate));
    }
    
    @GET
    @Path("/quarantine")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReceivedPackagingMaterial> getQuarantineReceivedRm() throws IOException, JAXBException {        
        return receivedPmFacade.findAllQuarantine();
    }
}
