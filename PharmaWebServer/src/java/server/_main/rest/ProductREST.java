/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server._main.rest;

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
import server._main.entity.Product;
import server._main.facade.ProductFacade;

/**
 * REST Web Service
 *
 * @author Maine
 */
@Path("product")
@RequestScoped
public class ProductREST {

    @Context
    private UriInfo context;

      @Inject
    private ProductFacade productFacade;
    /**
     * Creates a new instance of PackagingmaterialREST
    public ProductREST() {
    }

    /**
     * Retrieves representation of an instance of rdr.inventory.rest.ProductREST
     * @return an instance of java.lang.String
     */
     @GET
    @Produces({"application/json"})
    public List<Product> getJson() {
        return productFacade.findAll();
    }

    /**
     * PUT method for updating or creating an instance of ProductREST
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
