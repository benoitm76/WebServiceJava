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
@Table(name = "T_ENTREPRISE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TEntreprise.findAll", query = "SELECT t FROM TEntreprise t"),
    @NamedQuery(name = "TEntreprise.findByIdEntreprsise", query = "SELECT t FROM TEntreprise t WHERE t.idEntreprsise = :idEntreprsise"),
    @NamedQuery(name = "TEntreprise.findByNomEntreprise", query = "SELECT t FROM TEntreprise t WHERE t.nomEntreprise = :nomEntreprise")})
public class TEntreprise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ENTREPRSISE")
    private Integer idEntreprsise;
    @Size(max = 100)
    @Column(name = "NOM_ENTREPRISE")
    private String nomEntreprise;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEntreprsise", fetch = FetchType.LAZY)
    private List<TEmploi> tEmploiList;

    public TEntreprise() {
    }

    public TEntreprise(Integer idEntreprsise) {
        this.idEntreprsise = idEntreprsise;
    }

    public Integer getIdEntreprsise() {
        return idEntreprsise;
    }

    public void setIdEntreprsise(Integer idEntreprsise) {
        this.idEntreprsise = idEntreprsise;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
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
        hash += (idEntreprsise != null ? idEntreprsise.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TEntreprise)) {
            return false;
        }
        TEntreprise other = (TEntreprise) object;
        if ((this.idEntreprsise == null && other.idEntreprsise != null) || (this.idEntreprsise != null && !this.idEntreprsise.equals(other.idEntreprsise))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jpa.TEntreprise[ idEntreprsise=" + idEntreprsise + " ]";
    }
    
}
