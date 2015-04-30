/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jemuel
 */

@XmlRootElement
public class ReceivedPackagingMaterial implements Serializable {
   private static final long serialVersionUID = 1L;
    private Integer id;
    private String rrNo;
    private String controlNo;
    private String poNo;
    private Integer quantity;
    private String unit;
    private Integer quantityPerBundle;
    private Date dateReceived;
    private String supplier;
    private String drNo;
    private PackagingMaterial packagingMaterialId;
    private Double unitCost;
    private Collection<TransferredPackagingMaterial> transferredPmCollection;
    

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

    public String getRrNo() {
        return rrNo;
    }

    public void setRrNo(String rrNo) {
        this.rrNo = rrNo;
    }

    public String getControlNo() {
        return controlNo;
    }

    public void setControlNo(String controlNo) {
        this.controlNo = controlNo;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getQuantityPerBundle() {
        return quantityPerBundle;
    }

    public void setQuantityPerBundle(Integer quantityPerBundle) {
        this.quantityPerBundle = quantityPerBundle;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getDrNo() {
        return drNo;
    }

    public void setDrNo(String drNo) {
        this.drNo = drNo;
    }

    public PackagingMaterial getPackagingMaterialId() {
        return packagingMaterialId;
    }

    public void setPackagingMaterialId(PackagingMaterial packagingMaterialId) {
        this.packagingMaterialId = packagingMaterialId;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    @XmlTransient
    public Collection<TransferredPackagingMaterial> getTransferredPmCollection() {
        return transferredPmCollection;
    }

    public void setTransferredPmCollection(Collection<TransferredPackagingMaterial> transferredPmCollection) {
        this.transferredPmCollection = transferredPmCollection;
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
        return "server.entity.ReceivedPm[ id=" + id + " ]";
    }
    
    
    
}
