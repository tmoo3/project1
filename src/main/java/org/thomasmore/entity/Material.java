/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author H
 */
@Entity
@Table(name = "MATERIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Material.findAll", query = "SELECT m FROM Material m"),
    @NamedQuery(name = "Material.findByMaterialid", query = "SELECT m FROM Material m WHERE m.materialid = :materialid"),
    @NamedQuery(name = "Material.findByMass", query = "SELECT m FROM Material m WHERE m.mass = :mass"),
    @NamedQuery(name = "Material.findByMaterialquality", query = "SELECT m FROM Material m WHERE m.materialquality = :materialquality"),
    @NamedQuery(name = "Material.findByMaterialquantity", query = "SELECT m FROM Material m WHERE m.materialquantity = :materialquantity"),
    @NamedQuery(name = "Material.findByMaterialtype", query = "SELECT m FROM Material m WHERE m.materialtype = :materialtype"),
    @NamedQuery(name = "Material.findByRentprice", query = "SELECT m FROM Material m WHERE m.rentprice = :rentprice")})
public class Material implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MATERIALID")
    private Integer materialid;
    @Lob
    @Column(name = "PICTURE")
    private byte[] picture;
    @Column(name = "MASS")
    private Integer mass;
    @Column(name = "MATERIALQUALITY")
    private Integer materialquality;
    @Column(name = "MATERIALQUANTITY")
    private Integer materialquantity;
    @Column(name = "MATERIALTYPE")
    private Integer materialtype;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "RENTPRICE")
    private Double rentprice;
    @JoinTable(name = "MATERIAL_STOCKAGE", joinColumns = {
        @JoinColumn(name = "MATERIALID", referencedColumnName = "MATERIALID")}, inverseJoinColumns = {
        @JoinColumn(name = "WAREHOUSEID", referencedColumnName = "WAREHOUSEID")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Warehouse> warehouseCollection;
    @OneToMany(mappedBy = "materialid", fetch = FetchType.EAGER)
    private Collection<Availability> availabilityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material", fetch = FetchType.EAGER)
    private Collection<SupplierMaterial> supplierMaterialCollection;
    @OneToMany(mappedBy = "materialid", fetch = FetchType.EAGER)
    private Collection<Equipmentrent> equipmentrentCollection;

    public Material() {
    }

    public Material(Integer materialid) {
        this.materialid = materialid;
    }

    public Integer getMaterialid() {
        return materialid;
    }

    public void setMaterialid(Integer materialid) {
        this.materialid = materialid;
    }

    public Integer getMass() {
        return mass;
    }

    public void setMass(Integer mass) {
        this.mass = mass;
    }

    public Integer getMaterialquality() {
        return materialquality;
    }

    public void setMaterialquality(Integer materialquality) {
        this.materialquality = materialquality;
    }

    public Integer getMaterialquantity() {
        return materialquantity;
    }

    public void setMaterialquantity(Integer materialquantity) {
        this.materialquantity = materialquantity;
    }

    public Integer getMaterialtype() {
        return materialtype;
    }

    public void setMaterialtype(Integer materialtype) {
        this.materialtype = materialtype;
    }

    public Double getRentprice() {
        return rentprice;
    }

    public void setRentprice(Double rentprice) {
        this.rentprice = rentprice;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @XmlTransient
    public Collection<Warehouse> getWarehouseCollection() {
        return warehouseCollection;
    }

    public void setWarehouseCollection(Collection<Warehouse> warehouseCollection) {
        this.warehouseCollection = warehouseCollection;
    }

    @XmlTransient
    public Collection<Availability> getAvailabilityCollection() {
        return availabilityCollection;
    }

    public void setAvailabilityCollection(Collection<Availability> availabilityCollection) {
        this.availabilityCollection = availabilityCollection;
    }

    @XmlTransient
    public Collection<SupplierMaterial> getSupplierMaterialCollection() {
        return supplierMaterialCollection;
    }

    public void setSupplierMaterialCollection(Collection<SupplierMaterial> supplierMaterialCollection) {
        this.supplierMaterialCollection = supplierMaterialCollection;
    }

    @XmlTransient
    public Collection<Equipmentrent> getEquipmentrentCollection() {
        return equipmentrentCollection;
    }

    public void setEquipmentrentCollection(Collection<Equipmentrent> equipmentrentCollection) {
        this.equipmentrentCollection = equipmentrentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materialid != null ? materialid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Material)) {
            return false;
        }
        Material other = (Material) object;
        if ((this.materialid == null && other.materialid != null) || (this.materialid != null && !this.materialid.equals(other.materialid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thomasmore.entity.Material[ materialid=" + materialid + " ]";
    }

}
