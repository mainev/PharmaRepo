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
import server.mbr.entity.PackagingMaterialRequirements;
import server.mbr.facade.PackagingMaterialRequirementsFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("mbr/packagingmaterialrequirements")
@RequestScoped
public class PackagingMaterialREST {

    @Context
    private UriInfo context;
    
    @Inject
    private PackagingMaterialRequirementsFacade packagingMaterialRequirementsFacade;

    /**
     * Creates a new instance of MBRPackagingMaterialREST
     */
    public PackagingMaterialREST() {
    }

    /**
     * Retrieves representation of an instance of server.mbr.rest.MBRPackagingMaterialREST
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public List<PackagingMaterialRequirements> getJson() {
        return packagingMaterialRequirementsFacade.findAll();
    }

    /**
     * PUT method for updating or creating an instance of MBRPackagingMaterialREST
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
