/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vincent
 */
@Entity
@Table(name = "UTILISATEUR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u"),
    @NamedQuery(name = "Utilisateur.connect", query = "SELECT u FROM Utilisateur u WHERE u.login = :login AND u.mdp = :mdp"),
    @NamedQuery(name = "Utilisateur.findByIdutil", query = "SELECT u FROM Utilisateur u WHERE u.idutil = :idutil"),
    @NamedQuery(name = "Utilisateur.findByNomutil", query = "SELECT u FROM Utilisateur u WHERE u.nomutil = :nomutil"),
    @NamedQuery(name = "Utilisateur.findByPrenomutil", query = "SELECT u FROM Utilisateur u WHERE u.prenomutil = :prenomutil"),
    @NamedQuery(name = "Utilisateur.findByRole", query = "SELECT u FROM Utilisateur u WHERE u.role = :role"),
    @NamedQuery(name = "Utilisateur.findByLogin", query = "SELECT u FROM Utilisateur u WHERE u.login = :login"),
    @NamedQuery(name = "Utilisateur.findByMdp", query = "SELECT u FROM Utilisateur u WHERE u.mdp = :mdp")})
public class Utilisateur implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDUTIL")
    private BigDecimal idutil;
    @Size(max = 20)
    @Column(name = "NOMUTIL")
    private String nomutil;
    @Size(max = 20)
    @Column(name = "PRENOMUTIL")
    private String prenomutil;
    @Size(max = 20)
    @Column(name = "ROLE")
    private String role;
    @Size(max = 20)
    @Column(name = "LOGIN")
    private String login;
    @Size(max = 20)
    @Column(name = "MDP")
    private String mdp;

    public Utilisateur() {
    }

    public Utilisateur(BigDecimal idutil) {
        this.idutil = idutil;
    }

    public BigDecimal getIdutil() {
        return idutil;
    }

    public void setIdutil(BigDecimal idutil) {
        this.idutil = idutil;
    }

    public String getNomutil() {
        return nomutil;
    }

    public void setNomutil(String nomutil) {
        this.nomutil = nomutil;
    }

    public String getPrenomutil() {
        return prenomutil;
    }

    public void setPrenomutil(String prenomutil) {
        this.prenomutil = prenomutil;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idutil != null ? idutil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.idutil == null && other.idutil != null) || (this.idutil != null && !this.idutil.equals(other.idutil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jpa.Utilisateur[ idutil=" + idutil + " ]";
    }
    
}
