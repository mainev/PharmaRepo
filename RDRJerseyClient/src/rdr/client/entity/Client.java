/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@XmlRootElement
public class Client implements Serializable {
     private static final long serialVersionUID = 1L;
    private Short id;
    private String name;
    private Collection<RawMaterial> rawMaterialCollection;
    private Collection<Product> productCollection;
    private Collection<PackagingMaterial> packagingMaterialCollection;

    public Client() {
    }

    public Client(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<RawMaterial> getRawMaterialCollection() {
        return rawMaterialCollection;
    }

    public void setRawMaterialCollection(Collection<RawMaterial> rawMaterialCollection) {
        this.rawMaterialCollection = rawMaterialCollection;
    }

    @XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    @XmlTransient
    public Collection<PackagingMaterial> getPackagingMaterialCollection() {
        return packagingMaterialCollection;
    }

    public void setPackagingMaterialCollection(Collection<PackagingMaterial> packagingMaterialCollection) {
        this.packagingMaterialCollection = packagingMaterialCollection;
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.entity.Client[ id=" + id + " ]";
    }
    
}
