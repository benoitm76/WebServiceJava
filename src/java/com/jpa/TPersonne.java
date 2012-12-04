/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Vincent
 */
@Entity
@Table(name = "T_PERSONNE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TPersonne.findAll", query = "SELECT t FROM TPersonne t"),
    @NamedQuery(name = "TPersonne.findByNom", query = "SELECT t FROM TPersonne t WHERE t.nom = :nom"),
    @NamedQuery(name = "TPersonne.findByPrenom", query = "SELECT t FROM TPersonne t WHERE t.prenom = :prenom"),
    @NamedQuery(name = "TPersonne.findByIdPersonne", query = "SELECT t FROM TPersonne t WHERE t.idPersonne = :idPersonne"),
    @NamedQuery(name = "TPersonne.findByDateDeNaissance", query = "SELECT t FROM TPersonne t WHERE t.dateDeNaissance = :dateDeNaissance"),
    @NamedQuery(name = "TPersonne.findBySexe", query = "SELECT t FROM TPersonne t WHERE t.sexe = :sexe")})
public class TPersonne implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 100)
    @Column(name = "NOM")
    private String nom;
    @Size(max = 100)
    @Column(name = "PRENOM")
    private String prenom;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERSONNE")
    private Integer idPersonne;
    @Column(name = "DATE_DE_NAISSANCE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDeNaissance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEXE")
    private char sexe;
    @JoinColumn(name = "ID_FINANCEUR", referencedColumnName = "ID_FINANCEUR")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TFinanceur idFinanceur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersonne", fetch = FetchType.LAZY)
    private List<TEmploi> tEmploiList;

    public TPersonne() {
    }

    public TPersonne(Integer idPersonne) {
        this.idPersonne = idPersonne;
    }

    public TPersonne(Integer idPersonne, char sexe) {
        this.idPersonne = idPersonne;
        this.sexe = sexe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Integer idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public char getSexe() {
        return sexe;
    }

    public void setSexe(char sexe) {
        this.sexe = sexe;
    }

    public TFinanceur getIdFinanceur() {
        return idFinanceur;
    }

    public void setIdFinanceur(TFinanceur idFinanceur) {
        this.idFinanceur = idFinanceur;
    }

    @XmlTransient
    public List<TEmploi> getTEmploiList() {
        return tEmploiList;
    }

    public void setTEmploiList(List<TEmploi> tEmploiList) {
        this.tEmploiList = tEmploiList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersonne != null ? idPersonne.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TPersonne)) {
            return false;
        }
        TPersonne other = (TPersonne) object;
        if ((this.idPersonne == null && other.idPersonne != null) || (this.idPersonne != null && !this.idPersonne.equals(other.idPersonne))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jpa.TPersonne[ idPersonne=" + idPersonne + " ]";
    }
    
}
