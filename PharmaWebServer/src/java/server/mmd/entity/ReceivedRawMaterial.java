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
import server._main.entity.RawMaterial;

/**
 *
 * @author maine
 */
@Entity(name = "mmd.received_raw_material")
@Table(name = "received_raw_material", schema = "mmd")
@XmlRootElement
public class ReceivedRawMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "date_received")
    @Temporal(TemporalType.DATE)
    private Date dateReceived;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "unit")
    @Size(max = 5)
    private String unit;

    @Column(name = "batch_no")
    @Size(max = 10)
    private String batchNo;

    @Column(name = "qc_control_no")
    @Size(max = 15)
    private String qcControlNo;

    @Column(name = "manufacturing_date")
    @Temporal(TemporalType.DATE)
    private Date manufacturingDate;

    @Column(name = "exp_date")
    @Temporal(TemporalType.DATE)
    private Date expDate;

    @Column(name = "rr_no")
    @Size(max = 10)
    private String rrNo;

    @Column(name = "received_by")
    @Size(max = 50)
    private String receivedBy;

    @ManyToOne
    @JoinColumn(name = "raw_material_id", referencedColumnName = "id")
    private RawMaterial rawMaterialId;

    @OneToMany(mappedBy = "receivedRawMaterialId")
    private Collection<IssuedRawMaterial> mmdIssuedRawMaterialCollection;

    public ReceivedRawMaterial() {
    }

    public ReceivedRawMaterial(Integer id) {
        this.id = id;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
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

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getQcControlNo() {
        return qcControlNo;
    }

    public void setQcControlNo(String qcControlNo) {
        this.qcControlNo = qcControlNo;
    }

    public Date getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(Date manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getRrNo() {
        return rrNo;
    }

    public void setRrNo(String rrNo) {
        this.rrNo = rrNo;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public RawMaterial getRawMaterialId() {
        return rawMaterialId;
    }

    public void setRawMaterialId(RawMaterial rawMaterialId) {
        this.rawMaterialId = rawMaterialId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (!(object instanceof ReceivedRawMaterial)) {
            return false;
        }
        ReceivedRawMaterial other = (ReceivedRawMaterial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.mmd.entity.ReceivedRawMaterial[ id=" + id + " ]";
    }

    public Collection<IssuedRawMaterial> getMmdIssuedRawMaterialCollection() {
        return mmdIssuedRawMaterialCollection;
    }

    public void setMmdIssuedRawMaterialCollection(Collection<IssuedRawMaterial> mmdIssuedRawMaterialCollection) {
        this.mmdIssuedRawMaterialCollection = mmdIssuedRawMaterialCollection;
    }

    
   
}
