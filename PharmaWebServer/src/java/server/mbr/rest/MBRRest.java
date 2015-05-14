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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import server.mbr.entity.MBR;
import server.mbr.facade.MBRFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("mbr")
@RequestScoped
public class MBRRest {

    @Context
    private UriInfo context;

    @Inject
    private MBRFacade mbrFacade;

    /**
     * Creates a new instance of MBRRest
     */
    public MBRRest() {
    }

    /**
     * Retrieves representation of an instance of server.mbr.rest.MBRRest
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public List<MBR> getJson() {
        return mbrFacade.findAll();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@QueryParam("mbr_product_id") String mbrProductId, 
            @QueryParam("mbr_packaging_size_id") String mbrPackagingSizeId, MBR mbr) {
        int productId = Integer.parseInt(mbrProductId);
        int packSizeId = Integer.parseInt(mbrPackagingSizeId);
        mbrFacade.create(productId, packSizeId, mbr);
    }
}
