/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objects.externe;

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
    
    public InfoPersonne()
    {        
    }   
}
