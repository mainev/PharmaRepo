/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.entity;

import java.io.Serializable;
import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maine
 */
@XmlRootElement
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String code;
    private String brandName;
    private String genericName;
    private String classification;
    private Client clientId;
    private String vrNo;
    private Short shelfLife;
     private Area areaId;
     
     //collection of product_packaging_size
    private Collection<PackagingSize> packagingSizeCollection;

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public String getVrNo() {
        return vrNo;
    }

    public void setVrNo(String vrNo) {
        this.vrNo = vrNo;
    }

    public Short getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Short shelfLife) {
        this.shelfLife = shelfLife;
    }

   

    public Area getAreaId() {
        return areaId;
    }

    public void setAreaId(Area areaId) {
        this.areaId = areaId;
    }

    public Collection<PackagingSize> getPackagingSizeCollection() {
        return packagingSizeCollection;
    }

    public void setPackagingSizeCollection(Collection<PackagingSize> packagingSizeCollection) {
        this.packagingSizeCollection = packagingSizeCollection;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return code + " : " + brandName;
    }
    
    public ObservableList<PackagingSize> packagingSizeCollectionProperty(){
        ObservableList<PackagingSize> result = FXCollections.observableArrayList();
        
        if(!packagingSizeCollection.isEmpty()){
            for(PackagingSize p : packagingSizeCollection){
                result.add(p);
            }
        }
        return result;
    }

    
}
