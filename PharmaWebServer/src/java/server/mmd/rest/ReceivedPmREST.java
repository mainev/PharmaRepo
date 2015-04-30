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
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import server.mmd.facade.ReceivedPackagingMaterialFacade;
import server.mmd.entity.ReceivedPackagingMaterial;

/**
 * REST Web Service
 *
 * @author Maine
 */
@Path("mmdreceivedpm")
@RequestScoped
public class ReceivedPmREST {

    @Context
    private UriInfo context;

    @Inject
    private ReceivedPackagingMaterialFacade receivedPmFacade;

    /**
     * Creates a new instance of ReceivedPmREST
     */
    public ReceivedPmREST() {
    }

    /**
     * Retrieves representation of an instance of server.mmd.rest.ReceivedPmREST
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public List<ReceivedPackagingMaterial> getJson() {
        return receivedPmFacade.findAll();

    }

}
