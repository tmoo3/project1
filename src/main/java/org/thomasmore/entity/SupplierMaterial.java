/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "SupplierMaterial.findByExperiencenote", query = "SELECT s FROM SupplierMaterial s WHERE s.experiencenote = :experiencenote")
})
public class SupplierMaterial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SUPPLIERMATERIALID")
    private Integer suppliermaterialid;
    @Size(max = 255)
    @Column(name = "EXPERIENCENOTE")
    private String experiencenote;
    @JoinColumn(name = "SUPPLIERSUPPLIERID", referencedColumnName = "SUPPLIERID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Supplier supplier;
    @JoinColumn(name = "MATERIALMATERIALID", referencedColumnName = "MATERIALID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Material material;

    public SupplierMaterial() {
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

    public Integer getSuppliermaterialid() {
        return suppliermaterialid;
    }

    public void setSuppliermaterialid(Integer suppliermaterialid) {
        this.suppliermaterialid = suppliermaterialid;
    }
}