/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objects.externe;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Benoit
 */
public class InfoPersonne {    
    
    private int ID;
    private String Nom;
    private String Prenom;
    private boolean Sexe;
    private String DateNaissance;
    private List<InfoEmploi> Emplois;    
    private InfoFinanceur Financeur;
    private InfoConseiller Conseiller;
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getDateNaissance() {
        return DateNaissance;
    }

    public void setDateNaissance(String DateNaissance) {
        this.DateNaissance = DateNaissance;
    }
    
    public boolean isSexe() {
        return Sexe;
    }

    public void setSexe(boolean Sexe) {
        this.Sexe = Sexe;
    }
    
    public List<InfoEmploi> getEmplois() {
        return Emplois;
    }

    public void setEmplois(List<InfoEmploi> Emplois) {
        this.Emplois = Emplois;
    }

    public InfoFinanceur getFinanceur() {
        return Financeur;
    }

    public void setFinanceur(InfoFinanceur Financeur) {
        this.Financeur = Financeur;
    }

    public InfoConseiller getConseiller() {
        return Conseiller;
    }

    public void setConseiller(InfoConseiller Conseiller) {
        this.Conseiller = Conseiller;
    }
    
    public InfoPersonne()
    {        
    }   
    
    public InfoPersonne(int ID, String Nom, String Prenom, boolean Sexe, String DateNaissance, List<InfoEmploi> Emplois, InfoFinanceur Financeur, InfoConseiller Conseiller) {
        this.ID = ID;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Sexe = Sexe;
        this.DateNaissance = DateNaissance;
        this.Emplois = Emplois;
        this.Financeur = Financeur;
        this.Conseiller = Conseiller;
    }
}
