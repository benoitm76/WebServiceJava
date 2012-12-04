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
@Table(name = "T_TYPE_EMPLOI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTypeEmploi.findAll", query = "SELECT t FROM TTypeEmploi t"),
    @NamedQuery(name = "TTypeEmploi.findByIdTypeEmploi", query = "SELECT t FROM TTypeEmploi t WHERE t.idTypeEmploi = :idTypeEmploi"),
    @NamedQuery(name = "TTypeEmploi.findByTypeEmploi", query = "SELECT t FROM TTypeEmploi t WHERE t.typeEmploi = :typeEmploi")})
public class TTypeEmploi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TYPE_EMPLOI")
    private Integer idTypeEmploi;
    @Size(max = 100)
    @Column(name = "TYPE_EMPLOI")
    private String typeEmploi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTypeEmploi", fetch = FetchType.LAZY)
    private List<TEmploi> tEmploiList;

    public TTypeEmploi() {
    }

    public TTypeEmploi(Integer idTypeEmploi) {
        this.idTypeEmploi = idTypeEmploi;
    }

    public Integer getIdTypeEmploi() {
        return idTypeEmploi;
    }

    public void setIdTypeEmploi(Integer idTypeEmploi) {
        this.idTypeEmploi = idTypeEmploi;
    }

    public String getTypeEmploi() {
        return typeEmploi;
    }

    public void setTypeEmploi(String typeEmploi) {
        this.typeEmploi = typeEmploi;
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
        hash += (idTypeEmploi != null ? idTypeEmploi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTypeEmploi)) {
            return false;
        }
        TTypeEmploi other = (TTypeEmploi) object;
        if ((this.idTypeEmploi == null && other.idTypeEmploi != null) || (this.idTypeEmploi != null && !this.idTypeEmploi.equals(other.idTypeEmploi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jpa.TTypeEmploi[ idTypeEmploi=" + idTypeEmploi + " ]";
    }
    
}
