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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "MONITOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Monitor.findAll", query = "SELECT m FROM Monitor m"),
    @NamedQuery(name = "Monitor.findByMonitorid", query = "SELECT m FROM Monitor m WHERE m.monitorid = :monitorid"),
    @NamedQuery(name = "Monitor.findByMonitorname", query = "SELECT m FROM Monitor m WHERE m.monitorname = :monitorname"),
    @NamedQuery(name = "Monitor.findByMonitoremail", query = "SELECT m FROM Monitor m WHERE m.monitoremail = :monitoremail"),
    @NamedQuery(name = "Monitor.findByMonitorphone", query = "SELECT m FROM Monitor m WHERE m.monitorphone = :monitorphone")})
public class Monitor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MONITORID")
    private Integer monitorid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "MONITORNAME")
    private String monitorname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "MONITOREMAIL")
    private String monitoremail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONITORPHONE")
    private int monitorphone;
    @ManyToMany(mappedBy = "monitorCollection", fetch = FetchType.LAZY)
    private Collection<Arrangementtype> arrangementtypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monitor", fetch = FetchType.LAZY)
    private Collection<Monitoring> monitoringCollection;

    public Monitor() {
    }

    public Monitor(Integer monitorid) {
        this.monitorid = monitorid;
    }

    public Monitor(Integer monitorid, String monitorname, String monitoremail, int monitorphone) {
        this.monitorid = monitorid;
        this.monitorname = monitorname;
        this.monitoremail = monitoremail;
        this.monitorphone = monitorphone;
    }

    public Integer getMonitorid() {
        return monitorid;
    }

    public void setMonitorid(Integer monitorid) {
        this.monitorid = monitorid;
    }

    public String getMonitorname() {
        return monitorname;
    }

    public void setMonitorname(String monitorname) {
        this.monitorname = monitorname;
    }

    public String getMonitoremail() {
        return monitoremail;
    }

    public void setMonitoremail(String monitoremail) {
        this.monitoremail = monitoremail;
    }

    public int getMonitorphone() {
        return monitorphone;
    }

    public void setMonitorphone(int monitorphone) {
        this.monitorphone = monitorphone;
    }

    @XmlTransient
    public Collection<Arrangementtype> getArrangementtypeCollection() {
        return arrangementtypeCollection;
    }

    public void setArrangementtypeCollection(Collection<Arrangementtype> arrangementtypeCollection) {
        this.arrangementtypeCollection = arrangementtypeCollection;
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
        hash += (monitorid != null ? monitorid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Monitor)) {
            return false;
        }
        Monitor other = (Monitor) object;
        if ((this.monitorid == null && other.monitorid != null) || (this.monitorid != null && !this.monitorid.equals(other.monitorid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thomasmore.entity.Monitor[ monitorid=" + monitorid + " ]";
    }
    
}
