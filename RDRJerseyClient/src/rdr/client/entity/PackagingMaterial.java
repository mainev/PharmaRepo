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
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Admin
 */
@XmlRootElement
public class PackagingMaterial implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String code;
    private String description;
    private Client clientId;
    private Collection<ReceivedPackagingMaterial> receivedPmCollection;

    public PackagingMaterial() {
    }

    public PackagingMaterial(Integer id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof PackagingMaterial)) {
            return false;
        }
        PackagingMaterial other = (PackagingMaterial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.entity.PackagingMaterial[ id=" + id + " ]";
    }
    
     @XmlTransient
    @JsonIgnore
    public Collection<ReceivedPackagingMaterial> getReceivedPmCollection() {
        return receivedPmCollection;
    }

    public void setReceivedPmCollection(Collection<ReceivedPackagingMaterial> receivedPmCollection) {
        this.receivedPmCollection = receivedPmCollection;
    }
}
