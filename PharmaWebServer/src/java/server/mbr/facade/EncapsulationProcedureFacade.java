/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.mbr.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server.mbr.entity.EncapsulationProcedure;

/**
 *
 * @author maine
 */
@Stateless
public class EncapsulationProcedureFacade {

    @PersistenceContext(unitName = "PharmaWebServerPU")
    private EntityManager em;

    public List<EncapsulationProcedure> findAll() {

        return em.createQuery("select e from EncapsulationProcedure e").getResultList();
    }
}
