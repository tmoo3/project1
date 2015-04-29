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
public class MonitoringPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONITORMONITORID")
    private int monitormonitorid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ARRANGEMENTARRANGEMENTID")
    private int arrangementarrangementid;

    public MonitoringPK() {
    }

    public MonitoringPK(int monitormonitorid, int arrangementarrangementid) {
        this.monitormonitorid = monitormonitorid;
        this.arrangementarrangementid = arrangementarrangementid;
    }

    public int getMonitormonitorid() {
        return monitormonitorid;
    }

    public void setMonitormonitorid(int monitormonitorid) {
        this.monitormonitorid = monitormonitorid;
    }

    public int getArrangementarrangementid() {
        return arrangementarrangementid;
    }

    public void setArrangementarrangementid(int arrangementarrangementid) {
        this.arrangementarrangementid = arrangementarrangementid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) monitormonitorid;
        hash += (int) arrangementarrangementid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonitoringPK)) {
            return false;
        }
        MonitoringPK other = (MonitoringPK) object;
        if (this.monitormonitorid != other.monitormonitorid) {
            return false;
        }
        if (this.arrangementarrangementid != other.arrangementarrangementid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thomasmore.entity.MonitoringPK[ monitormonitorid=" + monitormonitorid + ", arrangementarrangementid=" + arrangementarrangementid + " ]";
    }
    
}
