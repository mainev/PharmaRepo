/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.mmd.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server.mmd.entity.IssuedPackagingMaterial;

/**
 *
 * @author Maine
 */
@Stateless
public class IssuedPackagingMaterialFacade {

    @PersistenceContext(unitName = "PharmaWebServerPU")
    private EntityManager em;

    public List<IssuedPackagingMaterial> findAll() {
        List<IssuedPackagingMaterial> list =  em.createQuery("select i from server.mmd.entity.IssuedPackagingMaterial i").getResultList();
        return list;
    }
    
     public IssuedPackagingMaterial create(IssuedPackagingMaterial ipm) {
        em.persist(ipm);
        em.flush();
        return em.find(IssuedPackagingMaterial.class, ipm.getId());
    }
    
    public IssuedPackagingMaterial findById(int id) {
        return em.find(IssuedPackagingMaterial.class, id);
    }
}
