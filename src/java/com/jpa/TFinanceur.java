/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
 * @author Vincent
 */
@Entity
@Table(name = "T_FINANCEUR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TFinanceur.findAll", query = "SELECT t FROM TFinanceur t"),
    @NamedQuery(name = "TFinanceur.findByIdFinanceur", query = "SELECT t FROM TFinanceur t WHERE t.idFinanceur = :idFinanceur"),
    @NamedQuery(name = "TFinanceur.findByNomFinanceur", query = "SELECT t FROM TFinanceur t WHERE t.nomFinanceur = :nomFinanceur")})
public class TFinanceur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_FINANCEUR")
    private Integer idFinanceur;
    @Size(max = 100)
    @Column(name = "NOM_FINANCEUR")
    private String nomFinanceur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFinanceur", fetch = FetchType.LAZY)
    private List<TPersonne> tPersonneList;

    public TFinanceur() {
    }

    public TFinanceur(Integer idFinanceur) {
        this.idFinanceur = idFinanceur;
    }

    public Integer getIdFinanceur() {
        return idFinanceur;
    }

    public void setIdFinanceur(Integer idFinanceur) {
        this.idFinanceur = idFinanceur;
    }

    public String getNomFinanceur() {
        return nomFinanceur;
    }

    public void setNomFinanceur(String nomFinanceur) {
        this.nomFinanceur = nomFinanceur;
    }

    @XmlTransient
    public List<TPersonne> getTPersonneList() {
        return tPersonneList;
    }

    public void setTPersonneList(List<TPersonne> tPersonneList) {
        this.tPersonneList = tPersonneList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFinanceur != null ? idFinanceur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TFinanceur)) {
            return false;
        }
        TFinanceur other = (TFinanceur) object;
        if ((this.idFinanceur == null && other.idFinanceur != null) || (this.idFinanceur != null && !this.idFinanceur.equals(other.idFinanceur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jpa.TFinanceur[ idFinanceur=" + idFinanceur + " ]";
    }
    
}
