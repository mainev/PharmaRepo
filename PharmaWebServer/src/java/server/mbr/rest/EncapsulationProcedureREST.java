/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.mbr.rest;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import server.mbr.entity.EncapsulationProcedure;
import server.mbr.facade.EncapsulationProcedureFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("mbr/encapsulationprocedure")
@RequestScoped
public class EncapsulationProcedureREST {

    @Context
    private UriInfo context;
    
    @Inject
    private EncapsulationProcedureFacade encapsulationProcedureFacade;

    /**
     * Creates a new instance of EncapsulationProcedureREST
     */
    public EncapsulationProcedureREST() {
    }

    /**
     * Retrieves representation of an instance of server.mbr.rest.EncapsulationProcedureREST
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public List<EncapsulationProcedure> getJson() {
        return encapsulationProcedureFacade.findAll();
    }

    /**
     * PUT method for updating or creating an instance of EncapsulationProcedureREST
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
