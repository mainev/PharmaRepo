/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server._main.entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;
import server.mbr.entity.PackagingMaterialRequirements;
import server.rdr.entity.ReceivedPackagingMaterial;

/**
 *
 * @author Maine
 */
@Entity
@Table(name = "packaging_material", schema = "main")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PackagingMaterial.findAll", query = "SELECT p FROM PackagingMaterial p")})
public class PackagingMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 15)
    @Column(name = "code")
    private String code;
    @Size(max = 100)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @ManyToOne
    private Client clientId;

    @OneToMany(mappedBy = "packagingMaterialId")
    private Collection<ReceivedPackagingMaterial> receivedPmCollection;

    @OneToMany(mappedBy = "packagingMaterialId")
    private Collection<server.mmd_tolling.entity.ReceivedPackagingMaterial> mmdReceivedPmCollection;

    @OneToMany(mappedBy = "packagingMaterialId")
    private Collection<PackagingMaterialRequirements> packagingMaterialRequirementsCollection;

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

    @XmlTransient
    @JsonIgnore
    public Collection<server.mmd_tolling.entity.ReceivedPackagingMaterial> getMmdReceivedPmCollection() {
        return mmdReceivedPmCollection;
    }

    public void setMmdReceivedPmCollection(Collection<server.mmd_tolling.entity.ReceivedPackagingMaterial> receivedPmCollection) {
        this.mmdReceivedPmCollection = receivedPmCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<PackagingMaterialRequirements> getPackagingMaterialRequirementsCollection() {
        return packagingMaterialRequirementsCollection;
    }

    public void setPackagingMaterialRequirementsCollection(Collection<PackagingMaterialRequirements> packagingMaterialRequirementsCollection) {
        this.packagingMaterialRequirementsCollection = packagingMaterialRequirementsCollection;
    }

}
