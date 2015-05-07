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
import server.rdr.entity.ReceivedPackagingMaterial;
import server.rdr.entity.TransferredPackagingMaterial;

/**
 *
 * @author Maine
 */
@Stateless
public class TransferredPackagingMaterialFacade {

   @PersistenceContext(unitName = "PharmaWebServerPU")
    private EntityManager em;

    
    public List<TransferredPackagingMaterial> findAll() {
        return em.createQuery("select r from RDR_TRANSFERRED_PACKAGING_MATERIAL r").getResultList();
    }
    
    public void save(TransferredPackagingMaterial tpm) {
      
        em.persist(tpm);    
    }
    
    
    public List<TransferredPackagingMaterial> getAvailablePm(int pmId) {
        //temporary method
       List<TransferredPackagingMaterial> tpmList = em.createQuery("SELECT t FROM RDR_TRANSFERRED_PACKAGING_MATERIAL t where t.receivedPmId.packagingMaterialId.id = :pmId AND t.isTransferred = 'FALSE' AND t.status='APPROVED' ")
                .setParameter("pmId", pmId)
                .getResultList();
       return tpmList;
    }
    
    public List<TransferredPackagingMaterial> getQuarantinePm(int pmId){
        List<TransferredPackagingMaterial> tpmList = em.createQuery("SELECT t FROM RDR_TRANSFERRED_PACKAGING_MATERIAL t where t.receivedPmId.packagingMaterialId.id = :pmId AND t.isTransferred = 'FALSE' AND t.status='QUARANTINE' ")
                .setParameter("pmId", pmId)
                .getResultList();
       return tpmList;
    }
//
//   public  void transferToMMD(List<TransferredPm> tpmList) {
//        if(!tpmList.isEmpty()){            
//            for(TransferredPm tpm: tpmList){               
//                
//                
//                if(tpm.getId()!=null){
//                    TransferredPm tpmFromDb = em.find(TransferredPm.class, tpm.getId());
//                    tpmFromDb.setIsTransferred(Boolean.TRUE);
//                    tpmFromDb.setPurpose(tpm.getPurpose());
//                    tpmFromDb.setTsNo(tpm.getTsNo());
//                    tpmFromDb.setDateTransferred(new Date());
//                }
//                else{
//                    em.persist(tpm);
//                    TransferredPm tpmFromDb = em.find(TransferredPm.class, tpm.getParentId());
//                    tpmFromDb.setQuantity(tpmFromDb.getQuantity()-tpm.getQuantity());
//                }
//            }
//        }   
//   }

    
   public  void transferToMMD(List<TransferredPackagingMaterial> tpmList) {
        if(!tpmList.isEmpty()){            
            for(TransferredPackagingMaterial tpm: tpmList){               
                TransferredPackagingMaterial dbItem = em.find(TransferredPackagingMaterial.class, tpm.getId());
                if(dbItem.getQuantity() == tpm.getQuantity()){
                    dbItem.setIsTransferred(Boolean.TRUE);
                    dbItem.setPurpose(tpm.getPurpose());
                    dbItem.setTsNo(tpm.getTsNo());
                    dbItem.setDateTransferred(new Date());
                }else{
                    dbItem.setQuantity(dbItem.getQuantity()-tpm.getQuantity());
                    tpm.setId(null);
                    tpm.setDateTransferred(new Date());
                    tpm.setIsTransferred(Boolean.TRUE);
                    em.persist(tpm);
                
                
                }
//                if(tpm.getId()!=null){
//                    TransferredPm tpmFromDb = em.find(TransferredPm.class, tpm.getId());
//                    tpmFromDb.setIsTransferred(Boolean.TRUE);
//                    tpmFromDb.setPurpose(tpm.getPurpose());
//                    tpmFromDb.setTsNo(tpm.getTsNo());
//                    tpmFromDb.setDateTransferred(new Date());
//                }
//                else{
//                    em.persist(tpm);
//                    TransferredPm tpmFromDb = em.find(TransferredPm.class, tpm.getParentId());
//                    tpmFromDb.setQuantity(tpmFromDb.getQuantity()-tpm.getQuantity());
//                }
            }
        }   
   }

    public List<TransferredPackagingMaterial> generateBundles(List<TransferredPackagingMaterial> tpmList) {
        
        List<TransferredPackagingMaterial> generatedBundlesFromDb = new ArrayList();
        
        for(TransferredPackagingMaterial tpm : tpmList){
            em.persist(tpm);
            em.flush();
            generatedBundlesFromDb.add(em.find(TransferredPackagingMaterial.class, tpm.getId()));
        }
        return generatedBundlesFromDb;         
    }
    
    public List<TransferredPackagingMaterial> getQuarantineTransferredPm(Integer receivedPmID) {
        ReceivedPackagingMaterial rpm = em.find(ReceivedPackagingMaterial.class, receivedPmID);
         List<TransferredPackagingMaterial> tpmList = (List<TransferredPackagingMaterial>) rpm.getTransferredPmCollection();
        
         List<TransferredPackagingMaterial> quarantineTPM = new ArrayList();
        
        for(TransferredPackagingMaterial tpm : tpmList)            
            if(tpm.getStatus().equalsIgnoreCase("QUARANTINE"))
                quarantineTPM.add(tpm);
        return quarantineTPM;
    }

    public TransferredPackagingMaterial approveReceivedRm(TransferredPackagingMaterial transferredPm) {
        TransferredPackagingMaterial tpm = em.find(TransferredPackagingMaterial.class, transferredPm.getId());
        
        tpm.setStatus("APPROVED");
        em.persist(tpm);
        return em.find(TransferredPackagingMaterial.class, tpm.getId() );
    }
    
}
