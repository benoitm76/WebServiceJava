/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objects.externe;

/**
 *
 * @author Benoît
 */
public class InfoConseiller {
    private int ID;
    private String Nom;
    private String Prenom;

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

    public InfoConseiller() {
    }

    public InfoConseiller(int ID, String Nom, String Prenom) {
        this.ID = ID;
        this.Nom = Nom;
        this.Prenom = Prenom;
    }   
}
