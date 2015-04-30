/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@XmlRootElement
public class TransferredRawMaterial implements Serializable {
private static final long serialVersionUID = 1L;
    private Boolean isTransferred;
    private String tsNo;
    private String soNo;
    private Date dateTransferred;
    private String unit;
    private Double quantity;
    private Short containerNo;
    private Integer id;
    private Product productId;
    private String status;
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
        return "["+receivedRmId.getRrNo() + "] "+quantity + " " +unit+" - " + receivedRmId.getRawMaterialId().getName() +" Exp: "+receivedRmId.getExpDate() ;
    }
    
     public boolean isApproved(){
        return status.equals("APPROVED");
    }

     public ObservableValue<String> dateTransferredProperty(){
         SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return new SimpleStringProperty(sdf.format(dateTransferred));
    }

}
