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
import server._main.entity.RawMaterial;

/**
 *
 * @author Maine
 */
@Stateless
public class RawMaterialFacade {
       @PersistenceContext(unitName = "PharmaWebServerPU")
    private EntityManager em;
       

   public List<RawMaterial> findAll() {
        return em.createQuery("select r from RawMaterial r").getResultList();
    }

    public void createRawMaterial(RawMaterial rawMat){
        em.persist(rawMat);
    }
    
    public RawMaterial findById(int id){
        return em.find(RawMaterial.class, id);
    }
}
