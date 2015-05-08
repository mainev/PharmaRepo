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
@Stateless(name = "MMD_RECEIVED_PACKAGING_MATERIAL_FACADE")
public class ReceivedPackagingMaterialFacade {

    @PersistenceContext(unitName = "PharmaWebServerPU")
    private EntityManager em;

    public List<ReceivedPackagingMaterial> findAll() {
        List<ReceivedPackagingMaterial> list = em.createQuery("select r from MMD_RECEIVED_PACKAGING_MATERIAL r order by r.dateReceived desc").getResultList();
        return list;
    }

    public void create(ReceivedPackagingMaterial rpm) {
        em.persist(rpm);
    }

    public ReceivedPackagingMaterial findById(int id) {
        return em.find(ReceivedPackagingMaterial.class, id);
    }
}
