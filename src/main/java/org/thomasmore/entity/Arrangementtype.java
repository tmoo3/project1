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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author H
 */
@Entity
@Table(name = "ARRANGEMENTTYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arrangementtype.findAll", query = "SELECT a FROM Arrangementtype a"),
    @NamedQuery(name = "Arrangementtype.findByArrangementtypeid", query = "SELECT a FROM Arrangementtype a WHERE a.arrangementtypeid = :arrangementtypeid"),
    @NamedQuery(name = "Arrangementtype.findByArrangementtype", query = "SELECT a FROM Arrangementtype a WHERE a.arrangementtype = :arrangementtype")})
public class Arrangementtype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ARRANGEMENTTYPEID")
    private Integer arrangementtypeid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ARRANGEMENTTYPE")
    private String arrangementtype;
    @JoinTable(name = "ARRANGEMENTTYPEMONITOR", joinColumns = {
        @JoinColumn(name = "ARRANGEMENTTYPEARRANGEMENTTYPEID", referencedColumnName = "ARRANGEMENTTYPEID")}, inverseJoinColumns = {
        @JoinColumn(name = "MONITORMONITORID", referencedColumnName = "MONITORID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Monitor> monitorCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "arrangementtype", fetch = FetchType.LAZY)
    private Arrangement arrangement;

    public Arrangementtype() {
    }

    public Arrangementtype(Integer arrangementtypeid) {
        this.arrangementtypeid = arrangementtypeid;
    }

    public Arrangementtype(Integer arrangementtypeid, String arrangementtype) {
        this.arrangementtypeid = arrangementtypeid;
        this.arrangementtype = arrangementtype;
    }

    public Integer getArrangementtypeid() {
        return arrangementtypeid;
    }

    public void setArrangementtypeid(Integer arrangementtypeid) {
        this.arrangementtypeid = arrangementtypeid;
    }

    public String getArrangementtype() {
        return arrangementtype;
    }

    public void setArrangementtype(String arrangementtype) {
        this.arrangementtype = arrangementtype;
    }

    @XmlTransient
    public Collection<Monitor> getMonitorCollection() {
        return monitorCollection;
    }

    public void setMonitorCollection(Collection<Monitor> monitorCollection) {
        this.monitorCollection = monitorCollection;
    }

    public Arrangement getArrangement() {
        return arrangement;
    }

    public void setArrangement(Arrangement arrangement) {
        this.arrangement = arrangement;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (arrangementtypeid != null ? arrangementtypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arrangementtype)) {
            return false;
        }
        Arrangementtype other = (Arrangementtype) object;
        if ((this.arrangementtypeid == null && other.arrangementtypeid != null) || (this.arrangementtypeid != null && !this.arrangementtypeid.equals(other.arrangementtypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thomasmore.entity.Arrangementtype[ arrangementtypeid=" + arrangementtypeid + " ]";
    }
    
}
