/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author H
 */
@Entity
@Table(name = "ARRANGEMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arrangement.findAll", query = "SELECT a FROM Arrangement a"),
    @NamedQuery(name = "Arrangement.findByArrangementid", query = "SELECT a FROM Arrangement a WHERE a.arrangementid = :arrangementid"),
    @NamedQuery(name = "Arrangement.findByArrangementstartdate", query = "SELECT a FROM Arrangement a WHERE a.arrangementstartdate = :arrangementstartdate"),
    @NamedQuery(name = "Arrangement.findByArrangementstarttime", query = "SELECT a FROM Arrangement a WHERE a.arrangementstarttime = :arrangementstarttime"),
    @NamedQuery(name = "Arrangement.findByArrangementdurationperminute", query = "SELECT a FROM Arrangement a WHERE a.arrangementdurationperminute = :arrangementdurationperminute")})
public class Arrangement implements Serializable {
    @JoinTable(name = "PARTICIPATION", joinColumns = {
        @JoinColumn(name = "ARRANGEMENTARRANGEMENTID", referencedColumnName = "ARRANGEMENTID")}, inverseJoinColumns = {
        @JoinColumn(name = "RESERVATIONRESERVATIONID", referencedColumnName = "RESERVATIONID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Reservation> reservationCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ARRANGEMENTID")
    private Integer arrangementid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ARRANGEMENTSTARTDATE")
    @Temporal(TemporalType.DATE)
    private Date arrangementstartdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ARRANGEMENTSTARTTIME")
    @Temporal(TemporalType.TIME)
    private Date arrangementstarttime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ARRANGEMENTDURATIONPERMINUTE")
    private int arrangementdurationperminute;
    @JoinColumn(name = "ARRANGEMENTID", referencedColumnName = "ARRANGEMENTTYPEID", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Arrangementtype arrangementtype;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "arrangement", fetch = FetchType.LAZY)
    private Collection<Monitoring> monitoringCollection;

    public Arrangement() {
    }

    public Arrangement(Integer arrangementid) {
        this.arrangementid = arrangementid;
    }

    public Arrangement(Integer arrangementid, Date arrangementstartdate, Date arrangementstarttime, int arrangementdurationperminute) {
        this.arrangementid = arrangementid;
        this.arrangementstartdate = arrangementstartdate;
        this.arrangementstarttime = arrangementstarttime;
        this.arrangementdurationperminute = arrangementdurationperminute;
    }

    public Integer getArrangementid() {
        return arrangementid;
    }

    public void setArrangementid(Integer arrangementid) {
        this.arrangementid = arrangementid;
    }

    public Date getArrangementstartdate() {
        return arrangementstartdate;
    }

    public void setArrangementstartdate(Date arrangementstartdate) {
        this.arrangementstartdate = arrangementstartdate;
    }

    public Date getArrangementstarttime() {
        return arrangementstarttime;
    }

    public void setArrangementstarttime(Date arrangementstarttime) {
        this.arrangementstarttime = arrangementstarttime;
    }

    public int getArrangementdurationperminute() {
        return arrangementdurationperminute;
    }

    public void setArrangementdurationperminute(int arrangementdurationperminute) {
        this.arrangementdurationperminute = arrangementdurationperminute;
    }

    public Arrangementtype getArrangementtype() {
        return arrangementtype;
    }

    public void setArrangementtype(Arrangementtype arrangementtype) {
        this.arrangementtype = arrangementtype;
    }

    @XmlTransient
    public Collection<Monitoring> getMonitoringCollection() {
        return monitoringCollection;
    }

    public void setMonitoringCollection(Collection<Monitoring> monitoringCollection) {
        this.monitoringCollection = monitoringCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (arrangementid != null ? arrangementid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arrangement)) {
            return false;
        }
        Arrangement other = (Arrangement) object;
        if ((this.arrangementid == null && other.arrangementid != null) || (this.arrangementid != null && !this.arrangementid.equals(other.arrangementid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thomasmore.entity.Arrangement[ arrangementid=" + arrangementid + " ]";
    }

    @XmlTransient
    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
    }
    
}
