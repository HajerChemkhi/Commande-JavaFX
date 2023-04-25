/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chemk
 */
@Entity
@Table(name = "etat_commande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EtatCommande.findAll", query = "SELECT e FROM EtatCommande e")
    , @NamedQuery(name = "EtatCommande.findById", query = "SELECT e FROM EtatCommande e WHERE e.id = :id")
    , @NamedQuery(name = "EtatCommande.findByType", query = "SELECT e FROM EtatCommande e WHERE e.type = :type")})
public class EtatCommande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @OneToMany(mappedBy = "etatCommandeId")
    private Collection<commande> commandeCollection;

    public EtatCommande() {
    }

    public EtatCommande(Integer id) {
        this.id = id;
    }

    public EtatCommande(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<commande> getCommandeCollection() {
        return commandeCollection;
    }

    public void setCommandeCollection(Collection<commande> commandeCollection) {
        this.commandeCollection = commandeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EtatCommande)) {
            return false;
        }
        EtatCommande other = (EtatCommande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.edu.project.entities.EtatCommande[ id=" + id + " ]";
    }
    
}
