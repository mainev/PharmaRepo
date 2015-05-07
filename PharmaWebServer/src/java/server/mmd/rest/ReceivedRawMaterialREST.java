/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.mmd.rest;

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
import javax.ws.rs.core.MediaType;
import server._main.facade.RawMaterialFacade;
import server.mmd.entity.ReceivedRawMaterial;
import server.mmd.facade.ReceivedRawMaterialFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("mmd/received/rawmaterial")
@RequestScoped
public class ReceivedRawMaterialREST {

    @Context
    private UriInfo context;

    @Inject
    private ReceivedRawMaterialFacade receivedRmFacade;

    /**
     * Creates a new instance of ReceivedRawMaterialREST
     */
    public ReceivedRawMaterialREST() {
    }

    /**
     * Retrieves representation of an instance of
     * server.mmd.rest.ReceivedRawMaterialREST
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public List<ReceivedRawMaterial> getJson() {
        return receivedRmFacade.findAll();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON)
    public void create(ReceivedRawMaterial receivedRm) {
        System.out.println(receivedRm.getDateReceived());
        receivedRmFacade.create(receivedRm);
    }

    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
