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
import server.mbr.entity.UDF;

/**
 *
 * @author maine
 */
@Stateless
public class UDFFacade {

   @PersistenceContext(unitName = "PharmaWebServerPU")
    private EntityManager em;

    public List<UDF> findAll() {
        return em.createQuery("select u from UDF u").getResultList();
    }
}
