/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdr.client.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Admin
 */
@XmlRootElement
public class RawMaterial implements Serializable {
    private static final long serialVersionUID = 1L;
   
    private Integer id;
    private String code;
    private String name;
    private String description;
    private String classification;
    private Client clientId;
    private Collection<ReceivedRawMaterial> receivedRmCollection;

    public RawMaterial() {
    }

    public RawMaterial(Integer id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RawMaterial)) {
            return false;
        }
        RawMaterial other = (RawMaterial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

  
    
    @XmlTransient
    @JsonIgnore
    public Collection<ReceivedRawMaterial> getReceivedRmCollection() {
        return receivedRmCollection;
    }

    public void setReceivedRmCollection(Collection<ReceivedRawMaterial> receivedRmCollection) {
        this.receivedRmCollection = receivedRmCollection;
    }

    
    @Override
    public String toString() {
        return code + " : " +name;
    }
    
    public ObservableValue<String> codeProperty(){
        return new SimpleStringProperty(code);
    }
    
    public ObservableValue<String> nameProperty(){
        return new SimpleStringProperty(name);
    }
    
   
}
