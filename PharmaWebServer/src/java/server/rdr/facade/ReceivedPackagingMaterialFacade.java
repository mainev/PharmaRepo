/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.rdr.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import server.rdr.domain.PackagingMaterialStockCard;
import server.rdr.entity.ReceivedPackagingMaterial;

/**
 *
 * @author Maine
 */
@Stateless
public class ReceivedPackagingMaterialFacade {

    @PersistenceContext(unitName = "PharmaWebServerPU")
    private EntityManager em;

    public List<ReceivedPackagingMaterial> findAll() {
        return em.createQuery("select r from RDR_RECEIVED_PACKAGING_MATERIAL r").getResultList();
    }

    public ReceivedPackagingMaterial findById(int id) {
        return em.find(ReceivedPackagingMaterial.class, id);
    }

    public ReceivedPackagingMaterial save(ReceivedPackagingMaterial rpm) {

        em.persist(rpm);
        em.flush();

        return em.find(ReceivedPackagingMaterial.class, rpm.getId());
    }

    /**
     * Returns a list of received packaging materials between two dates.
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<PackagingMaterialStockCard> getReceivedPmBetweenDate(Date startDate, Date endDate) {
        List<PackagingMaterialStockCard> receivedPmListResult = new ArrayList();

        List<ReceivedPackagingMaterial> rpmList = em.createQuery("SELECT r from RDR_RECEIVED_PACKAGING_MATERIAL r WHERE r.dateReceived BETWEEN :startDate AND :endDate")
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();

        rpmList.forEach(rpm -> {
            PackagingMaterialStockCard pms = new PackagingMaterialStockCard();
            pms.setItemCode(rpm.getPackagingMaterialId().getCode());
            pms.setRrNo(rpm.getRrNo());
            pms.setItemName(rpm.getPackagingMaterialId().getDescription());
            pms.setInventory2(rpm.getInventory2Quantity());
            pms.setIssuance(rpm.getIssuance());
            pms.setInventory1(rpm.getInventory1Quantity());
            pms.setUnitCost(rpm.getUnitCost());
            pms.setTotalCost(rpm.getTotalCost());

            receivedPmListResult.add(pms);
        });

        return receivedPmListResult;
    }

    public List<ReceivedPackagingMaterial> findAllQuarantine() {
        Query query = em.createNativeQuery("SELECT distinct(received_pm_id) FROM rdr.transferred_packaging_material\n"
                + "where rdr.transferred_packaging_material.status = 'QUARANTINE';");
        List<Integer> ids = (List<Integer>) query.getResultList();

        List<ReceivedPackagingMaterial> rpmList = new ArrayList();
        if (ids != null) {
            for (Integer id : ids) {
                rpmList.add(em.find(ReceivedPackagingMaterial.class, id));
            }
        }

        return rpmList;
    }
}
