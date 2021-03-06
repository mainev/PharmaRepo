/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.mbr.entity;

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
import javax.xml.bind.annotation.XmlTransient;
import server._main.entity.ProductWithPackSize;

/**
 *
 * @author maine
 */
@Entity
@Table(name = "mbr", schema = "mbr")
@XmlRootElement
public class MBR implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "batch_size")
    private Double batchSize;

    @Column(name = "batch_no")
    @Size(max = 10)
    private String batchNo;

    @Column(name = "unit")
    @Size(max = 5)
    private String unit;

    @Column(name = "mfg_date")
    @Temporal(TemporalType.DATE)
    private Date mfgDate;

    @Column(name = "exp_date")
    @Temporal(TemporalType.DATE)
    private Date expDate;

    @Column(name = "po_no")
    @Size(max = 15)
    private String poNo;


//    @JoinColumn(name = "product_id", referencedColumnName = "id")
//    @ManyToOne
//    private Product productId;
    
   
    
//     @JoinTable(name = "product_packaging_size", schema="main", 
//            joinColumns = { 
//                   @JoinColumn(name = "product_id", referencedColumnName = "id")
//            }, 
//            inverseJoinColumns = { 
//                   @JoinColumn(name = "packaging_size_id", referencedColumnName = "id")
//            }
//     )
//    @ManyToOne
//    private Product productId;
//     
//       @JoinTable(name = "product_packaging_size", schema="main", 
//            joinColumns = { 
//                   @JoinColumn(name = "product_id", referencedColumnName = "id")
//            }, 
//            inverseJoinColumns = { 
//                   @JoinColumn(name = "packaging_size_id", referencedColumnName = "id")
//            }
//     )
//    @ManyToOne
//    private PackagingSize packagingSizeId;
     
   //@Column(name="product_packaging_size_id")
   //private Integer productPackagingSizeId;
   
   @JoinColumn(name = "product_packaging_size_id", referencedColumnName = "id")
   @ManyToOne
   private ProductWithPackSize productWithPackSizeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Double batchSize) {
        this.batchSize = batchSize;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getMfgDate() {
        return mfgDate;
    }

    public void setMfgDate(Date mfgDate) {
        this.mfgDate = mfgDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    //@XmlTransient
    public ProductWithPackSize getProductWithPackSizeId() {
        return productWithPackSizeId;
    }

    public void setProductWithPackSizeId(ProductWithPackSize productWithPackSizeId) {
        this.productWithPackSizeId = productWithPackSizeId;
    }

    
//    public Product getProductId() {
//        return productId;
//    }
//
//    public void setProductId(Product productId) {
//        this.productId = productId;
//    }
//
//    public PackagingSize getPackagingSizeId() {
//        return packagingSizeId;
//    }
//
//    public void setPackagingSizeId(PackagingSize packagingSizeId) {
//        this.packagingSizeId = packagingSizeId;
//    }

//    @XmlTransient
//    public Integer getProductPackagingSizeId() {
//        return productPackagingSizeId;
//    }
//
//    public void setProductPackagingSizeId(Integer productPackagingSizeId) {
//        this.productPackagingSizeId = productPackagingSizeId;
//    }

    
  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MBR)) {
            return false;
        }
        MBR other = (MBR) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.mbr.entity.MBR[ id=" + id + " ]";
    }

}
