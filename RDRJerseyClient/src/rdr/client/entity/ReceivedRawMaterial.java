/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import rdr.client.utils.DateFormatter;

/**
 *
 * @author Admin
 */
@XmlRootElement
public class ReceivedRawMaterial implements Serializable {

  private static final long serialVersionUID = 1L;
    private String batchNo;
    private String rrNo;
    private Date dateReceived;
    private Date expDate;
    private Double quantity;
    private String unit;
    private Double amountPerContainer;
    private Integer id;
    private RawMaterial rawMaterialId;
    private Double unitCost;
    private Collection<TransferredRawMaterial> transferredRmCollection;

    public ReceivedRawMaterial() {
    }

    public ReceivedRawMaterial(Integer id) {
        this.id = id;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getRrNo() {
        return rrNo;
    }

    public void setRrNo(String rrNo) {
        this.rrNo = rrNo;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
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

    public Double getAmountPerContainer() {
        return amountPerContainer;
    }

    public void setAmountPerContainer(Double amountPerContainer) {
        this.amountPerContainer = amountPerContainer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RawMaterial getRawMaterialId() {
        return rawMaterialId;
    }

    public void setRawMaterialId(RawMaterial rawMaterialId) {
        this.rawMaterialId = rawMaterialId;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    @XmlTransient
    public Collection<TransferredRawMaterial> getTransferredRmCollection() {
        return transferredRmCollection;
    }

    public void setTransferredRmCollection(Collection<TransferredRawMaterial> transferredRmCollection) {
        this.transferredRmCollection = transferredRmCollection;
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
        return "server.entity.ReceivedRm[ id=" + id + " ]";
    }
    public int getTotalNoOfContainer() {
        int quotient = (int)(quantity / amountPerContainer);
        if(getRemainder()!=0)
            return quotient+=1;
        return quotient;
    }

    public double getRemainder(){
        return (quantity % amountPerContainer);
    }
    /**
     * Returns total cost of the received raw material.
     *
     * @return
     */
    public double getTotalCost() {
        return unitCost * quantity;
    }

     public ObservableValue<String> dateReceivedProperty() {
        return new SimpleStringProperty(DateFormatter.convertToString(dateReceived));
    }

    public ObservableValue<String> expDateProperty() {
        return new SimpleStringProperty(DateFormatter.convertToString(expDate));
    }

}
