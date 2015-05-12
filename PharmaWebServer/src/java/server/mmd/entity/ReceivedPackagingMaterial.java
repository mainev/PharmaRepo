/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.mmd.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity(name = "MMD_TOLLING_RECEIVED_PACKAGING_MATERIAL")
@Table(name = "received_packaging_material", schema = "mmd_tolling")
@XmlRootElement
public class ReceivedPackagingMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date_received")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReceived;
    
    @Size(max = 10)
    @Column(name = "rr_no")
    private String rrNo;
    
    @Column(name = "quantity")
    private Double quantity;
    
    @Size(max = 5)
    @Column(name = "unit")
    private String unit;
    
    @Size(max = 15)
    @Column(name = "qc_control_no")
    private String qcControlNo;
    
    @Size(max = 50)
    @Column(name = "received_by")
    private String receivedBy;
    
    @ManyToOne
    @JoinColumn(name = "packaging_material_id", referencedColumnName = "id")
    private PackagingMaterial packagingMaterialId;

    @OneToMany(mappedBy = "receivedPackagingMaterialId")
    private Collection<IssuedPackagingMaterial> mmdTransferredPackagingMaterialCollection;
    
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

    @XmlTransient
    public Collection<IssuedPackagingMaterial> getMmdTransferredPackagingMaterialCollection() {
        return mmdTransferredPackagingMaterialCollection;
    }

    public void setMmdTransferredPackagingMaterialCollection(Collection<IssuedPackagingMaterial> transferredPmCollection) {
        this.mmdTransferredPackagingMaterialCollection = transferredPmCollection;
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
        return "server.mmd.entity.ReceivedPackagingMateral[ id=" + id + " ]";
    }

}
