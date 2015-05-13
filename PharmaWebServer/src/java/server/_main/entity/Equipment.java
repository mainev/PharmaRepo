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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;
import server.mbr.entity.EquipmentRequirementsCompounding;
import server.mbr.entity.EquipmentRequirementsEncapsulation;

/**
 *
 * @author maine
 */
@Entity
@Table(name = "equipment", schema = "main")
@XmlRootElement
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short id;

    @Size(max = 15)
    @Column(name = "code")
    private String code;

    @Size(max = 100)
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "equipmentId")
    private Collection<EquipmentRequirementsCompounding> equipmentRequirementsCompoundingCollection;

    @OneToMany(mappedBy = "equipmentId")
    private Collection<EquipmentRequirementsEncapsulation> equipmentRequirementsEncapsulationCollection;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
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

    @XmlTransient
    @JsonIgnore
    public Collection<EquipmentRequirementsCompounding> getEquipmentRequirementsCompoundingCollection() {
        return equipmentRequirementsCompoundingCollection;
    }

    public void setEquipmentRequirementsCompoundingCollection(Collection<EquipmentRequirementsCompounding> equipmentRequirementsCompoundingCollection) {
        this.equipmentRequirementsCompoundingCollection = equipmentRequirementsCompoundingCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<EquipmentRequirementsEncapsulation> getEquipmentRequirementsEncapsulationCollection() {
        return equipmentRequirementsEncapsulationCollection;
    }

    public void setEquipmentRequirementsEncapsulationCollection(Collection<EquipmentRequirementsEncapsulation> equipmentRequirementsEncapsulationCollection) {
        this.equipmentRequirementsEncapsulationCollection = equipmentRequirementsEncapsulationCollection;
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
        if (!(object instanceof Equipment)) {
            return false;
        }
        Equipment other = (Equipment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server._main.entity.Equipment[ id=" + id + " ]";
    }

}
