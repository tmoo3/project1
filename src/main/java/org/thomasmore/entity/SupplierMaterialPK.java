/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author H
 */
@Embeddable
public class SupplierMaterialPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "MATERIALMATERIALID")
    private int materialmaterialid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUPPLIERSUPPLIERID")
    private int suppliersupplierid;

    public SupplierMaterialPK() {
    }

    public SupplierMaterialPK(int materialmaterialid, int suppliersupplierid) {
        this.materialmaterialid = materialmaterialid;
        this.suppliersupplierid = suppliersupplierid;
    }

    public int getMaterialmaterialid() {
        return materialmaterialid;
    }

    public void setMaterialmaterialid(int materialmaterialid) {
        this.materialmaterialid = materialmaterialid;
    }

    public int getSuppliersupplierid() {
        return suppliersupplierid;
    }

    public void setSuppliersupplierid(int suppliersupplierid) {
        this.suppliersupplierid = suppliersupplierid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) materialmaterialid;
        hash += (int) suppliersupplierid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SupplierMaterialPK)) {
            return false;
        }
        SupplierMaterialPK other = (SupplierMaterialPK) object;
        if (this.materialmaterialid != other.materialmaterialid) {
            return false;
        }
        if (this.suppliersupplierid != other.suppliersupplierid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thomasmore.entity.SupplierMaterialPK[ materialmaterialid=" + materialmaterialid + ", suppliersupplierid=" + suppliersupplierid + " ]";
    }
    
}
