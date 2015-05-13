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
import server.mbr.entity.DosageInProcedure;

/**
 *
 * @author maine
 */
@Stateless
public class DosageInProcedureFacade {

   @PersistenceContext(unitName = "PharmaWebServerPU")
   private EntityManager em;
   
   public List<DosageInProcedure> findAll(){
       return em.createQuery("select d from DosageInProcedure d").getResultList();
   }
}
