/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author chemk
 */
@Entity
@Table(name = "commande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commande.findAll", query = "SELECT c FROM Commande c")
    , @NamedQuery(name = "Commande.findById", query = "SELECT c FROM Commande c WHERE c.id = :id")
    , @NamedQuery(name = "Commande.findByDateCommande", query = "SELECT c FROM Commande c WHERE c.dateCommande = :dateCommande")
    , @NamedQuery(name = "Commande.findByDateLivraison", query = "SELECT c FROM Commande c WHERE c.dateLivraison = :dateLivraison")
    , @NamedQuery(name = "Commande.findByTotal", query = "SELECT c FROM Commande c WHERE c.total = :total")
    , @NamedQuery(name = "Commande.findByPrixLivraison", query = "SELECT c FROM Commande c WHERE c.prixLivraison = :prixLivraison")})
public class commande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date_commande")
    @Temporal(TemporalType.DATE)
    private Date dateCommande;
    @Basic(optional = false)
    @Column(name = "date_livraison")
    @Temporal(TemporalType.DATE)
    private Date dateLivraison;
    @Basic(optional = false)
    @Column(name = "total")
    private int total;
    @Basic(optional = false)
    @Column(name = "prix_livraison")
    private int prixLivraison;
    @JoinColumn(name = "etat_commande_id", referencedColumnName = "id")
    @ManyToOne
    private EtatCommande etatCommandeId;

    public commande() {
    }

    public commande(Integer id) {
        this.id = id;
    }

    public commande(Integer id, Date dateCommande, Date dateLivraison, int total, int prixLivraison) {
        this.id = id;
        this.dateCommande = dateCommande;
        this.dateLivraison = dateLivraison;
        this.total = total;
        this.prixLivraison = prixLivraison;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPrixLivraison() {
        return prixLivraison;
    }

    public void setPrixLivraison(int prixLivraison) {
        this.prixLivraison = prixLivraison;
    }

    public EtatCommande getEtatCommandeId() {
        return etatCommandeId;
    }

    public void setEtatCommandeId(EtatCommande etatCommandeId) {
        this.etatCommandeId = etatCommandeId;
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
        if (!(object instanceof commande)) {
            return false;
        }
        commande other = (commande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.edu.project.entities.Commande[ id=" + id + " ]";
    }
    
}
