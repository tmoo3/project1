/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "SETUP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Setup.findAll", query = "SELECT s FROM Setup s"),
    @NamedQuery(name = "Setup.findBySetupid", query = "SELECT s FROM Setup s WHERE s.setupid = :setupid"),
    @NamedQuery(name = "Setup.findByCommunication", query = "SELECT s FROM Setup s WHERE s.communication = :communication")})
public class Setup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SETUPID")
    private Integer setupid;
    @Size(max = 255)
    @Column(name = "COMMUNICATION")
    private String communication;

    public Setup() {
    }

    public Setup(Integer setupid) {
        this.setupid = setupid;
    }

    public Integer getSetupid() {
        return setupid;
    }

    public void setSetupid(Integer setupid) {
        this.setupid = setupid;
    }

    public String getCommunication() {
        return communication;
    }

    public void setCommunication(String communication) {
        this.communication = communication;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (setupid != null ? setupid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Setup)) {
            return false;
        }
        Setup other = (Setup) object;
        if ((this.setupid == null && other.setupid != null) || (this.setupid != null && !this.setupid.equals(other.setupid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thomasmore.entity.Setup[ setupid=" + setupid + " ]";
    }
    
}
