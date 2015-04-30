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
import server.rdr.domain.RawMaterialStockCard;
import server.rdr.entity.ReceivedRawMaterial;
import server.rdr.entity.TransferredRawMaterial;

/**
 *
 * @author Maine
 */
@Stateless
public class ReceivedRawMaterialFacade {

    @PersistenceContext(unitName = "PharmaWebServerPU")
    private EntityManager em;

    public List<ReceivedRawMaterial> findAll() {
        return em.createQuery("select r from ReceivedRawMaterial r").getResultList();
    }

    public ReceivedRawMaterial save(ReceivedRawMaterial rrm) {
        
        em.persist(rrm);
        em.flush();
        
        return em.find(ReceivedRawMaterial.class, rrm.getId());
    }

    public ReceivedRawMaterial findById(int id) {
        return em.find(ReceivedRawMaterial.class, id);
    }

    /**
     * Returns a list of received raw materials between two dates. (List of stockcard).
     * @param startDate
     * @param endDate
     * @return 
     */
    public List<RawMaterialStockCard> getReceivedRmBetweenDate(Date startDate, Date endDate){
        List<RawMaterialStockCard> rmStockCard = new ArrayList();
        
        List<ReceivedRawMaterial> rrmList = em.createQuery("SELECT r from ReceivedRawMaterial r WHERE r.dateReceived BETWEEN :startDate AND :endDate")
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
        
        for(ReceivedRawMaterial rrm : rrmList){
            RawMaterialStockCard rms = new RawMaterialStockCard();
            rms.setItemCode(rrm.getRawMaterialId().getCode());
            rms.setRrNo(rrm.getRrNo());
            rms.setItemName(rrm.getRawMaterialId().getName());
            rms.setBatchNo(rrm.getBatchNo());
            rms.setExpDate(rrm.getExpDate());
            rms.setInventory2(rrm.getInventory2Quantity());
            rms.setIssuance(rrm.getIssuance());
            rms.setInventory1(rrm.getInventory1Quantity());
            rms.setUnitCost(rrm.getUnitCost());
            rms.setTotalCost(rrm.getTotalCost());
            
            rmStockCard.add(rms);
        
        }
        
       return rmStockCard;
    
    }
    
    public List<ReceivedRawMaterial> findAllQuarantine() {
        Query query = em.createNativeQuery("SELECT distinct(received_rm_id) FROM transferred_raw_material\n" +
                                           "where transferred_raw_material.status = 'QUARANTINE';");
        List<Integer> ids= (List<Integer>) query.getResultList();
        
        List<ReceivedRawMaterial> rrmList = new ArrayList();
        if(ids!=null)
            for(Integer id: ids)
                rrmList.add(em.find(ReceivedRawMaterial.class, id));
        
        return rrmList;
    }
    
    public ReceivedRawMaterial approveReceivedRm(ReceivedRawMaterial receivedRm) {
        ReceivedRawMaterial rrm = em.find(ReceivedRawMaterial.class, receivedRm.getId());
        
        for(TransferredRawMaterial trm: rrm.getTransferredRmCollection()){
            trm.setStatus("APPROVED");
        }
        
        em.persist(rrm);
        return em.find(ReceivedRawMaterial.class, rrm.getId() );
    }
  
}
