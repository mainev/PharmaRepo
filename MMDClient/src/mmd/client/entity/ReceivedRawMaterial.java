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
 * @author maine
 */
@XmlRootElement
public class ReceivedRawMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Date dateReceived;
    private Double quantity;
    private String unit;
    private String batchNo;
    private String qcControlNo;
    private Date manufacturingDate;
    private Date expDate;
    private String rrNo;
    private String receivedBy;
    private RawMaterial rawMaterialId;
    public ReceivedRawMaterial() {
    }
    
    public ReceivedRawMaterial(RawMaterial rawMaterial, Date dateReceived, double quantity, String unit,
            String batchNo, String qcControlNo, Date mfgDate, Date expDate, String rrNo, String receivedBy){
        this.rawMaterialId = rawMaterial;
        this.dateReceived = dateReceived;
        this.quantity = quantity;
        this.unit = unit;
        this.batchNo = batchNo;
        this.qcControlNo = qcControlNo;
        this.manufacturingDate = mfgDate;
        this.expDate = expDate;
        this.rrNo = rrNo;
        this.receivedBy = receivedBy;
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

    

    
   
    
   
}
