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
import server._main.entity.Equipment;

/**
 *
 * @author maine
 */
@Stateless
public class EquipmentFacade {

    @PersistenceContext(unitName = "PharmaWebServerPU")
    private EntityManager em;

    public List<Equipment> findAll() {
        return em.createQuery("select e from Equipment e").getResultList();
    }
}
