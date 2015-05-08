/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.mmd.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import server._main.entity.Product;

/**
 *
 * @author Maine
 */
@Entity(name = "MMD_ISSUED_PACKAGING_MATERIAL")
@Table(name = "issued_packaging_material", schema = "mmd")
@XmlRootElement
public class IssuedPackagingMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantity")
    private Double quantity;

    @Size(max = 5)
    @Column(name = "unit")
    private String unit;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product productId;
    
    @Size(max = 10)
    @Column(name = "batch_no")
    private String batchNo;
    
    @Size(max = 50)
    @Column(name = "issued_by")
    private String issuedBy;
    
    @ManyToOne
    @JoinColumn(name = "received_packaging_material_id", referencedColumnName = "id")
    private ReceivedPackagingMaterial receivedPackagingMaterialId;
    
    @Column(name = "date_issued")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateIssued;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public ReceivedPackagingMaterial getReceivedPackagingMaterialId() {
        return receivedPackagingMaterialId;
    }

    public void setReceivedPackagingMaterialId(ReceivedPackagingMaterial receivedPackagingMaterialId) {
        this.receivedPackagingMaterialId = receivedPackagingMaterialId;
    }

    public Date getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(Date dateIssued) {
        this.dateIssued = dateIssued;
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
        if (!(object instanceof IssuedPackagingMaterial)) {
            return false;
        }
        IssuedPackagingMaterial other = (IssuedPackagingMaterial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + id + " ]";
    }

}
