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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author H
 */
@Entity
@Table(name = "MONITORING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Monitoring.findAll", query = "SELECT m FROM Monitoring m"),
    @NamedQuery(name = "Monitoring.findByMonitormonitorid", query = "SELECT m FROM Monitoring m WHERE m.monitoringPK.monitormonitorid = :monitormonitorid"),
    @NamedQuery(name = "Monitoring.findByArrangementarrangementid", query = "SELECT m FROM Monitoring m WHERE m.monitoringPK.arrangementarrangementid = :arrangementarrangementid"),
    @NamedQuery(name = "Monitoring.findByMonitoringstartdate", query = "SELECT m FROM Monitoring m WHERE m.monitoringstartdate = :monitoringstartdate"),
    @NamedQuery(name = "Monitoring.findByMonitoringstarttime", query = "SELECT m FROM Monitoring m WHERE m.monitoringstarttime = :monitoringstarttime"),
    @NamedQuery(name = "Monitoring.findByMonitoringdurationperminute", query = "SELECT m FROM Monitoring m WHERE m.monitoringdurationperminute = :monitoringdurationperminute")})
public class Monitoring implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MonitoringPK monitoringPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONITORINGSTARTDATE")
    @Temporal(TemporalType.DATE)
    private Date monitoringstartdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONITORINGSTARTTIME")
    @Temporal(TemporalType.TIME)
    private Date monitoringstarttime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONITORINGDURATIONPERMINUTE")
    private int monitoringdurationperminute;
    @JoinColumn(name = "MONITORMONITORID", referencedColumnName = "MONITORID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Monitor monitor;
    @JoinColumn(name = "ARRANGEMENTARRANGEMENTID", referencedColumnName = "ARRANGEMENTID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Arrangement arrangement;

    public Monitoring() {
    }

    public Monitoring(MonitoringPK monitoringPK) {
        this.monitoringPK = monitoringPK;
    }

    public Monitoring(MonitoringPK monitoringPK, Date monitoringstartdate, Date monitoringstarttime, int monitoringdurationperminute) {
        this.monitoringPK = monitoringPK;
        this.monitoringstartdate = monitoringstartdate;
        this.monitoringstarttime = monitoringstarttime;
        this.monitoringdurationperminute = monitoringdurationperminute;
    }

    public Monitoring(int monitormonitorid, int arrangementarrangementid) {
        this.monitoringPK = new MonitoringPK(monitormonitorid, arrangementarrangementid);
    }

    public MonitoringPK getMonitoringPK() {
        return monitoringPK;
    }

    public void setMonitoringPK(MonitoringPK monitoringPK) {
        this.monitoringPK = monitoringPK;
    }

    public Date getMonitoringstartdate() {
        return monitoringstartdate;
    }

    public void setMonitoringstartdate(Date monitoringstartdate) {
        this.monitoringstartdate = monitoringstartdate;
    }

    public Date getMonitoringstarttime() {
        return monitoringstarttime;
    }

    public void setMonitoringstarttime(Date monitoringstarttime) {
        this.monitoringstarttime = monitoringstarttime;
    }

    public int getMonitoringdurationperminute() {
        return monitoringdurationperminute;
    }

    public void setMonitoringdurationperminute(int monitoringdurationperminute) {
        this.monitoringdurationperminute = monitoringdurationperminute;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
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
        hash += (monitoringPK != null ? monitoringPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Monitoring)) {
            return false;
        }
        Monitoring other = (Monitoring) object;
        if ((this.monitoringPK == null && other.monitoringPK != null) || (this.monitoringPK != null && !this.monitoringPK.equals(other.monitoringPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thomasmore.entity.Monitoring[ monitoringPK=" + monitoringPK + " ]";
    }
    
}
