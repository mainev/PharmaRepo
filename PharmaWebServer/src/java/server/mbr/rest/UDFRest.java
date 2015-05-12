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
import server.mbr.entity.UDF;
import server.mbr.facade.UDFFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("udf")
@RequestScoped
public class UDFRest {

    @Context
    private UriInfo context;
    
    @Inject
    private UDFFacade udfFacade;

    /**
     * Creates a new instance of UDFRest
     */
    public UDFRest() {
    }

    /**
     * Retrieves representation of an instance of server.mbr.rest.UDFRest
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public List<UDF> getJson() {
        return udfFacade.findAll();
    }

    /**
     * PUT method for updating or creating an instance of UDFRest
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
