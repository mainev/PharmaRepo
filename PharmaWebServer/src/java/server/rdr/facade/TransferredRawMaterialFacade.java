/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.rdr.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import server.rdr.entity.ReceivedRawMaterial;
import server.rdr.entity.TransferredRawMaterial;

/**
 *
 * @author Maine
 */
@Stateless
public class TransferredRawMaterialFacade {

    @EJB
    private ReceivedRawMaterialFacade receivedRmFacade;

    @PersistenceContext(unitName = "PharmaWebServerPU")
    private EntityManager em;

    public List<TransferredRawMaterial> findAll() {
        return em.createQuery("select t from RDR_TRANSFERRED_RAW_MATERIAL t where t.isTransferred = true").getResultList();
    }

    public TransferredRawMaterial findById(int id) {
        return em.find(TransferredRawMaterial.class, id);
    }

    /**
     * Generates container for the received raw material.
     */
    public void insertGeneratedContainer(List<TransferredRawMaterial> trmList, int rrId) {
        if (!trmList.isEmpty()) {
            ReceivedRawMaterial rrm = receivedRmFacade.findById(rrId);
            for (TransferredRawMaterial trm : trmList) {
                trm.setReceivedRmId(rrm);
                em.persist(trm);
            }
        }
    }

    public List<TransferredRawMaterial> updateTransferredRm(List<TransferredRawMaterial> selectedRmToTransfer) {
        for (TransferredRawMaterial trm : selectedRmToTransfer) {
            Query q = em.createQuery("UPDATE RDR_TRANSFERRED_RAW_MATERIAL t SET t.productId = :product, t.dateTransferred = :today, t.tsNo = :tsNo, t.soNo = :soNo, t.isTransferred = :isTransferred WHERE t.id = :id ");

            q.setParameter("product", trm.getProductId());
            q.setParameter("today", trm.getDateTransferred());
            q.setParameter("tsNo", trm.getTsNo());
            q.setParameter("soNo", trm.getSoNo());
            q.setParameter("isTransferred", trm.getIsTransferred());
            q.setParameter("id", trm.getId());
            q.executeUpdate();
        }

        return selectedRmToTransfer;

    }

    public List<TransferredRawMaterial> getTransferableRm(int rmId) {
        Date today = new Date();
        return em.createQuery("SELECT t from RDR_TRANSFERRED_RAW_MATERIAL t where t.receivedRmId.rawMaterialId.id = :rmId AND t.receivedRmId.expDate >= :today AND t.status LIKE :status AND t.isTransferred <> true ORDER by t.receivedRmId.expDate, t.containerNo ASC")
                .setParameter("rmId", rmId)
                .setParameter("today", today)
                .setParameter("status", "APPROVED")
                .getResultList();

    }

      public List<TransferredRawMaterial> getQuarantineRm(int rmId){
        List<TransferredRawMaterial> trmList = em.createQuery("SELECT t FROM RDR_TRANSFERRED_RAW_MATERIAL t where t.receivedRmId.rawMaterialId.id = :rmId AND t.isTransferred = 'FALSE' AND t.status='QUARANTINE' ")
                .setParameter("rmId", rmId)
                .getResultList();
       return trmList;
    }
    public List<TransferredRawMaterial> findByRmCodeAndRrNo(String rmCode, String rrNo) {
        return em.createQuery("SELECT t from RDR_TRANSFERRED_RAW_MATERIAL t where t.receivedRmId.rawMaterialId.code = :rmCode AND t.receivedRmId.rrNo LIKE :rrNo AND t.isTransferred = true ORDER by t.dateTransferred ASC")
                .setParameter("rrNo", rrNo)
                .setParameter("rmCode", rmCode)
                .getResultList();
    }
    
     public List<TransferredRawMaterial> getQuarantineTransferredRm(Integer receivedRmID) {
         ReceivedRawMaterial rrm = em.find(ReceivedRawMaterial.class, receivedRmID);
         List<TransferredRawMaterial> trmList = (List<TransferredRawMaterial>) rrm.getTransferredRmCollection();
        
         List<TransferredRawMaterial> quarantineTRM = new ArrayList();
        
        for(TransferredRawMaterial trm : trmList)            
            if(trm.getStatus().equalsIgnoreCase("QUARANTINE"))
                quarantineTRM.add(trm);
        return quarantineTRM;
    }

    public TransferredRawMaterial approveTransferredRm(TransferredRawMaterial transferredRm) {
        TransferredRawMaterial trm = em.find(TransferredRawMaterial.class, transferredRm.getId());
        
        trm.setStatus("APPROVED");
        em.persist(trm);
        return em.find(TransferredRawMaterial.class, trm.getId() );
    }
}
