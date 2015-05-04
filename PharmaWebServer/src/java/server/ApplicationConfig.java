/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Maine
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(server._main.rest.ClientREST.class);
        resources.add(server._main.rest.PackagingMaterialREST.class);
        resources.add(server._main.rest.ProductREST.class);
        resources.add(server._main.rest.RawMaterialREST.class);
        resources.add(server.mmd.rest.IssuedPackagingMaterialREST.class);
        resources.add(server.mmd.rest.ReceivedPmREST.class);
        resources.add(server.mmd.rest.ReceivedRawMaterialREST.class);
        resources.add(server.rdr.rest.ReceivedPackagingMaterialREST.class);
        resources.add(server.rdr.rest.ReceivedRawMaterialREST.class);
        resources.add(server.rdr.rest.TransferredPackagingMaterialREST.class);
        resources.add(server.rdr.rest.TransferredRawMaterialREST.class);
    }
    
}
