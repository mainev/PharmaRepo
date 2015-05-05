/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmd.client.entity;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maine
 */
@XmlRootElement
public class ReceivedPackagingMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Date dateReceived;
    private String rrNo;
    private Double quantity;
    private String unit;
    private String qcControlNo;
    private String receivedBy;
    private PackagingMaterial packagingMaterialId;
    
    public ReceivedPackagingMaterial() {
    }

    public ReceivedPackagingMaterial(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getRrNo() {
        return rrNo;
    }

    public void setRrNo(String rrNo) {
        this.rrNo = rrNo;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getQcControlNo() {
        return qcControlNo;
    }

    public void setQcControlNo(String qcControlNo) {
        this.qcControlNo = qcControlNo;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public PackagingMaterial getPackagingMaterialId() {
        return packagingMaterialId;
    }

    public void setPackagingMaterialId(PackagingMaterial packagingMaterialId) {
        this.packagingMaterialId = packagingMaterialId;
    }

   
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ReceivedPackagingMaterial)) {
            return false;
        }
        ReceivedPackagingMaterial other = (ReceivedPackagingMaterial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " "+id;
    }

}
