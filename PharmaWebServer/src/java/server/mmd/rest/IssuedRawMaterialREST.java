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
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import server.mmd.entity.IssuedRawMaterial;
import server.mmd.facade.IssuedRawMaterialFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("mmdissuedrawmaterial")
@RequestScoped
public class IssuedRawMaterialREST {

    @Context
    private UriInfo context;

    @Inject
    private IssuedRawMaterialFacade issuedRawMaterialFacade;

    /**
     * Creates a new instance of IssuedRawMaterialREST
     */
    public IssuedRawMaterialREST() {
    }

    /**
     * Retrieves representation of an instance of
     * server.mmd.rest.IssuedRawMaterialREST
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public List<IssuedRawMaterial> getJson() {
        return issuedRawMaterialFacade.findAll();
    }

    /**
     * PUT method for updating or creating an instance of IssuedRawMaterialREST
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
