/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author H
 */
@Entity
@Table(name = "SUPPLIER_MATERIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SupplierMaterial.findAll", query = "SELECT s FROM SupplierMaterial s"),
    @NamedQuery(name = "SupplierMaterial.findByExperiencenote", query = "SELECT s FROM SupplierMaterial s WHERE s.experiencenote = :experiencenote"),
    @NamedQuery(name = "SupplierMaterial.findByMaterialmaterialid", query = "SELECT s FROM SupplierMaterial s WHERE s.supplierMaterialPK.materialmaterialid = :materialmaterialid"),
    @NamedQuery(name = "SupplierMaterial.findBySuppliersupplierid", query = "SELECT s FROM SupplierMaterial s WHERE s.supplierMaterialPK.suppliersupplierid = :suppliersupplierid")})
public class SupplierMaterial implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SupplierMaterialPK supplierMaterialPK;
    @Size(max = 255)
    @Column(name = "EXPERIENCENOTE")
    private String experiencenote;
    @JoinColumn(name = "SUPPLIERSUPPLIERID", referencedColumnName = "SUPPLIERID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Supplier supplier;
    @JoinColumn(name = "MATERIALMATERIALID", referencedColumnName = "MATERIALID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Material material;

    public SupplierMaterial() {
    }

    public SupplierMaterial(SupplierMaterialPK supplierMaterialPK) {
        this.supplierMaterialPK = supplierMaterialPK;
    }

    public SupplierMaterial(int materialmaterialid, int suppliersupplierid) {
        this.supplierMaterialPK = new SupplierMaterialPK(materialmaterialid, suppliersupplierid);
    }

    public SupplierMaterialPK getSupplierMaterialPK() {
        return supplierMaterialPK;
    }

    public void setSupplierMaterialPK(SupplierMaterialPK supplierMaterialPK) {
        this.supplierMaterialPK = supplierMaterialPK;
    }

    public String getExperiencenote() {
        return experiencenote;
    }

    public void setExperiencenote(String experiencenote) {
        this.experiencenote = experiencenote;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (supplierMaterialPK != null ? supplierMaterialPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SupplierMaterial)) {
            return false;
        }
        SupplierMaterial other = (SupplierMaterial) object;
        if ((this.supplierMaterialPK == null && other.supplierMaterialPK != null) || (this.supplierMaterialPK != null && !this.supplierMaterialPK.equals(other.supplierMaterialPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thomasmore.entity.SupplierMaterial[ supplierMaterialPK=" + supplierMaterialPK + " ]";
    }
    
}
