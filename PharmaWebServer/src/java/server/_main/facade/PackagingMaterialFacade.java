/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server._main.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server._main.entity.PackagingMaterial;

/**
 *
 * @author Maine
 */
@Stateless
public class PackagingMaterialFacade {

 @PersistenceContext(unitName = "PharmaWebServerPU")
    private EntityManager em;

    public List<PackagingMaterial> findAll() {
        return em.createQuery("select p from PackagingMaterial p").getResultList();
    }
    
    public PackagingMaterial findById(int id){
        return em.find(PackagingMaterial.class, id);
    }
}
