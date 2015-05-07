/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.rdr.entity;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Entity(name = "RDR_TRANSFERRED_RAW_MATERIAL")
@Table(name = "transferred_raw_material", schema="rdr")
@XmlRootElement
public class TransferredRawMaterial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "is_transferred")
    private Boolean isTransferred;
    @Size(max = 10)
    @Column(name = "ts_no")
    private String tsNo;
    @Size(max = 10)
    @Column(name = "so_no")
    private String soNo;
    @Column(name = "date_transferred")
    @Temporal(TemporalType.DATE)
    private Date dateTransferred;
    @Size(max = 5)
    @Column(name = "unit")
    private String unit;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantity")
    private Double quantity;
    @Column(name = "container_no")
    private Short containerNo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
   @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne
    private Product productId;
    @Size(max = 15)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "received_rm_id", referencedColumnName = "id")
    @ManyToOne
    private ReceivedRawMaterial receivedRmId;

    public TransferredRawMaterial() {
    }

    public TransferredRawMaterial(Integer id) {
        this.id = id;
    }

   
    public Boolean getIsTransferred() {
        return isTransferred;
    }

    public void setIsTransferred(Boolean isTransferred) {
        this.isTransferred = isTransferred;
    }

    public String getTsNo() {
        return tsNo;
    }

    public void setTsNo(String tsNo) {
        this.tsNo = tsNo;
    }

    public String getSoNo() {
        return soNo;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public Date getDateTransferred() {
        return dateTransferred;
    }

    public void setDateTransferred(Date dateTransferred) {
        this.dateTransferred = dateTransferred;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Short getContainerNo() {
        return containerNo;
    }

    public void setContainerNo(Short containerNo) {
        this.containerNo = containerNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ReceivedRawMaterial getReceivedRmId() {
        return receivedRmId;
    }

    public void setReceivedRmId(ReceivedRawMaterial receivedRmId) {
        this.receivedRmId = receivedRmId;
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
        if (!(object instanceof TransferredRawMaterial)) {
            return false;
        }
        TransferredRawMaterial other = (TransferredRawMaterial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.entity.TransferredRm[ id=" + id + " ]";
    }
    
  
    
      public boolean isQuarantine(){
        return status.equals("QUARANTINE");
    }
    public boolean isApproved(){
        return status.equals("APPROVED");
    }
    
     public Boolean isTransferred() {
        return isTransferred;
    }

    
}
