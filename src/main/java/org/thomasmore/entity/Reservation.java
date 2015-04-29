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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author H
 */
@Entity
@Table(name = "RESERVATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
    @NamedQuery(name = "Reservation.findByReservationid", query = "SELECT r FROM Reservation r WHERE r.reservationid = :reservationid"),
    @NamedQuery(name = "Reservation.findByDurationperminute", query = "SELECT r FROM Reservation r WHERE r.durationperminute = :durationperminute"),
    @NamedQuery(name = "Reservation.findByStartdate", query = "SELECT r FROM Reservation r WHERE r.startdate = :startdate"),
    @NamedQuery(name = "Reservation.findByStarttime", query = "SELECT r FROM Reservation r WHERE r.starttime = :starttime"),
    @NamedQuery(name = "Reservation.findByTotal", query = "SELECT r FROM Reservation r WHERE r.total = :total")})
public class Reservation implements Serializable {
    @ManyToMany(mappedBy = "reservationCollection", fetch = FetchType.LAZY)
    private Collection<Arrangement> arrangementCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RESERVATIONID")
    private Integer reservationid;
    @Column(name = "DURATIONPERMINUTE")
    private Integer durationperminute;
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Column(name = "STARTTIME")
    @Temporal(TemporalType.TIME)
    private Date starttime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TOTAL")
    private Double total;
    @JoinColumn(name = "CLIENTID", referencedColumnName = "CLIENTID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Client clientid;
    @OneToMany(mappedBy = "reservationid", fetch = FetchType.LAZY)
    private Collection<Equipmentrent> equipmentrentCollection;

    public Reservation() {
    }

    public Reservation(Integer reservationid) {
        this.reservationid = reservationid;
    }

    public Integer getReservationid() {
        return reservationid;
    }

    public void setReservationid(Integer reservationid) {
        this.reservationid = reservationid;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Client getClientid() {
        return clientid;
    }

    public void setClientid(Client clientid) {
        this.clientid = clientid;
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
        hash += (reservationid != null ? reservationid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.reservationid == null && other.reservationid != null) || (this.reservationid != null && !this.reservationid.equals(other.reservationid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thomasmore.entity.Reservation[ reservationid=" + reservationid + " ]";
    }

    @XmlTransient
    public Collection<Arrangement> getArrangementCollection() {
        return arrangementCollection;
    }

    public void setArrangementCollection(Collection<Arrangement> arrangementCollection) {
        this.arrangementCollection = arrangementCollection;
    }
    
}
