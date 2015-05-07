/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.rdr.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import server._main.entity.PackagingMaterial;

/**
 *
 * @author Maine
 */
@Entity(name = "RDR_RECEIVED_PACKAGING_MATERIAL")
@Table(name = "received_packaging_material", schema = "rdr")
@XmlRootElement
public class ReceivedPackagingMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 10)
    @Column(name = "rr_no")
    private String rrNo;
    @Size(max = 15)
    @Column(name = "control_no")
    private String controlNo;
    @Size(max = 15)
    @Column(name = "po_no")
    private String poNo;
    @Column(name = "quantity")
    private Integer quantity;
    @Size(max = 5)
    @Column(name = "unit")
    private String unit;
    @Column(name = "quantity_per_bundle")
    private Integer quantityPerBundle;
    @Column(name = "date_received")
    @Temporal(TemporalType.DATE)
    private Date dateReceived;
    @Size(max = 20)
    @Column(name = "supplier")
    private String supplier;
    @Size(max = 20)
    @Column(name = "dr_no")
    private String drNo;
    @ManyToOne
    @JoinColumn(name = "packaging_material_id", referencedColumnName = "id")
    private PackagingMaterial packagingMaterialId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "unit_cost")
    private Double unitCost;
    @OneToMany(mappedBy = "receivedPmId")
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

    /**
     * Returns total quantity of packaging materials tagged as APPROVED.
     * (Inventory 2)
     *
     * @return
     */
    public double getInventory2Quantity() {
        double availableQty = 0;

        for (TransferredPackagingMaterial tpm : transferredPmCollection) {
            if (tpm.isApproved()) {
                availableQty += tpm.getQuantity();
            }
        }

        return availableQty;
    }

    /**
     * Returns total quantity of transferred materials.
     *
     * @return
     */
    public double getIssuance() {
        double total = 0;

        for (TransferredPackagingMaterial tpm : transferredPmCollection) {
            if (tpm.isTransferred()) {
                total += tpm.getQuantity();
            }
        }

        return total;
    }

    /**
     * Returns total quantity of packaging materials tagged as QUARANTINE.
     * (Inventory 1)
     *
     * @return
     */
    public double getInventory1Quantity() {
        double total = 0;

        for (TransferredPackagingMaterial tpm : transferredPmCollection) {
            if (tpm.isQuarantine() && !tpm.isTransferred()) {
                total += tpm.getQuantity();
            }
        }

        return total;
    }

    /**
     * Returns total cost of the received packaging material.
     *
     * @return
     */
    public double getTotalCost() {
        return unitCost * quantity;
    }
}
