/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client.entity;

import java.io.Serializable;
import java.util.Collection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
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
    public String toString() {
        return code + " : " + brandName;
    }

    public ObservableValue<String> brandNameProperty() {
        return new SimpleStringProperty(brandName);
    }

}
