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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author H
 */
@Entity
@Table(name = "EQUIPMENTRENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipmentrent.findAll", query = "SELECT e FROM Equipmentrent e"),
    @NamedQuery(name = "Equipmentrent.findByEquipmentrentid", query = "SELECT e FROM Equipmentrent e WHERE e.equipmentrentid = :equipmentrentid"),
    @NamedQuery(name = "Equipmentrent.findByDurationperminute", query = "SELECT e FROM Equipmentrent e WHERE e.durationperminute = :durationperminute"),
    @NamedQuery(name = "Equipmentrent.findByStartdate", query = "SELECT e FROM Equipmentrent e WHERE e.startdate = :startdate"),
    @NamedQuery(name = "Equipmentrent.findByStarttime", query = "SELECT e FROM Equipmentrent e WHERE e.starttime = :starttime"),
    @NamedQuery(name = "Equipmentrent.findByState", query = "SELECT e FROM Equipmentrent e WHERE e.state = :state"),
    @NamedQuery(name = "Equipmentrent.findByTotal", query = "SELECT e FROM Equipmentrent e WHERE e.total = :total")})
public class Equipmentrent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EQUIPMENTRENTID")
    private Integer equipmentrentid;
    @Column(name = "DURATIONPERMINUTE")
    private Integer durationperminute;
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Column(name = "STARTTIME")
    @Temporal(TemporalType.TIME)
    private Date starttime;
    @Size(max = 255)
    @Column(name = "STATE")
    private String state;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TOTAL")
    private Double total;
    @JoinColumn(name = "RESERVATIONID", referencedColumnName = "RESERVATIONID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Reservation reservationid;
    @JoinColumn(name = "MATERIALID", referencedColumnName = "MATERIALID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Material materialid;

    public Equipmentrent() {
    }

    public Equipmentrent(Integer equipmentrentid) {
        this.equipmentrentid = equipmentrentid;
    }

    public Integer getEquipmentrentid() {
        return equipmentrentid;
    }

    public void setEquipmentrentid(Integer equipmentrentid) {
        this.equipmentrentid = equipmentrentid;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Reservation getReservationid() {
        return reservationid;
    }

    public void setReservationid(Reservation reservationid) {
        this.reservationid = reservationid;
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
        hash += (equipmentrentid != null ? equipmentrentid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipmentrent)) {
            return false;
        }
        Equipmentrent other = (Equipmentrent) object;
        if ((this.equipmentrentid == null && other.equipmentrentid != null) || (this.equipmentrentid != null && !this.equipmentrentid.equals(other.equipmentrentid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thomasmore.entity.Equipmentrent[ equipmentrentid=" + equipmentrentid + " ]";
    }
    
}
