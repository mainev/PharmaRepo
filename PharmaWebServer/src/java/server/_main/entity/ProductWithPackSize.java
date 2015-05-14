/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server._main.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import server.mbr.entity.MBR;

/**
 *
 * @author maine
 */
@Entity
@Table(name = "product_packaging_size", schema = "main")
@XmlRootElement
public class ProductWithPackSize implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne
    private Product productId;
    
    @JoinColumn(name = "packaging_size_id", referencedColumnName = "id")
    @ManyToOne
    private PackagingSize packagingSizeId;
    
    @OneToMany(mappedBy = "productWithPackSizeId")
    private Collection<MBR> mbrCollection;

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

    public PackagingSize getPackagingSizeId() {
        return packagingSizeId;
    }

    public void setPackagingSizeId(PackagingSize packagingSizeId) {
        this.packagingSizeId = packagingSizeId;
    }

    @XmlTransient
    public Collection<MBR> getMbrCollection() {
        return mbrCollection;
    }

    public void setMbrCollection(Collection<MBR> mbrCollection) {
        this.mbrCollection = mbrCollection;
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
        if (!(object instanceof ProductWithPackSize)) {
            return false;
        }
        ProductWithPackSize other = (ProductWithPackSize) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server._main.entity.ProductWithPackSize[ id=" + id + " ]";
    }

}
