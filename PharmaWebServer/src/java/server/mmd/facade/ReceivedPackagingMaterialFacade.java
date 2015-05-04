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
import server.mmd.entity.ReceivedPackagingMaterial;

/**
 *
 * @author Maine
 */
@Stateless
public class ReceivedPackagingMaterialFacade {

    @PersistenceContext(unitName = "PharmaWebServerPU")
    private EntityManager em;

    public List<server.mmd.entity.ReceivedPackagingMaterial> findAll() {
        List<server.mmd.entity.ReceivedPackagingMaterial> list = em.createQuery("select r from server.mmd.entity.ReceivedPackagingMaterial r").getResultList();
        return list;
    }
    
    public ReceivedPackagingMaterial create(ReceivedPackagingMaterial rpm){
        em.persist(rpm);
        em.flush();
        return em.find(ReceivedPackagingMaterial.class, rpm.getId());
    }
    
    public ReceivedPackagingMaterial findById(int id){
        return em.find(ReceivedPackagingMaterial.class, id);
    }
}
