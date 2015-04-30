/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client.entity;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jemuel
 */

@XmlRootElement
public class TransferredPackagingMaterial implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date dateTransferred;
    private String tsNo;
    private String purpose;
    private Integer quantity;
    private Short bundleNo;
    private String status;
    private Boolean isTransferred;
    private Integer id;
    private ReceivedPackagingMaterial receivedPmId;

    public TransferredPackagingMaterial() {
    }

    public TransferredPackagingMaterial(Integer id) {
        this.id = id;
    }

    public Date getDateTransferred() {
        return dateTransferred;
    }

    public void setDateTransferred(Date dateTransferred) {
        this.dateTransferred = dateTransferred;
    }

    public String getTsNo() {
        return tsNo;
    }

    public void setTsNo(String tsNo) {
        this.tsNo = tsNo;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Short getBundleNo() {
        return bundleNo;
    }

    public void setBundleNo(Short bundleNo) {
        this.bundleNo = bundleNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIsTransferred() {
        return isTransferred;
    }

    public void setIsTransferred(Boolean isTransferred) {
        this.isTransferred = isTransferred;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReceivedPackagingMaterial getReceivedPmId() {
        return receivedPmId;
    }

    public void setReceivedPmId(ReceivedPackagingMaterial receivedPmId) {
        this.receivedPmId = receivedPmId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransferredPackagingMaterial)) {
            return false;
        }
        TransferredPackagingMaterial other = (TransferredPackagingMaterial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

   
    @Override
    public String toString() {
        return receivedPmId.getRrNo() + " : " + quantity + " PCS : " + receivedPmId.getPackagingMaterialId().getDescription() ;
    }
    
    public void addQuantity(int q){
        quantity = quantity + q;
    }
    
    public void copy(TransferredPackagingMaterial item){
        
        this.dateTransferred = item.getDateTransferred();
        this.id = item.getId();
        this.isTransferred = item.getIsTransferred();
//        this.purpose = item.getPurpose();
        this.quantity = item.getQuantity();
        this.receivedPmId = item.getReceivedPmId();
        this.status = item.getStatus();
        this.bundleNo = item.getBundleNo();
//        this.tsNo = item.getTsNo();
    }
}
