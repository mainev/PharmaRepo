/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.mmd_tolling.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server.mmd_tolling.entity.IssuedPackagingMaterial;

/**
 *
 * @author Maine
 */
@Stateless(name = "MMD_TOLLING_ISSUED_PACKAGING_MATERIAL_FACADE")
public class IssuedPackagingMaterialFacade {

    @PersistenceContext(unitName = "PharmaWebServerPU")
    private EntityManager em;

    public List<IssuedPackagingMaterial> findAll() {
        List<IssuedPackagingMaterial> list =  em.createQuery("select i from MMD_TOLLING_ISSUED_PACKAGING_MATERIAL i").getResultList();
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
