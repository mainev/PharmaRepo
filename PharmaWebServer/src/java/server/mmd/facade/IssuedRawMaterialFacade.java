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
import server.mmd.entity.IssuedRawMaterial;

/**
 *
 * @author maine
 */
@Stateless
public class IssuedRawMaterialFacade {

   @PersistenceContext(unitName = "PharmaWebServerPU")
    private EntityManager em;

    public List<IssuedRawMaterial> findAll() {
        List<IssuedRawMaterial> list =  em.createQuery("select i from server.mmd.entity.IssuedRawMaterial i").getResultList();
        return list;
    }
    
    public IssuedRawMaterial create(IssuedRawMaterial irm) {
        em.persist(irm);
        em.flush();
        return em.find(IssuedRawMaterial.class, irm.getId());
    }
    
    public IssuedRawMaterial findById(int id) {
        return em.find(IssuedRawMaterial.class, id);
    }
    
}
