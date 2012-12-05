/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vincent
 */
@Entity
@Table(name = "T_EMPLOI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TEmploi.findAll", query = "SELECT t FROM TEmploi t"),
    @NamedQuery(name = "TEmploi.findByIntituleMetier", query = "SELECT t FROM TEmploi t WHERE t.intituleMetier = :intituleMetier"),
    @NamedQuery(name = "TEmploi.findByIdMetier", query = "SELECT t FROM TEmploi t WHERE t.idMetier = :idMetier")})
public class TEmploi implements Serializable {
    @Column(name = "DT_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtFin;
    @Column(name = "DT_DEBUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtDebut;
    private static final long serialVersionUID = 1L;
    @Size(max = 100)
    @Column(name = "INTITULE_METIER")
    private String intituleMetier;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_METIER")
    private Integer idMetier;
    @JoinColumn(name = "ID_TYPE_EMPLOI", referencedColumnName = "ID_TYPE_EMPLOI")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TTypeEmploi idTypeEmploi;
    @JoinColumn(name = "ID_PERSONNE", referencedColumnName = "ID_PERSONNE")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TPersonne idPersonne;
    @JoinColumn(name = "ID_ENTREPRSISE", referencedColumnName = "ID_ENTREPRSISE")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TEntreprise idEntreprsise;

    public TEmploi() {
    }

    public TEmploi(Integer idMetier) {
        this.idMetier = idMetier;
    }

    public String getIntituleMetier() {
        return intituleMetier;
    }

    public void setIntituleMetier(String intituleMetier) {
        this.intituleMetier = intituleMetier;
    }

    public Integer getIdMetier() {
        return idMetier;
    }

    public void setIdMetier(Integer idMetier) {
        this.idMetier = idMetier;
    }

    public TTypeEmploi getIdTypeEmploi() {
        return idTypeEmploi;
    }

    public void setIdTypeEmploi(TTypeEmploi idTypeEmploi) {
        this.idTypeEmploi = idTypeEmploi;
    }

    public TPersonne getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(TPersonne idPersonne) {
        this.idPersonne = idPersonne;
    }

    public TEntreprise getIdEntreprsise() {
        return idEntreprsise;
    }

    public void setIdEntreprsise(TEntreprise idEntreprsise) {
        this.idEntreprsise = idEntreprsise;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMetier != null ? idMetier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TEmploi)) {
            return false;
        }
        TEmploi other = (TEmploi) object;
        if ((this.idMetier == null && other.idMetier != null) || (this.idMetier != null && !this.idMetier.equals(other.idMetier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jpa.TEmploi[ idMetier=" + idMetier + " ]";
    }

    public Date getDtFin() {
        return dtFin;
    }

    public void setDtFin(Date dtFin) {
        this.dtFin = dtFin;
    }

    public Date getDtDebut() {
        return dtDebut;
    }

    public void setDtDebut(Date dtDebut) {
        this.dtDebut = dtDebut;
    }
    
}
