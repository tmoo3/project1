/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author H
 */
@Entity
@Table(name = "AVAILABILITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Availability.findAll", query = "SELECT a FROM Availability a"),
    @NamedQuery(name = "Availability.findByAvailabilityid", query = "SELECT a FROM Availability a WHERE a.availabilityid = :availabilityid"),
    @NamedQuery(name = "Availability.findByDurationperminute", query = "SELECT a FROM Availability a WHERE a.durationperminute = :durationperminute"),
    @NamedQuery(name = "Availability.findByStartdate", query = "SELECT a FROM Availability a WHERE a.startdate = :startdate"),
    @NamedQuery(name = "Availability.findByStarttime", query = "SELECT a FROM Availability a WHERE a.starttime = :starttime")})
public class Availability implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AVAILABILITYID")
    private Integer availabilityid;
    @Column(name = "DURATIONPERMINUTE")
    private Integer durationperminute;
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Column(name = "STARTTIME")
    @Temporal(TemporalType.TIME)
    private Date starttime;
    @JoinColumn(name = "SUPPLIERID", referencedColumnName = "SUPPLIERID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier supplierid;
    @JoinColumn(name = "MATERIALID", referencedColumnName = "MATERIALID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Material materialid;

    public Availability() {
    }

    public Availability(Integer availabilityid) {
        this.availabilityid = availabilityid;
    }

    public Integer getAvailabilityid() {
        return availabilityid;
    }

    public void setAvailabilityid(Integer availabilityid) {
        this.availabilityid = availabilityid;
    }

    public Integer getDurationperminute() {
        return durationperminute;
    }

    public void setDurationperminute(Integer durationperminute) {
        this.durationperminute = durationperminute;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Supplier getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(Supplier supplierid) {
        this.supplierid = supplierid;
    }

    public Material getMaterialid() {
        return materialid;
    }

    public void setMaterialid(Material materialid) {
        this.materialid = materialid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (availabilityid != null ? availabilityid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Availability)) {
            return false;
        }
        Availability other = (Availability) object;
        if ((this.availabilityid == null && other.availabilityid != null) || (this.availabilityid != null && !this.availabilityid.equals(other.availabilityid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thomasmore.entity.Availability[ availabilityid=" + availabilityid + " ]";
    }
    
}
