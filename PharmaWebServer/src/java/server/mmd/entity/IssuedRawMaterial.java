/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.mmd.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
 * @author maine
 */
@Entity(name="MMD_ISSUED_RAW_MATERIAL")
@Table(name = "issued_raw_material", schema="mmd")
@XmlRootElement
public class IssuedRawMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantity")
    private Double quantity;
    @Size(max = 10)
    @Column(name = "batch_no")
    private String batchNo;
    @Size(max = 10)
    @Column(name = "po_no")
    private String poNo;
    @Size(max = 100)
    @Column(name = "issued_by")
    private String issuedBy;
    @Size(max = 100)
    @Column(name = "dispensed_by")
    private String dispensedBy;
    @Size(max = 5)
    @Column(name = "unit")
    private String unit;
    
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne
    private Product productId;

    @ManyToOne
    @JoinColumn(name = "received_raw_material_id", referencedColumnName = "id")
    private ReceivedRawMaterial receivedRawMaterialId;
    
    @Column(name = "date_issued")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateIssued;

    public IssuedRawMaterial() {
    }

    public IssuedRawMaterial(Integer id) {
        this.id = id;
    }

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

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public String getDispensedBy() {
        return dispensedBy;
    }

    public void setDispensedBy(String dispensedBy) {
        this.dispensedBy = dispensedBy;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IssuedRawMaterial)) {
            return false;
        }
        IssuedRawMaterial other = (IssuedRawMaterial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.mmd.entity.IssuedRawMaterial[ id=" + id + " ]";
    }

    public ReceivedRawMaterial getReceivedRawMaterialId() {
        return receivedRawMaterialId;
    }

    public void setReceivedRawMaterialId(ReceivedRawMaterial receivedRawMaterialId) {
        this.receivedRawMaterialId = receivedRawMaterialId;
    }

    public Date getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(Date dateIssued) {
        this.dateIssued = dateIssued;
    }
    
    

}
