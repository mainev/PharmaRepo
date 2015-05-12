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
import server.mmd.entity.IssuedPackagingMaterial;
import server.mmd.facade.IssuedPackagingMaterialFacade;

/**
 * REST Web Service
 *
 * @author Maine
 */
@Path("mmd/tolling/issued/packagingmaterial")
@RequestScoped
public class IssuedPackagingMaterialREST {

    @Context
    private UriInfo context;

    @Inject
    private IssuedPackagingMaterialFacade issuedPackagingMaterialFacade;

    public IssuedPackagingMaterialREST() {
    }

    @GET
    @Produces("application/json")
    public List<IssuedPackagingMaterial> getJson() {
        return issuedPackagingMaterialFacade.findAll();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public IssuedPackagingMaterial create(IssuedPackagingMaterial issuedPm) {
        return issuedPackagingMaterialFacade.create(issuedPm);
    }

    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
