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
import server.mmd_tolling.entity.ReceivedRawMaterial;

/**
 *
 * @author maine
 */
@Stateless(name="MMD_TOLLING_RECEIVED_RAW_MATERIAL_FACADE")
public class ReceivedRawMaterialFacade {

    @PersistenceContext(unitName = "PharmaWebServerPU")
    private EntityManager em;

    public List<ReceivedRawMaterial> findAll() {
        List<ReceivedRawMaterial> list = em.createQuery("select r from MMD_TOLLING_RECEIVED_RAW_MATERIAL r order by r.dateReceived desc").getResultList();
        return list;
    }

    public void create(ReceivedRawMaterial rrm) {
        em.persist(rrm);
    }

    public ReceivedRawMaterial findById(int id) {
        return em.find(ReceivedRawMaterial.class, id);
    }

}
