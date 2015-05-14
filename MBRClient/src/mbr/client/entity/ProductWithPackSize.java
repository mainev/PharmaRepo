/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.entity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maine
 */
@XmlRootElement
public class ProductWithPackSize implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Product productId;
    private PackagingSize packagingSizeId;

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
