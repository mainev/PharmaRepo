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

/**
 *
 * @author Maine
 */
@Entity
@Table(name = "transferred_packaging_material", schema = "rdr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransferredPackagingMaterial.findAll", query = "SELECT t FROM TransferredPackagingMaterial t")})
public class TransferredPackagingMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "date_transferred")
    @Temporal(TemporalType.DATE)
    private Date dateTransferred;
    @Size(max = 10)
    @Column(name = "ts_no")
    private String tsNo;
    @Size(max = 100)
    @Column(name = "purpose")
    private String purpose;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "bundle_no")
    private Short bundleNo;
    @Size(max = 15)
    @Column(name = "status")
    private String status;
    @Column(name = "is_transferred")
    private Boolean isTransferred;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "received_pm_id", referencedColumnName = "id")
    @ManyToOne
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
        return "server.entity.TransferredPm[ id=" + id + " ]";
    }

    public boolean isApproved() {
        return status.equals("APPROVED");
    }

    public boolean isTransferred() {
        return isTransferred;
    }

    public boolean isQuarantine() {
        return status.equals("QUARANTINE");
    }

}
