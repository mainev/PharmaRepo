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
import server._main.entity.RawMaterial;

/**
 *
 * @author Maine
 */
@Entity(name = "RDR_RECEIVED_RAW_MATERIAL")
@Table(name = "received_raw_material", schema="rdr")
@XmlRootElement
public class ReceivedRawMaterial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 10)
    @Column(name = "batch_no")
    private String batchNo;
    @Size(max = 10)
    @Column(name = "rr_no")
    private String rrNo;
    @Column(name = "date_received")
    @Temporal(TemporalType.DATE)
    private Date dateReceived;
    @Column(name = "exp_date")
    @Temporal(TemporalType.DATE)
    private Date expDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantity")
    private Double quantity;
    @Size(max = 5)
    @Column(name = "unit")
    private String unit;
    @Column(name = "amount_per_container")
    private Double amountPerContainer;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
  @ManyToOne
    @JoinColumn(name = "raw_material_id", referencedColumnName = "id")
    private RawMaterial rawMaterialId;
    @Column(name = "unit_cost")
    private Double unitCost;
    @OneToMany(mappedBy = "receivedRmId")
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
 
     /**
     * Returns total quantity of raw materials tagged as APPROVED in the inventory 2.
     * @return 
     */
    public double getInventory2Quantity(){
        double availableQty = 0;
        
        for(TransferredRawMaterial trm: transferredRmCollection){
            if(trm.isApproved()){
                
                availableQty += trm.getQuantity();
            }
        }
        //System.out.println(availableQty);
        return availableQty;
    }
    
    /**
     * Returns total quantity of transferred materials. 
     * @return 
     */
   public double getIssuance(){
       double total = 0;
       
       for(TransferredRawMaterial trm: transferredRmCollection){
           if(trm.isTransferred()){
               total += trm.getQuantity();
           }
       }
       return total;
   }
   
   /**
    * Returns total quantity of raw materials tagged as QUARANTINE. (Inventory 1)
    * @return 
    */
   public double getInventory1Quantity(){
       double total = 0;
       for(TransferredRawMaterial trm: transferredRmCollection){
           if(trm.isQuarantine() && !trm.isTransferred()){
                
               total += trm.getQuantity();
           }
       }
      // System.out.println(total);
       return total;
   }
   
   /**
    * Returns total cost of the received raw material.
    * @return 
    */
   public double getTotalCost(){
       return unitCost * quantity;
   }
    
}
